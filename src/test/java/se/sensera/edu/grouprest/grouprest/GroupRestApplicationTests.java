package se.sensera.edu.grouprest.grouprest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class GroupRestApplicationTests {

    @LocalServerPort
    int port;

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    GroupRepository groupRepository;

    GroupApi groupApi;
    Group aaa;
    Group bbb;
    Group ccc;

    @BeforeEach
    void setUp() {
        KeyCloakToken token = KeyCloakToken.acquire("https://iam.sensera.se/", "test", "group-api", "user", "djnJnPf7VCQvp3Fc")
                .block(Duration.ofSeconds(10));
        webTestClient = webTestClient.mutate()
                .defaultHeader("Authorization","Bearer "+token.getAccessToken())
                .build();
        groupApi = new GroupApi(webTestClient);

        aaa = new Group("AAA");
        bbb = new Group("BBB");
        ccc = new Group("CCC");
    }

    @AfterEach
    void tearDown() {
        groupRepository.deleteAll()
                .block();
    }

    @Test
    void test_all_success() {
        // Given
        groupRepository.saveAll(List.of(aaa, bbb, ccc))
                .collectList()
                .block();

        // When
        List<GroupApi.Group> groups = groupApi.all()
                .collectList()
                .block();

        // Then
        assertEquals(List.of("AAA", "BBB", "CCC"), groups.stream().map(GroupApi.Group::getName).collect(Collectors.toList()));
    }

    @Test
    void test_get_success() {
        // Given
        groupRepository.saveAll(List.of(aaa, bbb, ccc))
                .collectList()
                .block();

        // When
        GroupApi.Group group = groupApi.get(aaa.getId())
                .block();

        // Then
        assertEquals("AAA", group.getName());
    }

    @Test
    void test_create_success() {
        // When
        GroupApi.Group group = groupApi.create("DDD")
                .block();

        // Then
        Group verifyGroup = groupRepository.findAll().single().block();
        assertEquals(verifyGroup.getName(), group.getName());
        assertEquals(verifyGroup.getId(), group.getId());
    }

    @Test
    void test_update_success() {
        // Given
        groupRepository.saveAll(List.of(aaa, bbb, ccc))
                .collectList()
                .block();

        // When
        GroupApi.Group group = groupApi.update(aaa.getId(), "DDD")
                .block();

        // Then
        Group verifyGroup = groupRepository.findById(aaa.getId()).block();
        assertEquals("DDD", group.getName());
        assertEquals("DDD", verifyGroup.getName());
    }

    @Test
    void test_delete_success() {
        // Given
        groupRepository.saveAll(List.of(aaa, bbb, ccc))
                .collectList()
                .block();

        // When
        groupApi.delete(aaa.getId())
                .block();

        // Then
        List<Group> verifyGroup = groupRepository.findAll().collectList().block();
        assertEquals(List.of("BBB", "CCC"), verifyGroup.stream().map(Group::getName).collect(Collectors.toList()));
    }
}


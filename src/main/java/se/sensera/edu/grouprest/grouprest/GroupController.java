package se.sensera.edu.grouprest.grouprest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;

@RestController
@RequestMapping("/api/groups")
@AllArgsConstructor
@RolesAllowed("Groups")
public class GroupController {

    GroupRepository groupRepository;

    @GetMapping
    public Flux<GroupDTO> all() {
        return groupRepository.findAll()
                .map(GroupController::toDTO);
    }

    @PostMapping
    public Mono<GroupDTO> create(@RequestBody() CreateGroupDTO createGroup) {
        return groupRepository.save(new Group(createGroup.getName()))
                .map(GroupController::toDTO);
    }

    @GetMapping("/{id}")
    public Mono<GroupDTO> get(@PathVariable("id") String id) {
        return groupRepository.findById(id)
                .map(GroupController::toDTO);
    }

    @PutMapping("/{id}")
    public Mono<GroupDTO> update(@PathVariable("id") String id, @RequestBody() UpdateGroupDTO updateGroup) {
        return groupRepository.findById(id)
                .flatMap(grp -> {
                    grp.setName(updateGroup.getName());
                    return groupRepository.save(grp);
                })
                .map(GroupController::toDTO);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") String id) {
        return groupRepository.deleteById(id);
    }

    public static GroupDTO toDTO(Group group) {
        return new GroupDTO(
                group.getId(),
                group.getName()
        );
    }

    @Value
    public static class GroupDTO {
        String id;
        String name;
    }

    @Value
    public static class CreateGroupDTO {
        String name;

        @JsonCreator
        public CreateGroupDTO(@JsonProperty("name") String name) {
            this.name = name;
        }
    }

    @Value
    public static class UpdateGroupDTO {
        String name;

        @JsonCreator
        public UpdateGroupDTO(@JsonProperty("name") String name) {
            this.name = name;
        }
    }
}

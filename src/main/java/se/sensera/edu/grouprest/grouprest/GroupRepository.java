package se.sensera.edu.grouprest.grouprest;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface GroupRepository extends ReactiveCrudRepository<Group,String> {

}

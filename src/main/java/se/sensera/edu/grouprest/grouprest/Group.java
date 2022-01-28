package se.sensera.edu.grouprest.grouprest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.annotation.security.RolesAllowed;
import java.util.UUID;

@Data
@NoArgsConstructor
@Table("_group")
public class Group {
    @Id String id;
    String name;
    @Version Long version;

    public Group(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.version = null;
    }
}

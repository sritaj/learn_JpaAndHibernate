package in.sritaj.jpaandhibernate.entity.customids.compositekeysembedded;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * Pilot Entity defining the Table structure and relevant mappings with dependent entities
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Pilot {

    @EmbeddedId
    private PilotID pilotID;

    private String name;
}

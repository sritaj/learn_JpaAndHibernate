package in.sritaj.jpaandhibernate.entity.customids.compositekeysembedded;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * PilotID class implementing Serializable interface for creating composite key for Pilot
 */
@Getter
@Setter
@Embeddable
public class PilotID implements Serializable {

    private int id;

    private String email;
}

package in.sritaj.jpaandhibernate.entity.customids.compositekeys;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * StaffID class implementing Serializable interface for creating composite key for Staff
 */
@Getter
@Setter
public class StaffID implements Serializable {

    private int id;

    private String email;

}

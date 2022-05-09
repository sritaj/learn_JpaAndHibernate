package in.sritaj.jpaandhibernate.entity.endtoendminiproject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

/**
 * Insurance Class for defining Insurance as embedded data in Patient Entity
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Insurance {

    private String providerName;

    private double copay;
}
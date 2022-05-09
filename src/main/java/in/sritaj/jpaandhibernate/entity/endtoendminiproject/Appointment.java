package in.sritaj.jpaandhibernate.entity.endtoendminiproject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Doctor Appointment defining the Table structure
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Timestamp appointmentTime;

    private boolean started;

    private boolean ended;

    private String reason;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;
}

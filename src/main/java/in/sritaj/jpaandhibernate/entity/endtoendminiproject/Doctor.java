package in.sritaj.jpaandhibernate.entity.endtoendminiproject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Doctor Entity defining the Table structure
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    private String speciality;

    @ManyToMany(mappedBy = "doctorList", cascade = CascadeType.ALL)
    private List<Patient> patientList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Appointment> appointmentList;
}

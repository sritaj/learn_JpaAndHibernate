package in.sritaj.jpaandhibernate.entity.endtoendminiproject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Patient Entity defining the Table structure
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    private String phone;

    @Embedded
    private Insurance insurance;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "patients_doctors",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id"))
    private List<Doctor> doctorList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Appointment> appointmentList;

}

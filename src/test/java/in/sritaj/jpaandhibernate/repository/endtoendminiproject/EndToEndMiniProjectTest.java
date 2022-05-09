package in.sritaj.jpaandhibernate.repository.endtoendminiproject;

import com.github.javafaker.Faker;
import in.sritaj.jpaandhibernate.entity.endtoendminiproject.Appointment;
import in.sritaj.jpaandhibernate.entity.endtoendminiproject.Doctor;
import in.sritaj.jpaandhibernate.entity.endtoendminiproject.Insurance;
import in.sritaj.jpaandhibernate.entity.endtoendminiproject.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class EndToEndMiniProjectTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private AppointmentSpringDataRepository appointmentSpringDataRepository;

    @Autowired
    private DoctorSpringDataRepository doctorSpringDataRepository;

    @Autowired
    private PatientSpringDataRepository patientSpringDataRepository;

    private Faker fs = new Faker();
    private Doctor insertedDoctor;
    private Patient insertedPatient;

    @Test(testName = "Validate Creation of Doctor", priority = 0)
    public void validateCreationOfDoctor() {
        Doctor doctor = new Doctor();
        doctor.setFirstName(fs.name().firstName());
        doctor.setLastName(fs.name().lastName());
        doctor.setSpeciality("Cardio");

        insertedDoctor = doctorSpringDataRepository.save(doctor);

        Assert.assertNotNull(insertedDoctor);

    }

    @Test(testName = "Validate Creation of Patient", priority = 1)
    public void validateCreationOfPatient() {

        Patient patient = new Patient();
        patient.setFirstName(fs.name().firstName());
        patient.setLastName(fs.name().lastName());
        patient.setPhone(fs.phoneNumber().cellPhone());

        Insurance insurance = new Insurance();
        insurance.setProviderName("Allainz HealthCare");
        insurance.setCopay(599.99);
        patient.setInsurance(insurance);

        insertedPatient = patientSpringDataRepository.save(patient);

        Assert.assertNotNull(insertedPatient);

    }

    @Test(testName = "Validate Mapping of Doctor And Patient", priority = 3,
            dependsOnMethods = {"validateCreationOfDoctor", "validateCreationOfPatient"})
    public void validateMappingOfDoctorAndPatient() {

        Iterable<Doctor> doctor = doctorSpringDataRepository.findAll();

        List<Doctor> doctorList = new ArrayList<>();
        doctor.forEach(d -> doctorList.add(d));

        insertedPatient.setDoctorList(doctorList);

        Patient updatedPatient = patientSpringDataRepository.save(insertedPatient);

        Assert.assertFalse(updatedPatient.getDoctorList().isEmpty());

    }

    @Test(testName = "Validate Creation of Appointment", priority = 4,
            dependsOnMethods = {"validateCreationOfDoctor", "validateCreationOfPatient", "validateMappingOfDoctorAndPatient"})
    public void validateCreationOfAppointment() {

        Appointment appointment = new Appointment();
        Timestamp appointmentTime = new Timestamp(new Date().getTime());
        appointment.setAppointmentTime(appointmentTime);
        appointment.setReason("Neck pain");

        appointment.setPatient(insertedPatient);
        appointment.setDoctor(insertedDoctor);

        Appointment createdAppointment = appointmentSpringDataRepository.save(appointment);
        Assert.assertNotNull(createdAppointment);
    }

    @AfterTest
    public void clearDB() {
        patientSpringDataRepository.deleteAll();
    }
}

package ma.enset.mvchospital;

import ma.enset.mvchospital.entities.Patient;
import ma.enset.mvchospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.Method;
import java.util.Date;

@SpringBootApplication
public class MvcHospitalApplication implements CommandLineRunner {
    @Autowired //l'injection de dependances
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(MvcHospitalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception { //executer des tache lors de demarage de l'appliquation
        /*//Method-1
        Patient patient1 = new Patient();
        patient1.setId(null);
        patient1.setNom("Lobna");
        patient1.setDatenaissance(new Date());
        patient1.setMalade(false);
        patient1.setScore(23);
        //methode-2
        Patient patient2 = new Patient(null,"Mohammed",new Date(),false,20);
        //methode-3 //en utilisant builder
        Patient patient3 = Patient.builder()
                .nom("safaa")
                .datenaissance(new Date())
                .score(50)
                .malade(false)
                .build();*/

        //patientRepository.save(new Patient(null,"lobna",new Date(),false,101));
        //patientRepository.save(new Patient(null,"rabhi",new Date(),true,200));
        //patientRepository.save(new Patient(null,"salma",new Date(),false,120));

    }

    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

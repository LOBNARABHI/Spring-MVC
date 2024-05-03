package ma.enset.mvchospital;

import ma.enset.mvchospital.entities.Patient;
import ma.enset.mvchospital.repository.PatientRepository;
import ma.enset.mvchospital.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

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

   //@Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
        PasswordEncoder passwordEncoder=passwordEncoder();
        return args -> {
            UserDetails user1=jdbcUserDetailsManager.loadUserByUsername("user11");
            if(user1==null){
                jdbcUserDetailsManager.createUser(
                        User.withUsername("user11").password(passwordEncoder.encode("1234")).roles("USER").build()
                );
            }
            UserDetails user2=jdbcUserDetailsManager.loadUserByUsername("user22");
            if(user2==null){
                jdbcUserDetailsManager.createUser(
                        User.withUsername("user22").password(passwordEncoder.encode("1234")).roles("USER").build()
                );
            }
            UserDetails user3=jdbcUserDetailsManager.loadUserByUsername("admin2");
            if(user3==null){
                jdbcUserDetailsManager.createUser(
                        User.withUsername("admin2").password(passwordEncoder.encode("1234")).roles("ADMIN","USER").build()
                );
            }


        };
    }

   // @Bean
    CommandLineRunner commandLineRunner (AccountService accountService) {
        return args -> {
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");
            accountService.addNewUser("user1", "1234", "user1@gmail.com", "1234");
            accountService.addNewUser("user2", "1234", "user2@gmail.com", "1234");
            accountService.addNewUser("admin", "1234", "admin@gmail.com", "1234");

            accountService.addRoleToUser("user1", "USER");
            accountService.addRoleToUser("user2", "USER");
            accountService.addRoleToUser("admin", "USER");
            accountService.addRoleToUser("admin", "ADMIN");
        };
    }

        @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

/*
package info.schuwan.projectone.employee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

//-----------------------This is temporary helper class to create students in teh database----------------//
@Configuration
public class EmployeeConfig {
    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository repository) {
        return args -> {
            UserEntity mariam = new UserEntity(                       // The id will be auto generated in the database
                        "mariam", "mariam.jamal@gmail.com",
                          LocalDate.of(2000, 1, 5));

            UserEntity lucy = new UserEntity(
                    "lucy", "lucy.yum@gmail.com",
                    LocalDate.of(2000, 2, 5));

            UserEntity cindy = new UserEntity(
                    "cindy", "cind.lubricat@gmail.com",
                    LocalDate.of(2001, 3, 5));

            repository.saveAll(List.of(mariam, lucy, cindy));   // to save the 3 students we have just created
        };
    }
}
*/

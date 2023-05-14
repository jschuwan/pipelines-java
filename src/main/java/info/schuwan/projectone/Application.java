package info.schuwan.projectone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication               //---------------------------This annotation makes this server Restful End points
//@EnableJpaRepositories(basePackageClasses =  UserRepository.class)    // we pass the package name /repository name where the repositories exist
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    Global.whoAmI = "user_user2";
    Global.setSubtotalRequest(0.00f);
    Global.setExpenseIdNumbers("Expense id(s) included on this report");


    }
}

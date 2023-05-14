package info.schuwan.projectone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
                     //---------------------------This annotation makes this server Restful End points
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}

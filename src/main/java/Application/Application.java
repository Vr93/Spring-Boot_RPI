package Application;


import Controllers.GreetingController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan(basePackageClasses = GreetingController.class)
public class Application {

    /**
     *
     * @author vegard
     *
     * To generate jar file, run maven command -> clean compile assembly:single
     *
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
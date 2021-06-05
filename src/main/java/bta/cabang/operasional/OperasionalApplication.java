package bta.cabang.operasional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class OperasionalApplication {

    public static void main(String[] args) {
        SpringApplication.run(OperasionalApplication.class, args);
    }

}

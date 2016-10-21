package uk.ac.ebi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Created by ilinca on 21/10/2016.
 */

@SpringBootApplication
public class PhenoImageShareMain extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PhenoImageShareMain.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(PhenoImageShareMain.class, args);
    }

}
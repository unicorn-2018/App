package jp.co.bizrefine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BizcalendarApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BizcalendarApplication.class, args);
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BizcalendarApplication.class);
    }

    @RequestMapping(value = "bizcalendar", method = RequestMethod.GET)
    public String getSomething() {
        return "something";
    }

}

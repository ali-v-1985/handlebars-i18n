package me.valizadeh.practices.handlebars.i18n;

import java.io.IOException;
import java.util.Locale;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import me.valizadeh.practices.handlebars.i18n.template.TemplateProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        run(context);
    }

    public static void run(ApplicationContext ctx) throws IOException {
        TemplateProvider templateProvider = ctx.getBean(TemplateProvider.class);
        log.debug(templateProvider.getTemplate().apply(new User("Ali", Locale.US.toString())));
        log.debug(templateProvider.getTemplate().apply(new User("Ali", "es")));
        log.debug(templateProvider.getTemplate().apply(new User("Ali", Locale.FRENCH.toString())));
        log.debug(templateProvider.getTemplate().apply(new User("Ali", "nl")));
    }


    @Value
    static class User {
        String fullName;
        String locale;
    }

}

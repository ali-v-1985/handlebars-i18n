package me.valizadeh.practices.handlebars.i18n.config;

import com.github.jknack.handlebars.EscapingStrategy;
import com.github.jknack.handlebars.Handlebars;
import java.util.Locale;
import javax.validation.constraints.NotNull;
import lombok.Data;
import me.valizadeh.practices.handlebars.i18n.template.helper.MessageBundleHelper;
import org.javamoney.moneta.format.CurrencyStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Component
@Validated
@Configuration
@ConfigurationProperties("handlebars")
public class HandlebarsConfiguration {

    @NotNull
    private CurrencyStyle currencyStyle;

    @NotNull
    private Locale locale;

    @NotNull
    private String[] resourceBaseNames;

    @NotNull
    private Resource template;

    @Bean
    public Handlebars handlebars(MessageBundleHelper messageBundleHelper) {
        return new Handlebars()
            .with(EscapingStrategy.HTML_ENTITY)
            .prettyPrint(true)
            .registerHelpers(messageBundleHelper);
    }

    @Bean
    public MessageSource labels(HandlebarsConfiguration configuration) {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.addBasenames(configuration.getResourceBaseNames());
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}

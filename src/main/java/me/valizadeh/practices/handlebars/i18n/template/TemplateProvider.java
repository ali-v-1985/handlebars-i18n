package me.valizadeh.practices.handlebars.i18n.template;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.URLTemplateSource;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import me.valizadeh.practices.handlebars.i18n.config.HandlebarsConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Data
@Slf4j
@Component
public class TemplateProvider {

    public static final String DEFAULT_TEMPLATE_NAME = "default";
    private final HandlebarsConfiguration configuration;
    private final Handlebars handlebars;

    private Template template;

    @PostConstruct
    public void loadTemplates() throws IOException {
        log.debug("Loading PDF templates {}", this.configuration.getTemplate());
        this.template = compileTemplate(this.configuration.getTemplate());
    }

    private Template compileTemplate(@NotNull Resource resource) throws IOException {
        URL url = resource.getURL();
        return handlebars.compile(
            new URLTemplateSource(Optional.ofNullable(resource.getFilename()).orElse(DEFAULT_TEMPLATE_NAME), url));
    }
}

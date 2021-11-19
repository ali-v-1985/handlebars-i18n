package me.valizadeh.practices.handlebars.i18n.template.helper;

import static org.apache.commons.lang3.Validate.notEmpty;

import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.internal.Locales;
import java.util.Locale;
import me.valizadeh.practices.handlebars.i18n.config.HandlebarsConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageBundleHelper {

    private final HandlebarsConfiguration configuration;
    private final MessageSource labels;


    public MessageBundleHelper(
        HandlebarsConfiguration configuration,
        MessageSource labels) {
        this.configuration = configuration;
        this.labels = labels;
    }

    public String label(final String key, final Options options) {
        notEmpty(key, "found: '%s', expected 'bundle's key'", key);
        Locale locale = Locales
            .fromString(options.hash("locale", configuration.getLocale()));
        return labels.getMessage(key, null, locale);
    }
}

package fr.atbdx.lightningtalk.domaine;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class ConfigurationDuDomaine {
    
    @Bean
    public static PropertyPlaceholderConfigurer valorisation(Environment environment) {
        PropertyPlaceholderConfigurer valorisation = new PropertyPlaceholderConfigurer();
        valorisation.setLocations(new ClassPathResource[] {new ClassPathResource("valorisation-environnement-" + environment.getProperty("environnement") + ".properties")});
        return valorisation;
    }
    
}

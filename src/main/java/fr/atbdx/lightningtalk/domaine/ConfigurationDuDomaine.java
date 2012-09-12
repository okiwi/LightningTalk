package fr.atbdx.lightningtalk.domaine;

import java.net.UnknownHostException;

import org.mongolink.MongoSessionManager;
import org.mongolink.Settings;
import org.mongolink.domain.mapper.ContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import com.mongodb.MongoException;

@Configuration
public class ConfigurationDuDomaine {

    @Bean
    public static PropertyPlaceholderConfigurer valorisation(Environment environment) {
        PropertyPlaceholderConfigurer valorisation = new PropertyPlaceholderConfigurer();
        valorisation.setLocations(new ClassPathResource[] { new ClassPathResource("valorisation-environnement-" + environment.getProperty("environnement") + ".properties") });
        return valorisation;
    }

    @Bean
    public static MongoSessionManager initialiserLaBaseLightningTalkMongoDB(@Value("${mongodb.adresseIp}") String adresseIp, @Value("${mongodb.port}") int port,
            @Value("${mongodb.utilisateur}") String utilisateur, @Value("${mongodb.motDePasse}") String motDePasse) throws UnknownHostException, MongoException {
        final Settings settings = Settings.defaultInstance().withHost(adresseIp).withPort(port).withDbName("lightningtalk").withAuthentication(utilisateur, motDePasse);
        return MongoSessionManager.create(new ContextBuilder("fr.atbdx.lightningtalk.domaine.mongodb.mapping"), settings);
    }

}

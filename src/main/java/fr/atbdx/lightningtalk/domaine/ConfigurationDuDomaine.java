package fr.atbdx.lightningtalk.domaine;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import com.mongodb.DB;
import com.mongodb.Mongo;
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
    public static DB initialiserLaBaseLightningTalkMongoDB(@Value("${mongodb.adresseIp}") String adresseIp, @Value("${mongodb.port}") int port, @Value("${mongodb.utilisateur}") String utilisateur,
            @Value("${mongodb.motDePasse}") String motDePasse) throws UnknownHostException, MongoException {
        DB lightningTalk = new Mongo(adresseIp, port).getDB("LightningTalk");
        lightningTalk.authenticate(utilisateur, motDePasse.toCharArray());
        return lightningTalk;
    }

}

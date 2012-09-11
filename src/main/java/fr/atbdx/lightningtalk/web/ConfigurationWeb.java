package fr.atbdx.lightningtalk.web;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.google.common.collect.Maps;

import fr.atbdx.lightningtalk.domaine.ConfigurationDuDomaine;
import freemarker.template.utility.XmlEscape;

@Configuration
@EnableWebMvc
@Import(ConfigurationDuDomaine.class)
public class ConfigurationWeb extends WebMvcConfigurerAdapter {
    private static final int UNE_JOURNEE_EN_SECONDE = (int) TimeUnit.DAYS.toSeconds(1);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        ResourceHandlerRegistration resourcesHandler = registry.addResourceHandler("/ressources/**");
        resourcesHandler.addResourceLocations("/ressources/");
        resourcesHandler.setCachePeriod(UNE_JOURNEE_EN_SECONDE);
    }

    @Bean
    public FreeMarkerViewResolver viewResolver() {
        FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
        freeMarkerViewResolver.setCache(true);
        freeMarkerViewResolver.setPrefix("");
        freeMarkerViewResolver.setSuffix(".ftl");
        return freeMarkerViewResolver;
    }
    

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("accueil");
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/freemarker/");
        Properties settings = new Properties();
        settings.setProperty("number_format", "0.######");
        freeMarkerConfigurer.setFreemarkerSettings(settings);
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("xml_escape", new XmlEscape());
        freeMarkerConfigurer.setFreemarkerVariables(variables);
        return freeMarkerConfigurer;
    }

}

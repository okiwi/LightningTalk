package fr.atbdx.lightningtalk.doublures.spring;

import static org.mockito.Mockito.mock;

import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

public class FakeResourceHandlerRegistry extends ResourceHandlerRegistry {

    public String[] ressourceAjouter;
    public FakeResourceHandlerRegistration resourceHandlerRegistration = new FakeResourceHandlerRegistration();

    public FakeResourceHandlerRegistry() {
        super(mock(ApplicationContext.class), null);
    }

    @Override
    public ResourceHandlerRegistration addResourceHandler(String... ressourceAjouter) {
        this.ressourceAjouter = ressourceAjouter;
        return resourceHandlerRegistration;
    }

}

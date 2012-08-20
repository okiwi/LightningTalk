package fr.atbdx.lightningtalk.doublures.spring;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;

public class FakeResourceHandlerRegistration extends ResourceHandlerRegistration {

    public String[] resourceLocations;

    public FakeResourceHandlerRegistration() {
        super(null, new String[1]);
    }

    @Override
    public ResourceHandlerRegistration addResourceLocations(String... resourceLocations) {
        this.resourceLocations = resourceLocations;
        return this;
    }

}

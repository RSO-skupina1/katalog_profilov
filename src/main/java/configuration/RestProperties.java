package configuration;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("rest-properties")
public class RestProperties {
    @ConfigValue(value = "external-dependencies.sporocilni-sistem.enabled", watch = true)
    private boolean sporocilniSistemEnabled;

    public boolean getSporocilniSistemEnabled() {
        return sporocilniSistemEnabled;
    }

    public void setSporocilniSistemEnabled(boolean sporocilniSistemEnabled) {
        this.sporocilniSistemEnabled = sporocilniSistemEnabled;
    }
}

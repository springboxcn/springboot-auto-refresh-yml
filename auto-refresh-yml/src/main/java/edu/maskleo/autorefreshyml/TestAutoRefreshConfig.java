package edu.maskleo.autorefreshyml;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "test")
@Component
public class TestAutoRefreshConfig {

    private String testVal;

    public String getTestVal() {
        return testVal;
    }

    public void setTestVal(String testVal) {
        this.testVal = testVal;
    }
}

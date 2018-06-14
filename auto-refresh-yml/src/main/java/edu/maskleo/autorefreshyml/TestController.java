package edu.maskleo.autorefreshyml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

@RestController
public class TestController {

    @Autowired
    private TestAutoRefreshConfig autoRefreshConfig;

    @RequestMapping("/")
    public ResponseEntity<String> index() {
        return new ResponseEntity<>(autoRefreshConfig.getTestVal(), HttpStatus.OK);
    }

    @RequestMapping("/refresh")
    public ResponseEntity<String> refresh() throws Exception {
        reload();
        return new ResponseEntity<>(autoRefreshConfig.getTestVal(), HttpStatus.OK);
    }

    private void reload() throws Exception {
        // reload from *.properties
        Properties properties = new Properties();
        InputStream is = this.getClass().getResourceAsStream("/application.properties");
        properties.load(is);

        ConfigurationProperties configurationProperties = TestAutoRefreshConfig.class.getAnnotation(ConfigurationProperties.class);
        String prefix = configurationProperties.prefix();
        Field[] fieldArr = TestAutoRefreshConfig.class.getDeclaredFields();
        for (Field field : fieldArr) {
            field.setAccessible(true);
            // You can set the value for the property here
            System.out.println(properties.getProperty(prefix + "." + field.getName()));
            TestAutoRefreshConfig config = SpringBeanUtils.getBean(TestAutoRefreshConfig.class);
            field.set(config, properties.getProperty(prefix + "." + field.getName()));
            field.setAccessible(false);
        }
    }

}

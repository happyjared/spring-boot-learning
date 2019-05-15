package cn.mariojd.container.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.server.Compression;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.util.unit.DataSize;

@SpringBootApplication
public class SpringBootConfigurationApplication implements WebServerFactoryCustomizer<JettyServletWebServerFactory> {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConfigurationApplication.class, args);
    }

    @Override
    public void customize(JettyServletWebServerFactory factory) {
        Compression compression = new Compression();
        compression.setEnabled(true);
        compression.setMinResponseSize(DataSize.ofBytes(512));
        factory.setCompression(compression);
    }

}

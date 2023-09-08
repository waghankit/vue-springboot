package com.vue.vuebackend;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpTransportConfig {

    @Bean
    public HttpTransport httpTransport() {
        return new NetHttpTransport();
    }
}

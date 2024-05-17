package com.co.unitravel.infrastructure.config;

import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class FeignResponseDecoderConfig {

    @Bean
    public Decoder feignDecoder() {
        ObjectFactory<HttpMessageConverters> messageConverters = () -> {
            HttpMessageConverters converters = new HttpMessageConverters(new MappingJackson2HttpMessageConverter());
            return converters;
        };
        return new SpringDecoder(messageConverters);
    }
}

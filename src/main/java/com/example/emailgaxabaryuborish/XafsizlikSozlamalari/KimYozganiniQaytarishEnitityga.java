package com.example.emailgaxabaryuborish.XafsizlikSozlamalari;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class KimYozganiniQaytarishEnitityga {
    @Bean
    AuditorAware<Integer> auditorAware(){
        return new KimYaratganiniBilish();
    }
}

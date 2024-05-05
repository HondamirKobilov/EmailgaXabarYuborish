package com.example.emailgaxabaryuborish.XafsizlikSozlamalari;

import com.example.emailgaxabaryuborish.Entity.Users;
import org.apache.catalina.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class KimYaratganiniBilish implements AuditorAware<Integer> {

    @Override
    public Optional getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();//bu aniqlab beradi
        if (authentication!=null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")){
            Users principal = (Users) authentication.getPrincipal();
            return Optional.of(principal.getId());
        }

        return Optional.empty();
    }

}

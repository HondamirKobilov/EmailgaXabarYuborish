package com.example.emailgaxabaryuborish.Token;
import com.example.emailgaxabaryuborish.Servis.UserServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class Filtr extends OncePerRequestFilter {
    @Autowired
    Tokenn tokenn;

    @Autowired
    UserServis userServis;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String auth = request.getHeader("auth");
        if (auth!=null) {
            boolean check = tokenn.check(auth);
            if (check){
                String username = tokenn.deshifr(auth);
                if(username!=null){
                    UserDetails userDetails = userServis.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(usernamePasswordAuthenticationToken);
                }
                else{
                    System.out.println("Deshifr qilolmadi");
                }
            }
            else {
                System.out.println("Muddati tugagan");
            }
        }
        else {
            System.out.println("Token ololmadi");
        }
        filterChain.doFilter(request,response);
    }

}

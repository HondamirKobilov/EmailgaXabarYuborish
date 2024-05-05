package com.example.emailgaxabaryuborish.Controller;

import com.example.emailgaxabaryuborish.Payloat.ApiResponsive;
import com.example.emailgaxabaryuborish.Payloat.LoginDTO;
import com.example.emailgaxabaryuborish.Payloat.UserDTO;
import com.example.emailgaxabaryuborish.Servis.UserServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userAPI")
public class UserController {
    @Autowired
    UserServis userServis;

    @PostMapping("/post")
    public HttpEntity<?> post(@RequestBody UserDTO userDTO){
        ApiResponsive apiResponsive = userServis.postUser(userDTO);
        return ResponseEntity.status(apiResponsive.isHolat()? HttpStatus.OK: HttpStatus.ALREADY_REPORTED).body(apiResponsive.getXabar());
    }
    @GetMapping("/tasdiqlash")
    public HttpEntity<?> get(@RequestParam String email, @RequestParam String emailkod){
        ApiResponsive apiResponsive = userServis.getTasdiqlash(email,emailkod);
        return  ResponseEntity.status(apiResponsive.isHolat()? 200:409).body(apiResponsive.getXabar());
    }
    @PostMapping("/loginQilish")
    public HttpEntity<?> loginQilish(@RequestBody LoginDTO loginDTO){
        ApiResponsive apiResponsive = userServis.loginPost(loginDTO);
        return ResponseEntity.status(apiResponsive.isHolat()? HttpStatus.OK: HttpStatus.ALREADY_REPORTED).body(apiResponsive.getXabar());
    }
}

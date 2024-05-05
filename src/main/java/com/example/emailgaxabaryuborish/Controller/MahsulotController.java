package com.example.emailgaxabaryuborish.Controller;

import com.example.emailgaxabaryuborish.Entity.Mahsulot;
import com.example.emailgaxabaryuborish.Payloat.ApiResponsive;
import com.example.emailgaxabaryuborish.Payloat.MahsulotDTO;
import com.example.emailgaxabaryuborish.Repository.MahsulotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mahsulotAPI")
public class MahsulotController {
    @Autowired
    MahsulotRepository mahsulotRepository;

    @PostMapping("/mahsulotPost")
    public HttpEntity<?> post(@RequestBody MahsulotDTO dto){
        Mahsulot mahsulot = new Mahsulot();
        mahsulot.setNomi(dto.getNomi());
        mahsulot.setTuri(dto.getTuri());
        mahsulot.setNarxi(dto.getNarxi());
        mahsulotRepository.save(mahsulot);
        return ResponseEntity.ok("Mahsulot qo'shildi");
    }
}

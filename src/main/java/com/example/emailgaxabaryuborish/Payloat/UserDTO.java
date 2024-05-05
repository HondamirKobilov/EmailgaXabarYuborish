package com.example.emailgaxabaryuborish.Payloat;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UserDTO {

    private String ismi;

    private String familyasi;

    private String telRaqam;

    private String username;

    private String password;
}

package com.example.emailgaxabaryuborish.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 3, max = 15)  //1-usul
    @Column(nullable = false, length = 15)
    private String ismi;

    @Length(min = 7, max = 17) //2-usul
    @Column(nullable = false)
    private String familyasi;

    @Length(min = 13, max = 13)
    @Column(nullable = false)
    private String telRaqam;

    @Email
    @Column(nullable = false)
    private String username;

    @Length(min = 8)
    @Column(nullable = false, columnDefinition = "text")
    private String password;

    private String emailKod;

    @Column(updatable = false)  // bu admin tahrirllolmaydigan qilib quyish
    @CreationTimestamp  //opkeb yozib berdiradigan anotatsiya
    private Timestamp yaratilganVaqt;


    @UpdateTimestamp   // tahrirlangan payti yoziladigan vaqt
    private Timestamp tahrirlanganVaqt;

    @OneToMany
    private List<Lavozim> lavozimList;

    private boolean accountNonExpired=true;
    private boolean accountNonLocked=true;
    private boolean credentialsNonExpired=true;
    private boolean enabled=false;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return lavozimList;
    }
}

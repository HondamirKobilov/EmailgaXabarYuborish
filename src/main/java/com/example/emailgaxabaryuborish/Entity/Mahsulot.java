package com.example.emailgaxabaryuborish.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(value = AuditingEntityListener.class)  //chaqirib beradi
public class Mahsulot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nomi;
    @Column(nullable = false)
    private String turi;
    @Column(nullable = false)
    private String narxi;
    @CreationTimestamp
    private Timestamp yaratilganVaqt;
    @UpdateTimestamp
    private Timestamp tahrirlanganVaqt;
    @CreatedBy
    private Integer kimYaratgan;
    @LastModifiedBy
    private Integer kimTahrirlagan;
}

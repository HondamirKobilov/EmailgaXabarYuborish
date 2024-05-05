package com.example.emailgaxabaryuborish.Repository;

import com.example.emailgaxabaryuborish.Entity.Enum.Lavozimlar;
import com.example.emailgaxabaryuborish.Entity.Lavozim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LavozimRepository extends JpaRepository<Lavozim, Integer> {
    List<Lavozim> findByLavozimlar(Lavozimlar lavozimlar);
}

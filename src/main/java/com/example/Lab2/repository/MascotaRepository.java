package com.example.Lab2.repository;

import com.example.Lab2.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    //Se realiza para encontrar las busquedas dependiendo de lo que ponga el usuario
    List<Mascota> findByNombreContainingIgnoreCase(String nombre);
    List<Mascota> findByEspecieContainingIgnoreCase(String especie);
    List<Mascota> findByEstado(Boolean estado);


}

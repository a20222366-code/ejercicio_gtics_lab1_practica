package com.example.Lab2.repository;

import com.example.Lab2.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {

    //Se realiza para encontrar las busquedas dependiendo de lo que ponga el usuario
    List<Mascota> findByNombreContainingIgnoreCase(String nombre);
    List<Mascota> findByEspecieContainingIgnoreCase(String especie);
    List<Mascota> findByEstado(Boolean estado);

    //Query para editar mascota
    @Transactional
    @Modifying
    @Query("""
    UPDATE Mascota m
    SET m.nombre = :nombre,
        m.especie = :especie,
        m.raza = :raza,
        m.edad = :edad,
        m.nombreDueno = :nombreDueno,
        m.telefono = :telefono,
        m.estado = :estado
    WHERE m.id = :id
""")
    void actualizarMascota(
            @Param("id") Long id,
            @Param("nombre") String nombre,
            @Param("especie") String especie,
            @Param("raza") String raza,
            @Param("edad") Integer edad,
            @Param("nombreDueno") String nombreDueno,
            @Param("telefono") String telefono,
            @Param("estado") Boolean estado
    );


}

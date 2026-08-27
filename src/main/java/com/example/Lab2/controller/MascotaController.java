package com.example.Lab2.controller;

import com.example.Lab2.entity.Mascota;
import com.example.Lab2.repository.MascotaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MascotaController {

    private final MascotaRepository mascotaRepository;

    public MascotaController(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    @GetMapping("/mascotas")
    public String listarMascotas(
            @RequestParam(required = false) String criterio,
            @RequestParam(required = false) String valor,
            Model model) {
        //Critero es el parametro de las opciones: buscar por nombre, edad, estado
        //Valor es lo que ponemos en el buscador

        List<Mascota> mascotas;

        if (criterio == null || valor == null || valor.trim().isEmpty()) {

            mascotas = mascotaRepository.findAll();

        } else {

            switch (criterio) {

                case "nombre":
                    mascotas = mascotaRepository.findByNombreContainingIgnoreCase(valor);
                    break;

                case "especie":
                    mascotas = mascotaRepository.findByEspecieContainingIgnoreCase(valor);
                    break;

                case "estado":
                    Boolean estado = valor.equalsIgnoreCase("activo");
                    mascotas = mascotaRepository.findByEstado(estado);
                    break;

                default:
                    mascotas = mascotaRepository.findAll();
                    break;
            }
        }

        model.addAttribute("mascotas", mascotas);
        model.addAttribute("criterio", criterio);
        model.addAttribute("valor", valor);

        return "mascotas";
    }
}
package com.example.Lab2.controller;

import com.example.Lab2.entity.Mascota;
import com.example.Lab2.repository.MascotaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MascotaController {

    private final MascotaRepository mascotaRepository;

    public MascotaController(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    @GetMapping("/mascotas")
    public String listarMascotas(Model model) {

        //Suficiente para el listado
        List<Mascota> mascotas = mascotaRepository.findAll();

        model.addAttribute("mascotas", mascotas);

        return "mascotas";
    }
}

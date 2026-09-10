package com.example.Lab2.controller;

import com.example.Lab2.entity.Mascota;
import com.example.Lab2.repository.MascotaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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

    //Mostrar formulario
    @GetMapping("/mascotas/nueva")
    public String mostrarFormulario(Model model) {

        model.addAttribute("mascota", new Mascota());

        return "nueva-mascota";
    }

    //Guardamos mascota
    @PostMapping("/mascotas/guardar")
    public String guardarMascota(@ModelAttribute Mascota mascota) {

        //@ModelAttribute Mascota mascota significa: Toma los datos que vienen de la petición y mételos dentro de un objeto Mascota

        mascotaRepository.save(mascota);


        //Después de guardar la mascota mandamos nuevamente a /mascotas
        return "redirect:/mascotas";
    }

    //Mostramos formulario de edición
    @GetMapping("/mascotas/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {

        Mascota mascota = mascotaRepository.findById(id).orElse(null);

        model.addAttribute("mascota", mascota);

        return "editar-mascota";
    }
    
    //Actualizamos los datos de mascota
    @PostMapping("/mascotas/actualizar")
    public String actualizarMascota(@ModelAttribute Mascota mascota) {

        mascotaRepository.actualizarMascota(
                mascota.getId(),
                mascota.getNombre(),
                mascota.getEspecie(),
                mascota.getRaza(),
                mascota.getEdad(),
                mascota.getNombreDueno(),
                mascota.getTelefono(),
                mascota.getEstado()
        );

        return "redirect:/mascotas";
    }







}
package es.entreVarales.controller;

import es.entreVarales.model.User;
import es.entreVarales.model.Costalero;
import es.entreVarales.repository.UserRepository;
import es.entreVarales.service.CostaleroService;
import es.entreVarales.service.PasoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;





@Controller
@RequestMapping("/costaleros")
public class CostaleroController {

    @Autowired
    private CostaleroService costaleroService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasoService pasoService;
    
    
    @GetMapping("/inicio")
    public String costaleroInicio() {
        return "indexCostalero";
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("costaleros", costaleroService.findAll());
        return "listaCostaleros";
    }

//    @GetMapping("/mostrarFormularioCrear")
//    public String mostrarFormularioCrear(Model model) {
//        model.addAttribute("costalero", new Costalero());
//        model.addAttribute("pasos", pasoService.findAll()); // Obtiene los pasos
//        Costalero costalero = new Costalero();
//        return "formularioCrearCostalero"; // Cambiado a formularioCrearCostalero.html
//    }
    
    @GetMapping("/mostrarFormularioCrear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("costalero", new Costalero());
        model.addAttribute("pasos", pasoService.findAll()); // Obtiene los pasos
        List<User> usuariosCostaleros = userRepository.findAll().stream()
                .filter(u -> u.getRole() == User.Role.COSTALERO)
                .toList();

        model.addAttribute("usuarios", userRepository.findByRole(User.Role.COSTALERO));
 // Agrega los usuarios

        return "formularioCrearCostalero";
    }


    @PostMapping("/crear")
    public String crear(@ModelAttribute Costalero costalero, Model model) {
        costaleroService.save(costalero);
        model.addAttribute("success", "Costalero creado correctamente.");
        return "redirect:/costaleros";
    }
    
    
   

    @GetMapping("/editar")
    public String mostrarFormularioEditar(@RequestParam("dni") String dni, Model model) {
        Costalero costalero = costaleroService.findById(dni).orElse(null);
        if (costalero == null) {
            model.addAttribute("error", "Costalero no encontrado.");
            return "redirect:/costaleros";
        }
        model.addAttribute("costalero", costalero);
        model.addAttribute("pasos", pasoService.findAll());
        model.addAttribute("usuarios", userRepository.findAll()); // <- esto falta
        return "formularioEditarCostalero";
    }


    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Costalero costalero, Model model) {
        costaleroService.update(costalero.getDniCostalero(), costalero);
        model.addAttribute("success", "Costalero actualizado.");
        return "redirect:/costaleros";
    }

//    @GetMapping("/eliminar")
//    public String eliminar(@RequestParam("dni") String dni, Model model) {
//        costaleroService.deleteById(dni);
//        model.addAttribute("success", "Costalero eliminado.");
//        return "redirect:/costaleros";
//    }
    @GetMapping("/eliminar")
    public String eliminar(@RequestParam("dni") String dni, Model model) {
        costaleroService.deleteById(dni);
        model.addAttribute("success", "Costalero eliminado.");
        return "redirect:/costaleros";
    }


    @GetMapping("/mostrarFormularioBusqueda")
    public String mostrarFormularioBusqueda() {
        return "buscarCostalero";
    }

    @GetMapping("/buscar")
    public String buscarPorDni(@RequestParam("dni") String dni, Model model) {
        Costalero costalero = costaleroService.findById(dni).orElse(null);
        if (costalero == null) {
            model.addAttribute("error", "Costalero no encontrado.");
        } else {
            model.addAttribute("costalero", costalero);
        }
        return "verCostalero";
    }
}

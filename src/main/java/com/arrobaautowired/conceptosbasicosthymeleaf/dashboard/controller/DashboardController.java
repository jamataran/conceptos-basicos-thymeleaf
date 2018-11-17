package com.arrobaautowired.conceptosbasicosthymeleaf.dashboard.controller;

import com.arrobaautowired.conceptosbasicosthymeleaf.dashboard.domain.DashboardDTO;
import com.arrobaautowired.conceptosbasicosthymeleaf.dashboard.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador de la página principal de la aplicación
 *
 * @author jamataran@gmail.com
 */
@Controller
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/") // Rutas que aceptará este método
    public String index(
            @RequestParam(name = "parametro", required = false) String param, // Parámetros.
            Model model) { // Spring inyectará aqui un modelo para trabajar con él.

        model.addAttribute("DTO", dashboardService.getDashboard().orElse(DashboardDTO.builder().build()));

        // Ruta por defecto. Spring añadirá prefijo (src/main/resources/templates por defecto) y Sufijo (.html) por defecto.
        return "index";

    }

}

package com.arrobaautowired.conceptosbasicosthymeleaf.categoria.controller;

import com.arrobaautowired.conceptosbasicosthymeleaf.categoria.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
        return "categoria/categoria";
    }


}
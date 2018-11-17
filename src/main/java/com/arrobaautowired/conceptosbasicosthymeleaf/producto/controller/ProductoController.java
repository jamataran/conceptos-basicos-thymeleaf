package com.arrobaautowired.conceptosbasicosthymeleaf.producto.controller;

import com.arrobaautowired.conceptosbasicosthymeleaf.categoria.service.CategoriaService;
import com.arrobaautowired.conceptosbasicosthymeleaf.producto.dao.Producto;
import com.arrobaautowired.conceptosbasicosthymeleaf.producto.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    private final CategoriaService categoriaService;


    public ProductoController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String index(@RequestParam(name = "idCategoria", required = false) Long criteriaCategoria, Model model) {
        model.addAttribute("productos", productoService.findAll());
        model.addAttribute("categorias", categoriaService.findAll());
        return "productos/lista";
    }

    @GetMapping("/detalle/{id}")
    public String getProducto(@PathVariable("id") Long id, Model model) {
        model.addAttribute("producto", productoService.findById(id));
        return "productos/detalle";
    }

    @GetMapping("/borrar/{id}") // Esto es una chapuza ... pero bueno
    public String delete(@PathVariable("id") Long id) {
        productoService.delete(Producto.builder().id(id).build());
        return "redirect:/productos";
    }

    /**
     * Método que añadirá o editará un producto
     *
     * @param model Command Object para el formulario.
     * @return Dirección de la plantilla
     */
    @GetMapping("/editar/{id}")
    public String upsertProducto(Model model, @PathVariable(name = "id", required = false) Long id) {
        Producto p = productoService.findById(id);
        if (p != null)
            model.addAttribute("producto", productoService.findById(id));
        else
            model.addAttribute("producto", Producto.builder().build());

        return "productos/upsert";

    }


    /**
     * Método que maneja el guardado de un formulario (procesa los submit de los formularios)
     *
     * @param producto Elemento del cuerpo del <code>POST</code>
     * @param model    Command Object del formulario.
     * @return Redirección a la lista.
     */
    @PostMapping("/save")
    public String handleSubmitForm(@Valid Producto producto, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            productoService.save(producto);
            return "redirect:/productos";
        } else {
            return "/productos/upsert";
        }
    }

}

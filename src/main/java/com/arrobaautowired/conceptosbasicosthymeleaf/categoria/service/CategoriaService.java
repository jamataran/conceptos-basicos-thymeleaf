package com.arrobaautowired.conceptosbasicosthymeleaf.categoria.service;

import com.arrobaautowired.conceptosbasicosthymeleaf.categoria.dao.Categoria;
import com.arrobaautowired.conceptosbasicosthymeleaf.categoria.dao.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private CategoriaRepository repositorio;

    public CategoriaService(CategoriaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Categoria> findAll() {
        return repositorio.findAll();
    }

    public List<Categoria> findDestacadas() {
        return repositorio.findDestacadas();
    }

    public Categoria save(Categoria categoria) {
        return repositorio.save(categoria);
    }

    public Categoria findById(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    public Categoria delete(Categoria categoria) {
        Categoria result = findById(categoria.getId());
        repositorio.delete(result);
        return result;
    }
}

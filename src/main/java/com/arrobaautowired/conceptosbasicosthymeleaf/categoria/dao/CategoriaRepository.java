package com.arrobaautowired.conceptosbasicosthymeleaf.categoria.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("select c from Categoria c where c.destacada = true")
    List<Categoria> findDestacadas();

}

package com.arrobaautowired.conceptosbasicosthymeleaf.producto.dao;

import com.arrobaautowired.conceptosbasicosthymeleaf.categoria.dao.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByCategoria(Categoria categoria);

    @Query("select p.id from Producto p")
    List<Long> obtenerIds();

    @Query("select p from Producto p where p.categoria.id = ?1")
    List<Producto> findByCategoriaId(Long categoriaId);

    @Query("select count(p) from Producto p where p.categoria = ?1")
    int findNumProductosByCategoria(Categoria categoria);
}

package com.arrobaautowired.conceptosbasicosthymeleaf.producto.dao;

import com.arrobaautowired.conceptosbasicosthymeleaf.categoria.dao.Categoria;
import com.arrobaautowired.conceptosbasicosthymeleaf.puntuacion.dao.Puntuacion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String nombre;

    @Lob
    private String descripcion;

    private float pvp;

    private float descuento;

    private String imagen;

    @ManyToOne
    private Categoria categoria;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Puntuacion> puntuaciones = new HashSet<Puntuacion>();

    /**
     * Métodos helper
     */
    public void addPuntuacion(Puntuacion puntuacion) {
        this.puntuaciones.add(puntuacion);
        puntuacion.setProducto(this);
    }


    public double getPuntuacionMedia() {
        if (this.puntuaciones.isEmpty())
            return 0;
        else
            return this.puntuaciones.stream()
                    .mapToInt(Puntuacion::getPuntuacion)
                    .average()
                    .getAsDouble();
    }

    public double getNumeroTotalPuntuaciones() {
        return this.puntuaciones.size();
    }

}

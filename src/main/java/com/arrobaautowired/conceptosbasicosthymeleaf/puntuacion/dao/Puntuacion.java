package com.arrobaautowired.conceptosbasicosthymeleaf.puntuacion.dao;

import com.arrobaautowired.conceptosbasicosthymeleaf.producto.dao.Producto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Puntuacion {

    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    private Date fecha;

    private int puntuacion;

    @ManyToOne
    private Producto producto;

}

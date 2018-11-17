package com.arrobaautowired.conceptosbasicosthymeleaf.dashboard.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class DashboardDTO {

    private String datoTexto;

    private Long datoNumerico;

    private LocalDateTime dateTime;

    private List<String> lista;

    private UsuarioDTO usuarioDTO;

    private EjemploOperadorDTO ejemploOperadorDTO;

    private List<UsuarioDTO> listaCompleja;

}

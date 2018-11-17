package com.arrobaautowired.conceptosbasicosthymeleaf.dashboard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
public class UsuarioDTO {

    private Long idUsuario;

    private String nombre;

    private String apellidos;

    private String mail;

    private String username;

}

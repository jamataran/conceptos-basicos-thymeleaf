package com.arrobaautowired.conceptosbasicosthymeleaf.dashboard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
public class EjemploOperadorDTO {

    private Long numeroPrimario;

    private Long numeroSecundario;

    private Boolean booleanoPrimario;

    private Boolean booleanoSecundario;
}

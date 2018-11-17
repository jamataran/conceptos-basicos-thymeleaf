package com.arrobaautowired.conceptosbasicosthymeleaf.dashboard.service;

import com.arrobaautowired.conceptosbasicosthymeleaf.dashboard.domain.DashboardDTO;
import com.arrobaautowired.conceptosbasicosthymeleaf.dashboard.domain.EjemploOperadorDTO;
import com.arrobaautowired.conceptosbasicosthymeleaf.dashboard.domain.UsuarioDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class DashboardServiceImpl implements DashboardService {

    private final static Random MOCKEADOR = new Random();

    /**
     * Devuelve un DTO para el 'Dashboard' del ejemplo
     *
     * @return Optional<Dashboard>
     */
    @Override
    public Optional<DashboardDTO> getDashboard() {
        return Optional
                .of(DashboardDTO
                        .builder()
                        .dateTime(LocalDateTime.now())
                        .datoNumerico(1105L)
                        .datoTexto("Proyecto de ejemplo de Thymeleaf.")
                        .usuarioDTO(UsuarioDTO
                                .builder()
                                .apellidos("Matarán Barrio")
                                .nombre("José Antonio")
                                .username("jamataran")
                                .build())
                        .ejemploOperadorDTO(EjemploOperadorDTO
                                .builder()
                                .numeroPrimario(MOCKEADOR.nextLong())
                                .numeroSecundario(MOCKEADOR.nextLong())
                                .booleanoPrimario(MOCKEADOR.nextBoolean())
                                .booleanoSecundario(MOCKEADOR.nextBoolean())
                                .build())
                        .lista(MOCKEADOR.nextBoolean() ? Arrays.asList("Elemento1", "Elemento2", "Elemento3") : new ArrayList<>())
                        .listaCompleja(IntStream
                                .range(0, MOCKEADOR.nextInt(100))
                                .mapToObj(i -> {
                                    UsuarioDTO usuarioDTO = usuarioDTORandom();
                                    usuarioDTO.setIdUsuario(new Long(i));
                                    return usuarioDTO;
                                })
                                .collect(Collectors.toList()))
                        .build());
    }

    private UsuarioDTO usuarioDTORandom() {
        return UsuarioDTO.builder()
                .nombre("NOMBRE ".concat(getRandomString()))
                .apellidos("APELLIDOS ".concat(getRandomString()))
                .username(getRandomString())
                .build();
    }

    private String getRandomString() {
        return UUID.randomUUID().toString();
    }

}

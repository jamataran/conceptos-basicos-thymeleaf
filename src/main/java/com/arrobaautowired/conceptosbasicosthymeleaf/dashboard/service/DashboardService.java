package com.arrobaautowired.conceptosbasicosthymeleaf.dashboard.service;

import com.arrobaautowired.conceptosbasicosthymeleaf.dashboard.domain.DashboardDTO;

import java.util.Optional;

public interface DashboardService {

    Optional<DashboardDTO> getDashboard();

}

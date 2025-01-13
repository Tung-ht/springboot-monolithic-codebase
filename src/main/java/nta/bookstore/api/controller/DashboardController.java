package nta.bookstore.api.controller;

import lombok.RequiredArgsConstructor;
import nta.bookstore.api.dto.AppResponse;
import nta.bookstore.api.dto.DashboardStatisticDTO;
import nta.bookstore.api.service.impl.StatisticService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/admin/dashboard")
public class DashboardController {

    private final StatisticService statisticService;

    @GetMapping("/statistic")
    public AppResponse<DashboardStatisticDTO> getShopStatistic() {
        return AppResponse.ok(statisticService.getShopStatistic());
    }
}

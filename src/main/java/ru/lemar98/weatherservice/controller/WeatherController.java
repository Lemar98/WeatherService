package ru.lemar98.weatherservice.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.lemar98.weatherservice.dto.WeatherForecastResponse;
import ru.lemar98.weatherservice.service.ForecastService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/weather")
@CrossOrigin(origins = "*")
public class WeatherController {
    private final ForecastService forecastService;

    @Autowired
    public WeatherController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping("/forecast")
    public List<WeatherForecastResponse> getWeatherForecast(@Valid @RequestParam int forecastDays) {
        return forecastService.getForecast(forecastDays);
    }
}

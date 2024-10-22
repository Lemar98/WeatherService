package ru.lemar98.weatherservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lemar98.weatherservice.dto.WeatherForecastResponse;
import ru.lemar98.weatherservice.dto.mapper.WeatherForecastResponseMapper;
import ru.lemar98.weatherservice.model.WeatherDay;

import java.util.List;

@Service
public class ForecastService {
    private final WeatherService weatherService;

    @Autowired
    public ForecastService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public List<WeatherForecastResponse> getForecast(int forecastDays) {
        List<WeatherDay> weatherDays = weatherService.getWeatherForDays(forecastDays);
        return weatherDays.stream().map(this::getWeatherForecastResponse).toList();
    }

    private WeatherForecastResponse getWeatherForecastResponse(WeatherDay weatherDay) {
        String warning = generateWarningOrNull(weatherDay);
        return WeatherForecastResponseMapper.toDTO(weatherDay, warning);
    }

    private String generateWarningOrNull(WeatherDay weatherDay) {
        if (weatherDay.avgDailyTemperature() < 5)
            return "Заморозки";

        boolean hasAbnormalHeat = weatherDay.getHourlyTemperatures().stream()
                .anyMatch(hour -> hour.getTemperature() > 35);
        if (hasAbnormalHeat)
            return "Аномальная жара";

        boolean hasAbnormalCold = weatherDay.getHourlyTemperatures().stream()
                .anyMatch(hour -> hour.getTemperature() < 30);
        if (hasAbnormalCold)
            return "Аномальный холод";

        return null;
    }
}

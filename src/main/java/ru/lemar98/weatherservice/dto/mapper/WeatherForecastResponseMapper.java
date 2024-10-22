package ru.lemar98.weatherservice.dto.mapper;

import ru.lemar98.weatherservice.dto.WeatherForecastResponse;
import ru.lemar98.weatherservice.model.WeatherDay;

import java.util.List;

public class WeatherForecastResponseMapper {
    private WeatherForecastResponseMapper() throws IllegalAccessException {
        throw new IllegalAccessException("Utility class!");
    }

    public static WeatherForecastResponse toDTO(WeatherDay weatherDay, String warning) {
        List<WeatherForecastResponse.TemperatureResponse> temperatureResponses = weatherDay.getHourlyTemperatures()
                .stream()
                .map(WeatherForecastResponse.TemperatureResponse::of)
                .toList();
        return new WeatherForecastResponse(
                weatherDay.getDate(),
                temperatureResponses,
                warning
        );
    }
}

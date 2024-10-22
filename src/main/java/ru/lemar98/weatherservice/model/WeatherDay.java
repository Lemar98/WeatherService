package ru.lemar98.weatherservice.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class WeatherDay {
    private final LocalDate date;
    private final List<WeatherHour> hourlyTemperatures;

    public float avgDailyTemperature() {
        return (float) hourlyTemperatures.stream()
                .mapToDouble(WeatherHour::getTemperature)
                .average()
                .orElse(0.0F);
    }
}

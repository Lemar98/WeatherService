package ru.lemar98.weatherservice.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
public class WeatherHour {
    private final LocalTime time;
    private final float temperature;
}

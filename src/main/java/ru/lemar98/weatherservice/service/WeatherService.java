package ru.lemar98.weatherservice.service;

import org.springframework.stereotype.Service;
import ru.lemar98.weatherservice.model.WeatherDay;
import ru.lemar98.weatherservice.model.WeatherHour;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class WeatherService {
    public List<WeatherDay> getWeatherForDays(int days) {
        return generateDays(days);
    }

    private List<WeatherDay> generateDays(int days) {
        List<WeatherDay> weatherDays = new ArrayList<>();
        for (int day = 1; day <= days; day++) {
            List<WeatherHour> weatherHours = generateHours();
            weatherDays.add(
                    WeatherDay.builder()
                            .date(LocalDate.now().plusDays(day))
                            .hourlyTemperatures(weatherHours)
                            .build()
            );
        }
        return weatherDays;
    }

    private List<WeatherHour> generateHours() {
        List<WeatherHour> weatherHours = new ArrayList<>();
        for (int hour = 0; hour < 23; hour++) {
            weatherHours.add(
                    WeatherHour.builder()
                            .time(LocalTime.of(hour, 0))
                            .temperature(generateTemperature())
                            .build()
            );
        }
        return weatherHours;
    }

    private float generateTemperature() {
        float temperature = ThreadLocalRandom.current().nextFloat(-30.0F, 41.0F);
        return Math.round(temperature * 10.0F) / 10.0F;
    }
}

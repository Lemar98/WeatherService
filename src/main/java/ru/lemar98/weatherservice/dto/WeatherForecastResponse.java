package ru.lemar98.weatherservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lemar98.weatherservice.model.WeatherHour;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherForecastResponse {
    private LocalDate date;
    private List<TemperatureResponse> temperatures;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String warning;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TemperatureResponse {
        private LocalTime hour;
        private float temperature;

        public static TemperatureResponse of(WeatherHour weatherHour) {
            return new TemperatureResponse(weatherHour.getTime(), weatherHour.getTemperature());
        }
    }
}

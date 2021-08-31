package cl.previred.prueba.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseData {
    private String id;
    @JsonProperty("fechaCreacion")
    private LocalDate startDate;
    @JsonProperty("fechaFin")
    private LocalDate endDate;
    @JsonProperty("fechas")
    private List<LocalDate> dates;
    @JsonProperty("fechasFaltantes")
    private List<LocalDate> missingDates;
}

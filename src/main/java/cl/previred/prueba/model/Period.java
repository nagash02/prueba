package cl.previred.prueba.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Period {
    @JsonProperty("id")
    private String id;

    @JsonProperty("fechaCreacion")
    private LocalDate startDate;

    @JsonProperty("fechaFin")
    private LocalDate endDate;

    @JsonProperty("fechas")
    private List<LocalDate> dates;
}

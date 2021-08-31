package cl.previred.prueba.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

import cl.previred.prueba.model.Period;

@FeignClient(name = "GDD", url = "${url.value}")
public interface GddClient {
    @PostMapping(value = "/gdd", produces = MediaType.APPLICATION_JSON_VALUE)
    Period getPeriodos();
}

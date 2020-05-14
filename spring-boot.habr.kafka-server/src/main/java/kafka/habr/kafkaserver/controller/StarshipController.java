package kafka.habr.kafkaserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kafka.habr.kafkaserver.dto.StarshipDto;
import kafka.habr.kafkaserver.service.StarshipService;

@RestController
@RequestMapping("/starship")
public class StarshipController {

    private final StarshipService service;

    @Autowired
    public StarshipController(StarshipService service) {
        this.service = service;
    }

    @PostMapping
    public void send(@RequestBody StarshipDto dto) {
        service.send(dto);
    }
}
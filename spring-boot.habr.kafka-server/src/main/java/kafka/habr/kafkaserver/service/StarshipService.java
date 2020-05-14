package kafka.habr.kafkaserver.service;

import kafka.habr.kafkaserver.dto.StarshipDto;

public interface StarshipService {

    StarshipDto save(StarshipDto dto);

    void send(StarshipDto dto);

    void consume(StarshipDto dto);
}

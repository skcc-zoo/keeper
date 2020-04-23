package com.example.keeper;

import com.example.keeper.event.CommonEvent;
import com.example.keeper.event.KeeperDispatched;
import com.example.keeper.event.PopulationChanged;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
public class KafkaListener {

    final Integer MAX_POPULATION = 10;

    @Autowired
    private KeeperRepository keeperRepository;

    @StreamListener(Processor.INPUT)
    public void onEvent(@Payload String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        CommonEvent event = null;

        try {
            event = objectMapper.readValue(message, CommonEvent.class);

            if (event.getEventType().equals("PopulationChanged")) {
                PopulationChanged populationChanged = new PopulationChanged((HashMap<String, Object>) event.getContent());

                if (populationChanged.getPopulation() >= MAX_POPULATION) {
                    Iterable<Keeper> keepers = keeperRepository.findAll();
                    for (Keeper keeper : keepers
                    ) {
                        if (keeper.getSpace() == null) {
                            keeper.setSpace(populationChanged.getSpaceId());
                            keeperRepository.save(keeper);
                            break;
                        }
                    }
                } else if (populationChanged.getPopulation() == MAX_POPULATION - 1) {
                    Iterable<Keeper> keepers = keeperRepository.findBySpace(populationChanged.getSpaceId());
                    for (Keeper keeper : keepers) {
                        keeper.setSpace(null);
                        keeperRepository.save(keeper);
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

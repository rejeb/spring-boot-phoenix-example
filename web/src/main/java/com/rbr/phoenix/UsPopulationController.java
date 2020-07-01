package com.rbr.phoenix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/populations")
public class UsPopulationController {
    @Autowired
    private UsPopulationRepository usPopulationRepository;

    @GetMapping
    public ResponseEntity<List<UsPopulation>> populations() {
        return ResponseEntity.ok(usPopulationRepository.populations());
    }

    @PostMapping
    public ResponseEntity<Void> addPopulation(@RequestBody UsPopulation usPopulation) {
        usPopulationRepository.addPopulation(usPopulation);
        return ResponseEntity.ok().build();
    }

    @GetMapping("events")
    public ResponseEntity<List<PopulationEvent>> populationsEvent() {
        return ResponseEntity.ok(usPopulationRepository.populationsEvents());
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePopulation(@RequestBody UsPopulation usPopulation){
        usPopulationRepository.deletePopulation(usPopulation);
        return ResponseEntity.ok().build();
    }
}

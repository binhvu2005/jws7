package com.example.ss7.controller;

import com.example.ss7.entity.Seed;
import com.example.ss7.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seeds")
public class SeedController {

    @Autowired
    private SeedService seedService;

    // GET /seeds
    @GetMapping
    public List<Seed> getAllSeeds() {
        return seedService.getAllSeeds();
    }

    // GET /seeds/{id}
    @GetMapping("/{id}")
    public Seed getSeedById(@PathVariable Long id) {
        return seedService.getSeedById(id);
    }

    // POST /seeds
    @PostMapping
    public Seed addSeed(@RequestBody Seed seed) {
        return seedService.addSeed(seed);
    }

    // PUT /seeds/{id}
    @PutMapping("/{id}")
    public Seed updateSeed(@PathVariable Long id, @RequestBody Seed seed) {
        return seedService.updateSeed(id, seed);
    }

    // DELETE /seeds/{id}
    @DeleteMapping("/{id}")
    public void deleteSeed(@PathVariable Long id) {
        seedService.deleteSeed(id);
    }
}

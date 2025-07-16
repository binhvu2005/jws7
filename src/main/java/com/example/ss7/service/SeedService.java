package com.example.ss7.service;

import com.example.ss7.entity.Seed;
import com.example.ss7.repository.SeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeedService {

    @Autowired
    private SeedRepository seedRepository;

    public List<Seed> getAllSeeds() {
        return seedRepository.findAll();
    }

    public Seed getSeedById(Long id) {
        return seedRepository.findById(id).orElse(null);
    }

    public Seed addSeed(Seed seed) {
        return seedRepository.save(seed);
    }

    public Seed updateSeed(Long id, Seed updatedSeed) {
        Optional<Seed> optionalSeed = seedRepository.findById(id);
        if (optionalSeed.isPresent()) {
            Seed seed = optionalSeed.get();
            seed.setSeedName(updatedSeed.getSeedName());
            seed.setQuantity(updatedSeed.getQuantity());
            return seedRepository.save(seed);
        }
        return null;
    }

    public void deleteSeed(Long id) {
        seedRepository.deleteById(id);
    }
}

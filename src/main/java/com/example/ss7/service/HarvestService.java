package com.example.ss7.service;

import com.example.ss7.entity.Harvest;
import com.example.ss7.repository.HarvestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HarvestService {

    @Autowired
    private HarvestRepository harvestRepo;

    public List<Harvest> getAllHarvests() {
        return harvestRepo.findAll();
    }

    public Harvest getHarvestById(Long id) {
        return harvestRepo.findById(id).orElse(null);
    }

    public Harvest addHarvest(Harvest harvest) {
        if (harvest.getCreatedAt() == null) {
            harvest.setCreatedAt(LocalDateTime.now());
        }
        return harvestRepo.save(harvest);
    }
}

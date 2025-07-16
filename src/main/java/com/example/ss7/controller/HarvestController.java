package com.example.ss7.controller;

import com.example.ss7.entity.Harvest;
import com.example.ss7.service.HarvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/harvests")
public class HarvestController {

    @Autowired
    private HarvestService harvestService;

    // Lấy danh sách thu hoạch
    @GetMapping
    public List<Harvest> getAll() {
        return harvestService.getAllHarvests();
    }

    // Lấy thu hoạch theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Harvest h = harvestService.getHarvestById(id);
        if (h == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(h);
    }

    // Thêm thông tin thu hoạch
    @PostMapping
    public ResponseEntity<Harvest> add(@RequestBody Harvest harvest) {
        return ResponseEntity.ok(harvestService.addHarvest(harvest));
    }
}

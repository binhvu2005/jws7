package com.example.ss7.controller;

import com.example.ss7.entity.Worker;
import com.example.ss7.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workers")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    // Lấy danh sách công nhân
    @GetMapping
    public List<Worker> getAll() {
        return workerService.getAllWorkers();
    }

    // Lấy công nhân theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Worker w = workerService.getWorkerById(id);
        if (w == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(w);
    }

    // Thêm công nhân mới
    @PostMapping
    public ResponseEntity<Worker> add(@RequestBody Worker worker) {
        return ResponseEntity.ok(workerService.addWorker(worker));
    }

    // Cập nhật công nhân
    @PutMapping("/{id}")
    public ResponseEntity<Worker> update(@PathVariable Long id, @RequestBody Worker worker) {
        return ResponseEntity.ok(workerService.updateWorker(id, worker));
    }

    // Xóa công nhân
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        workerService.deleteWorker(id);
        return ResponseEntity.ok().build();
    }
}

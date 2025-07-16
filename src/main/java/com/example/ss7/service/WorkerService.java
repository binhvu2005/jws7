package com.example.ss7.service;

import com.example.ss7.entity.Worker;
import com.example.ss7.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepo;

    public List<Worker> getAllWorkers() {
        return workerRepo.findAll();
    }

    public Worker getWorkerById(Long id) {
        return workerRepo.findById(id).orElse(null);
    }

    public Worker addWorker(Worker worker) {
        return workerRepo.save(worker);
    }

    public Worker updateWorker(Long id, Worker newData) {
        Worker old = workerRepo.findById(id).orElseThrow();
        old.setFullName(newData.getFullName());
        old.setPhone(newData.getPhone());
        old.setAddress(newData.getAddress());
        old.setSalary(newData.getSalary());
        return workerRepo.save(old);
    }

    public void deleteWorker(Long id) {
        workerRepo.deleteById(id);
    }
}

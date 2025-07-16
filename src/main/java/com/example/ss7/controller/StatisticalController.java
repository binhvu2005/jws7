package com.example.ss7.controller;

import com.example.ss7.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class StatisticalController {

    @Autowired
    private StatisticalService statisticalService;

    // 1. Số lượng hạt giống còn trong kho
    @GetMapping("/remaining-seeds")
    public ResponseEntity<Integer> countRemainingSeeds() {
        return ResponseEntity.ok(statisticalService.countRemainingSeeds());
    }

    // 2. Tổng tiền thu hoạch trong tháng
    @GetMapping("/harvest-money")
    public ResponseEntity<Double> totalHarvestMoneyThisMonth() {
        return ResponseEntity.ok(statisticalService.totalHarvestMoneyThisMonth());
    }

    // 3. Tổng số tiền chi trong tháng
    @GetMapping("/payment-slips")
    public ResponseEntity<Double> totalPaymentSlipsThisMonth() {
        return ResponseEntity.ok(statisticalService.totalPaymentSlipsThisMonth());
    }

    // 4. Lãi/lỗ từng tháng trong năm
    @GetMapping("/profit-loss")
    public ResponseEntity<Map<String, Double>> profitLossOverYear() {
        return ResponseEntity.ok(statisticalService.profitLossOverYear());
    }

    // 5. Tổng tiền phải trả công nhân
    @GetMapping("/worker-salary")
    public ResponseEntity<Double> totalWorkerSalary() {
        return ResponseEntity.ok(statisticalService.totalWorkerSalary());
    }
}

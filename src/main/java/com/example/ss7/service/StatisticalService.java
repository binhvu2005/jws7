package com.example.ss7.service;

import com.example.ss7.entity.Harvest;
import com.example.ss7.entity.PaymentSlip;
import com.example.ss7.entity.Seed;
import com.example.ss7.entity.Worker;
import com.example.ss7.repository.HarvestRepository;
import com.example.ss7.repository.PaymentSlipRepository;
import com.example.ss7.repository.SeedRepository;
import com.example.ss7.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.time.YearMonth;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class StatisticalService {

    @Autowired
    private SeedRepository seedRepository;

    @Autowired
    private HarvestRepository harvestRepository;

    @Autowired
    private PaymentSlipRepository paymentSlipRepository;

    @Autowired
    private WorkerRepository workerRepository;

    // 1. Thống kê số lượng hạt giống còn trong kho
    public int countRemainingSeeds() {
        return seedRepository.findAll().stream()
                .mapToInt(Seed::getQuantity)
                .sum();
    }

    // 2. Thống kê tổng tiền thu hoạch trong tháng hiện tại
    public double totalHarvestMoneyThisMonth() {
        YearMonth now = YearMonth.now();
        return harvestRepository.findAll().stream()
                .filter(h -> h.getCreatedAt() != null &&
                        YearMonth.from(h.getCreatedAt()).equals(now))
                .mapToDouble(Harvest::getTotalMoney)
                .sum();
    }

    // 3. Thống kê tổng số tiền phiếu chi trong tháng
    public double totalPaymentSlipsThisMonth() {
        YearMonth now = YearMonth.now();
        return paymentSlipRepository.findAll().stream()
                .filter(p -> p.getCreatedAt() != null &&
                        YearMonth.from(p.getCreatedAt()).equals(now))
                .mapToDouble(PaymentSlip::getMoney)
                .sum();
    }

    // 4. Thống kê lãi/lỗ của 12 tháng trong năm hiện tại
    public Map<String, Double> profitLossOverYear() {
        Map<String, Double> result = new LinkedHashMap<>();
        Year year = Year.now();

        for (int month = 1; month <= 12; month++) {
            YearMonth ym = YearMonth.of(year.getValue(), month);

            double harvestTotal = harvestRepository.findAll().stream()
                    .filter(h -> h.getCreatedAt() != null &&
                            YearMonth.from(h.getCreatedAt()).equals(ym))
                    .mapToDouble(Harvest::getTotalMoney)
                    .sum();

            double paymentTotal = paymentSlipRepository.findAll().stream()
                    .filter(p -> p.getCreatedAt() != null &&
                            YearMonth.from(p.getCreatedAt()).equals(ym))
                    .mapToDouble(PaymentSlip::getMoney)
                    .sum();

            double profit = harvestTotal - paymentTotal;
            result.put(ym.getMonth().name(), profit);
        }

        return result;
    }

    // 5. Thống kê tổng lương công nhân hiện tại (theo tháng)
    public double totalWorkerSalary() {
        return workerRepository.findAll().stream()
                .mapToDouble(Worker::getSalary)
                .sum();
    }
}

package com.example.ss7.service;

import com.example.ss7.entity.PaymentSlip;
import com.example.ss7.repository.PaymentSlipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentSlipService {

    @Autowired
    private PaymentSlipRepository paymentSlipRepo;

    public List<PaymentSlip> getAllPaymentSlips() {
        return paymentSlipRepo.findAll();
    }

    public PaymentSlip getPaymentSlipById(Long id) {
        return paymentSlipRepo.findById(id).orElse(null);
    }

    public PaymentSlip addPaymentSlip(PaymentSlip paymentSlip) {
        if (paymentSlip.getCreatedAt() == null) {
            paymentSlip.setCreatedAt(LocalDateTime.now());
        }
        return paymentSlipRepo.save(paymentSlip);
    }
}

package com.example.ss7.controller;

import com.example.ss7.entity.PaymentSlip;
import com.example.ss7.service.PaymentSlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paymentslips")
public class PaymentSlipController {

    @Autowired
    private PaymentSlipService paymentSlipService;


    @GetMapping
    public List<PaymentSlip> getAll() {
        return paymentSlipService.getAllPaymentSlips();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        PaymentSlip slip = paymentSlipService.getPaymentSlipById(id);
        if (slip == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(slip);
    }


    @PostMapping
    public ResponseEntity<PaymentSlip> add(@RequestBody PaymentSlip paymentSlip) {
        return ResponseEntity.ok(paymentSlipService.addPaymentSlip(paymentSlip));
    }
}

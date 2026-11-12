package com.university.aoplab.service;

import com.university.aoplab.annotation.Auditable;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Auditable("PAYMENT_PROCESSING")

    public String processPayment(Long studentId, double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be positive");
        }

        if (amount > 100000) {
            throw new IllegalArgumentException("Payment amount exceeds the permitted limit");
        }

        return "Payment of " + amount +
                " successfully processed for student " + studentId;
    }
}
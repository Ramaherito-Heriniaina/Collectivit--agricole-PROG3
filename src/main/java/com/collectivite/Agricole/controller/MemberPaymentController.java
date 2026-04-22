package com.collectivite.Agricole.controller;

import com.collectivite.Agricole.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("/members/{memberId}/payments")
public class MemberPaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public <MemberPayment, CreateMemberPayment> ResponseEntity<?> createPayments(@PathVariable String memberId,
                                                                                 @RequestBody List payments) {
        // validation et création
        List created = paymentService.createPayments(memberId, payments);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}

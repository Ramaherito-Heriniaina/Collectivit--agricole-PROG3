package com.collectivite.Agricole.controller;


import com.collectivite.Agricole.exception.BusinessException;
import com.collectivite.Agricole.model.Account;
import com.collectivite.Agricole.model.MembershipFee;
import com.collectivite.Agricole.model.Payment;
import com.collectivite.Agricole.service.AccountService;
import com.collectivite.Agricole.service.ContributionService;
import com.collectivite.Agricole.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collectivities/{id}")
public class FinanceController {

    @Autowired
    private ContributionService contributionService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/contributions")
    public ResponseEntity<?> createContribution(@PathVariable String id,
                                                @RequestBody MembershipFee contribution) {
        try {
            MembershipFee created = contributionService.createContribution(id, contribution);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/payments")
    public ResponseEntity<?> recordPayment(@PathVariable String id,
                                           @RequestBody Payment payment) {
        try {
            Payment recorded = paymentService.recordPayment(id, payment);
            return ResponseEntity.status(HttpStatus.CREATED).body(recorded);
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/accounts")
    public ResponseEntity getAccounts(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/accounts")
    public ResponseEntity<?> addAccount(@PathVariable String id,
                                        @RequestBody Account account) {
        try {
            Account created = accountService.addAccount(id, account);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
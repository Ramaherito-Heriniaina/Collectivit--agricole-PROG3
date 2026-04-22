package com.collectivite.Agricole.controller;

import com.collectivite.Agricole.model.CollectivityTransaction;
import com.collectivite.Agricole.model.MembershipFee;
import com.collectivite.Agricole.service.MembershipFeeService;
import com.collectivite.Agricole.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/collectivities/{id}")
public class CollectivityController {

    @Autowired
    private MembershipFeeService feeService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/membershipFees")
    public ResponseEntity<?> createMembershipFees(@PathVariable String id,
                                                  @RequestBody List<MembershipFee> fees) {
        try {
            List<MembershipFee> created = feeService.createMembershipFees(id, fees);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/membershipFees")
    public ResponseEntity<List<MembershipFee>> getMembershipFees(@PathVariable String id) {
        return ResponseEntity.ok(feeService.getMembershipFees(id));
    }

    @GetMapping("/transactions")
    public ResponseEntity<?> getTransactions(@PathVariable String id,
                                             @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                             @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        try {
            List<CollectivityTransaction> transactions = transactionService.getTransactions(id, from, to);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
package com.collectivite.Agricole.controller;

import com.collectivite.Agricole.exception.BusinessException;
import com.collectivite.Agricole.model.*;
import com.collectivite.Agricole.repository.AccountRepository;
import com.collectivite.Agricole.repository.CollectivityRepository;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getCollectivityById(@PathVariable String id) {
        AccountRepository collectivityRepository = null;
        Account collectivity = collectivityRepository.findById(Long.valueOf(id))
                .orElseThrow();
        return ResponseEntity.ok(collectivity);
    }

    @GetMapping("/{id}/financialAccounts")
    public ResponseEntity<?> getFinancialAccounts(@PathVariable String id,
                                                  @RequestParam("at") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate at) {
        // Vérifier que la collectivité existe
        CollectivityRepository collectivityRepository = null;
        if (!collectivityRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Collectivité non trouvée");
        }
        Object accountService = null;
        Class<?> accounts = accountService.getClass();
        return ResponseEntity.ok(accounts);
    }
}
package com.collectivite.Agricole.service;

import com.collectivite.Agricole.exception.BusinessException;
import com.collectivite.Agricole.model.Account;
import com.collectivite.Agricole.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account addAccount(String collectivityId, Account account) {
        account.setEntityType("COLLECTIVITY");
        account.setEntityId(collectivityId);
        // Une seule caisse par collectivité
        if ("CAISSE".equals(account.getAccountType())) {
            if (accountRepository.findCaisseByEntity("COLLECTIVITY", collectivityId).isPresent()) {
                throw new BusinessException("Une seule caisse est autorisée par collectivité");
            }
        }
        account.setCurrentBalance(0.0);
        account.setLastUpdate(LocalDate.now());
        return accountRepository.save(account);
    }

    public void getAccounts(String id) {
        return ;
    }
}
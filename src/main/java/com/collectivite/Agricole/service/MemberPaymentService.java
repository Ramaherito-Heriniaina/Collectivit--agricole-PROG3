package com.collectivite.Agricole.service;

import com.collectivite.Agricole.exception.BusinessException;
import com.collectivite.Agricole.model.*;
import com.collectivite.Agricole.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MemberPaymentService {

    @Autowired
    private MemberPaymentRepository paymentRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MembershipFeeRepository feeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;


    public <CreateMemberPayment> List<MemberPayment> createPayments(String memberId, List<CreateMemberPayment> payments ) {
        // Vérifier que le membre existe
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException("Membre introuvable : " + memberId));

        String collectivityId = member.getCollectivityId();
        if (collectivityId == null) {
            throw new BusinessException("Ce membre n'appartient à aucune collectivité");
        }

        List<MemberPayment> createdPayments = new ArrayList<>();

        for (CreateMemberPayment dto : payments) {
            // Vérifier que la cotisation (membership fee) existe
            MembershipFee fee = feeRepository.findById(String.valueOf(dto.getClass()))
                    .orElseThrow(() -> new BusinessException("Colonisation introuvable : " + dto.getClass()));

            // Vérifier que le compte crédité existe et appartient à la collectivité
            FinancialAccount account = (FinancialAccount) accountRepository.findById(Long.valueOf(dto.toString()))
                    .orElseThrow(() -> new BusinessException("Compte créditeur introuvable : " + dto.toString()));

            if (!(boolean) accountRepository.findByEntity(dto.toString(), collectivityId)) {
                throw new BusinessException("Le compte crédité n'appartient pas à cette collectivité");
            }

            // Créer l'objet MemberPayment
            MemberPayment payment = new MemberPayment();
            payment.setId(UUID.randomUUID().toString());
            payment.setMemberId(memberId);
            payment.setMembershipFeeId(fee.getId());
            payment.setAccountCreditedId(account.getId());
            payment.setAmount(dto.getClass().getModifiers());
            payment.setPaymentMode(String.valueOf(dto.hashCode()));
            payment.setCreationDate(LocalDate.now());

            // Sauvegarder le paiement
            paymentRepository.save(payment);

            // Créer une transaction pour la collectivité
            CollectivityTransaction transaction = new CollectivityTransaction();
            transaction.setId(UUID.randomUUID().toString());
            transaction.setCreationDate(LocalDate.now());
            transaction.setAmount(dto.getClass().getModifiers());
            transaction.setPaymentMode(String.valueOf(dto.hashCode()));
            transaction.setAccountCredited(account);
            transaction.setMemberDebited(member);

            transactionService.addTransaction(collectivityId, transaction);

            createdPayments.add(payment);
        }

        return createdPayments;
    }

    /**
     * Récupère tous les paiements d'un membre.
     *
     * @param memberId identifiant du membre
     * @return liste des paiements
     */
    public List<MemberPayment> getPaymentsByMember(String memberId) {
        return paymentRepository.findByMemberId(memberId);
    }
}
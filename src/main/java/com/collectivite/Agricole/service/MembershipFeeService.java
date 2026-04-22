package com.collectivite.Agricole.service;

import com.collectivite.Agricole.exception.BusinessException;
import com.collectivite.Agricole.model.MembershipFee;
import com.collectivite.Agricole.repository.CollectivityRepository;
import com.collectivite.Agricole.repository.MembershipFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MembershipFeeService {

    @Autowired
    private MembershipFeeRepository feeRepository;

    @Autowired
    private CollectivityRepository collectivityRepository;

    public List<MembershipFee> createMembershipFees(String collectivityId, List<MembershipFee> fees) {
        if (!collectivityRepository.existsById(collectivityId)) {
            throw new BusinessException("Collectivité introuvable");
        }
        for (MembershipFee fee : fees) {
            if (fee.getAmount() <= 0) {
                throw new BusinessException("Le montant doit être supérieur à 0");
            }
            fee.setCollectivityId(collectivityId);
            fee.setStatus("ACTIVE");
            feeRepository.save(fee);
        }
        return fees;
    }

    public List<MembershipFee> getMembershipFees(String collectivityId) {
        return feeRepository.findByCollectivityId(collectivityId);
    }
}
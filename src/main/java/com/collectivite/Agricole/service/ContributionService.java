package com.collectivite.Agricole.service;


import com.collectivite.Agricole.exception.BusinessException;
import com.collectivite.Agricole.model.MembershipFee;
import com.collectivite.Agricole.repository.CollectivityRepository;
import com.collectivite.Agricole.repository.ContributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContributionService {

    @Autowired
    private ContributionRepository contributionRepository;

    @Autowired
    private CollectivityRepository collectivityRepository;

    public MembershipFee createContribution(String collectivityId, MembershipFee contribution) {
        if (!collectivityRepository.existsById(collectivityId)) {
            throw new BusinessException("Collectivité introuvable");
        }
        contribution.setCollectivityId(collectivityId);
        return contributionRepository.save(contribution);
    }
}
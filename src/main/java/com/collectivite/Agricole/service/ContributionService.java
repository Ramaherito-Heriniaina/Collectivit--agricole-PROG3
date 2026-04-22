package com.collectivite.Agricole.service;


import com.collectivite.Agricole.exception.BusinessException;
import com.collectivite.Agricole.model.Contribution;
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

    public Contribution createContribution(String collectivityId, Contribution contribution) {
        if (!collectivityRepository.existsById(collectivityId)) {
            throw new BusinessException("Collectivité introuvable");
        }
        contribution.setCollectivityId(collectivityId);
        return contributionRepository.save(contribution);
    }
}
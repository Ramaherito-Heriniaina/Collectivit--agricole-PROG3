package com.collectivite.Agricole.service;

package com.collectivite.Agricole.service;

import com.collectivite.Agricole.exception.BusinessException;
import com.collectivite.Agricole.model.Collectivity;
import com.collectivite.Agricole.model.Member;
import com.collectivite.Agricole.repository.CollectivityRepository;
import com.collectivite.Agricole.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class CollectivityService {

    @Autowired
    private CollectivityRepository collectivityRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Collectivity createCollectivity(Collectivity collectivity) {
        List<Member> founders = collectivity.getFoundingMembers();
        if (founders == null || founders.size() < 10) {
            throw new BusinessException("Il faut au moins 10 membres fondateurs.");
        }

        for (Member m : founders) {
            Member existing = memberRepository.findById(m.getId());
            if (existing == null) {
                throw new BusinessException("Membre fondateur introuvable : " + m.getId());
            }
            long months = ChronoUnit.MONTHS.between(existing.getMembershipDate(), LocalDate.now());
            if (months < 6) {
                throw new BusinessException("Le membre " + existing.getFirstName() + " doit avoir au moins 6 mois d'ancienneté.");
            }
        }

        collectivity.setCreationDate(LocalDate.now());
        collectivity.setAuthorized(false);
        return collectivityRepository.save(collectivity);
    }
}

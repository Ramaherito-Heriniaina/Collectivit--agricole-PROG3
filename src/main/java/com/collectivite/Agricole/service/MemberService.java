package com.collectivite.Agricole.service;


import com.collectivite.Agricole.exception.BusinessException;
import com.collectivite.Agricole.model.Member;
import com.collectivite.Agricole.model.Sponsor;
import com.collectivite.Agricole.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member addMember(Member member) {
        List<Sponsor> sponsors = member.getSponsors();
        if (sponsors == null || sponsors.size() < 2) {
            throw new BusinessException("Au moins deux parrains sont requis.");
        }

        Long targetCollectivityId = Long.valueOf(member.getCollectivity().getId());
        long sameCollectivityCount = 0;
        long otherCollectivityCount = 0;

        for (Sponsor s : sponsors) {
            Member sponsor = memberRepository.findById(s.getSponsorMemberId());
            if (sponsor == null) {
                throw new BusinessException("Parrain introuvable : " + s.getSponsorMemberId());
            }
            if (sponsor.getCollectivity() != null && sponsor.getCollectivity().getId().equals(targetCollectivityId)) {
                sameCollectivityCount++;
            } else {
                otherCollectivityCount++;
            }
        }
        if (sameCollectivityCount < otherCollectivityCount) {
            throw new BusinessException("Le nombre de parrains de la collectivité visée doit être au moins égal à celui des autres collectivités.");
        }

        if (!member.isFeesPaid()) {
            throw new BusinessException("Les frais d'adhésion de 50 000 Ar doivent être réglés.");
        }
        if (!member.isAnnualContributionsPaid()) {
            throw new BusinessException("La totalité des cotisations annuelles obligatoires doit être payée.");
        }

        member.setMembershipDate(LocalDate.now());
        return memberRepository.save(member);
    }
}

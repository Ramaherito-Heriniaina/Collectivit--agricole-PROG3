package com.collectivite.Agricole.repository;


import com.collectivite.Agricole.model.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemberRepository {
    private final Map<Long, Member> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Member save(Member member) {
        if (member.getId() == null) {
            member.setId(idGenerator.getAndIncrement());
        }
        storage.put(member.getId(), member);
        return member;
    }

    public List<Member> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Member findById(Long id) {
        return storage.get(id);
    }
}

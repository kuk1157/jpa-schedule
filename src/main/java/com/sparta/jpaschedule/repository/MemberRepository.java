package com.sparta.jpaschedule.repository;

import com.sparta.jpaschedule.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findAllBy();
    Optional<Member> findByName(String name);
    Optional<Member> findByEmail(String email);
}

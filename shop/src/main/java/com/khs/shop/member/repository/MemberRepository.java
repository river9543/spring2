package com.khs.shop.member.repository;

import com.khs.shop.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
   Member findByEmail(String email); // 수정된 메서드 이름
}

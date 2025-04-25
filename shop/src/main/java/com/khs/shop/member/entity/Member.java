package com.khs.shop.member.entity;

import com.khs.shop.member.constant.Role;
import com.khs.shop.member.dto.MemberFormDto;
import com.khs.shop.utils.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;


    private String name;

    @Column(unique = true)
    private String email;

    private String password;
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
         Member member=new Member();
         member.setName(memberFormDto.getName());
         member.setEmail(memberFormDto.getEmail());
         member.setAddress(memberFormDto.getAddress());
         member.setRole(Role.USER);
         String password=passwordEncoder.encode(memberFormDto.getPassword());
         member.setPassword(password);
         return member;
    }



}

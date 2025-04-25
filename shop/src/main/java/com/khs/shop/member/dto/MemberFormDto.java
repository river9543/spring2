package com.khs.shop.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class MemberFormDto {

    @NotBlank(message = "이름은 필수항목입니다.")
    private String name;

    @Email(message = "이메일 형식으로 입력하세요.")
    @NotEmpty(message = "이메일은 필수항목입니다.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    @Length(min = 4,max = 12,message = "최소4,최대12로 입력하세요.")
    private String password;

    @NotEmpty(message = "주소는 필수항목입니다.")
    private String address;
}

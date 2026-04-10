package com.example.community.member.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateMemberRequestDto {

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Size(max = 50)
    private String nickname;
}

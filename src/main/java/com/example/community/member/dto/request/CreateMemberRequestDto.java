package com.example.community.member.dto.request;

import com.example.community.member.domain.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateMemberRequestDto(
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        @Size(max = 255, message = "이메일은 255자 이하여야 합니다.")
        String email,

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Size(max = 255, message = "비밀번호는 255자 이하여야 합니다.")
        String password,

        @NotBlank(message = "닉네임은 필수입니다.")
        @Size(max = 50, message = "닉네임은 50자 이하여야 합니다.")
        String nickname,

        @NotBlank(message = "대학교는 필수입니다.")
        @Size(max = 255, message = "대학교는 255자 이하여야 합니다.")
        String university,

        @NotBlank(message = "학번은 필수입니다.")
        @Size(max = 20, message = "학번은 20자 이하여야 합니다.")
        String studentId
) {
    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .university(university)
                .studentId(studentId)
                .build();
    }
}

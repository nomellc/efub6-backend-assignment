package com.example.community.member.dto.response;

import com.example.community.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class UpdateMemberResponseDto {

    private String email;
    private String nickname;
    private String university;
    private String studentId;

    public static UpdateMemberResponseDto from(Member member) {
        return UpdateMemberResponseDto.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .university(member.getUniversity())
                .studentId(member.getStudentId())
                .build();
    }
}

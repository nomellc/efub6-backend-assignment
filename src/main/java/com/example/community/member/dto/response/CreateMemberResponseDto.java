package com.example.community.member.dto.response;

import com.example.community.member.domain.Member;

public record CreateMemberResponseDto(
        Long memberId,
        String email,
        String nickname,
        String university,
        String studentId
) {
    public static CreateMemberResponseDto from(Member member) {
        return new CreateMemberResponseDto(
                member.getMemberId(),
                member.getEmail(),
                member.getNickname(),
                member.getUniversity(),
                member.getStudentId()
        );
    }
}

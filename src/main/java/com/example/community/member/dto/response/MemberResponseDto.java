package com.example.community.member.dto.response;

import com.example.community.member.domain.Member;

public record MemberResponseDto(
        Long memberId,
        String email,
        String nickname,
        String university,
        String studentId
) {
    public static MemberResponseDto from(Member member) {
        return new MemberResponseDto(
                member.getMemberId(),
                member.getEmail(),
                member.getNickname(),
                member.getUniversity(),
                member.getStudentId()
        );
    }
}

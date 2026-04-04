package com.example.community.member.dto.response;

import com.example.community.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class CreateMemberResponseDto {

    private String email;
    private String nickname;
    private String university;
    private String studentId;



    public static CreateMemberResponseDto from(Member member) {

        return CreateMemberResponseDto.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .university(member.getUniversity())
                .studentId(member.getStudentId())
                .build();
    }
}

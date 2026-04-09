package com.example.community.member.dto.request;

import lombok.Getter;

@Getter
public class UpdateMemberRequestDto {

    private String email;
    private String password;
    private String nickname;
    private String university;
    private String studentId;
}

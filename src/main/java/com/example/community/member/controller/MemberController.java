package com.example.community.member.controller;

import com.example.community.member.dto.request.CreateMemberRequestDto;
import com.example.community.member.dto.request.UpdateMemberRequestDto;
import com.example.community.member.dto.response.CreateMemberResponseDto;
import com.example.community.member.dto.response.MemberResponseDto;
import com.example.community.member.dto.response.UpdateMemberResponseDto;
import com.example.community.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원 조회
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable Long memberId) {
        MemberResponseDto responseDto = memberService.getMember(memberId);
        return ResponseEntity.ok(responseDto);
    }

    // 회원 생성
    @PostMapping
    public ResponseEntity<CreateMemberResponseDto> createMember(@RequestBody CreateMemberRequestDto requestDto) {
        CreateMemberResponseDto responseDto = memberService.createMember(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 회원 수정
    @PatchMapping("/profile/{memberId}")
    public ResponseEntity<UpdateMemberResponseDto> updateMember(
            @PathVariable Long memberId,
            @RequestBody UpdateMemberRequestDto requestDto
    ) {
        UpdateMemberResponseDto responseDto = memberService.updateMember(memberId, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 회원 삭제 (논리 삭제)
    @PatchMapping("/{memberId}")
    public ResponseEntity<Map<String, String>> deleteMember(@PathVariable("memberId") Long memberId) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "삭제가 완료되었습니다.");
        return ResponseEntity.ok(response);
    }

}
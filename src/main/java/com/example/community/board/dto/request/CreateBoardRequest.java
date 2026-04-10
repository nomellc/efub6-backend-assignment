package com.example.community.board.dto.request;

import com.example.community.board.domain.Board;
import com.example.community.member.domain.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateBoardRequest {

    // temporary
    @NotNull
    private Long memberId;

    @NotBlank(message = "게시판 이름을 입력해야 합니다.")
    @Size(max = 50)
    private String name;

    @Size(max = 255)
    private String description;

    @Size(max = 255)
    private String notice;

    public Board toEntity(Member owner) {
        return Board.builder()
                .owner(owner)
                .name(name)
                .description(description)
                .notice(notice)
                .build();
    }
}

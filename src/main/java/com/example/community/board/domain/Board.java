package com.example.community.board.domain;

import com.example.community.global.domain.BaseEntity;
import com.example.community.member.domain.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member owner;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 255)
    private String description;

    @Column(length = 255)
    private String notice;

    @Builder
    public Board(Member owner, String name, String description, String notice) {
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.notice = notice;
    }

    public void changeOwner(Member newOwner) {
        this.owner = newOwner;
    }
}

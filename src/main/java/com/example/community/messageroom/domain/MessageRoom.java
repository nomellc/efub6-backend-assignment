package com.example.community.messageroom.domain;

import com.example.community.global.domain.BaseEntity;
import com.example.community.member.domain.Member;
import com.example.community.message.domain.Message;
import com.example.community.post.domain.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "message_room")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_room_id")
    private Long messageRoomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "messageRoom", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private final List<Message> messages = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private Member sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    private Member receiver;

    @Builder
    public MessageRoom(Post post, Member sender, Member receiver) {
        this.post = post;  // null이면 direct message room
        this.sender = sender;
        this.receiver = receiver;
    }

    public Member getOpponent(Long memberId) {
        return sender.getMemberId().equals(memberId) ? receiver : sender;
    }

    public boolean isNotParticipant(Long memberId) {
        return !sender.getMemberId().equals(memberId) && !receiver.getMemberId().equals(memberId);
    }
}

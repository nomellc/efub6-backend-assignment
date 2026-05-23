package com.example.community.message.service;

import com.example.community.global.exception.CustomException;
import com.example.community.global.exception.ErrorCode;
import com.example.community.member.domain.Member;
import com.example.community.member.service.MemberService;
import com.example.community.message.dto.request.CreateMessageRequest;
import com.example.community.message.dto.response.CreateMessageResponse;
import com.example.community.message.dto.response.MessageListResponse;
import com.example.community.message.domain.Message;
import com.example.community.message.repository.MessageRepository;
import com.example.community.messageroom.domain.MessageRoom;
import com.example.community.messageroom.repository.MessageRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageRoomRepository messageRoomRepository;
    private final MemberService memberService;

    // 쪽지 생성
    @Transactional
    public CreateMessageResponse createMessage(Long senderId, CreateMessageRequest request) {
        Member sender = memberService.findByMemberId(senderId);
        MessageRoom messageRoom = findMessageRoom(request.messageRoomId());

        validateParticipant(messageRoom, senderId);

        Message message = Message.builder()
                .messageRoom(messageRoom)
                .sender(sender)
                .content(request.content())
                .build();

        Message saved = messageRepository.save(message);
        return CreateMessageResponse.from(saved);
    }

    // 쪽지방 내 모든 쪽지 조회
    @Transactional(readOnly = true)
    public MessageListResponse getMessages(Long messageRoomId, Long memberId) {
        memberService.findByMemberId(memberId);
        MessageRoom messageRoom = findMessageRoom(messageRoomId);

        validateParticipant(messageRoom, memberId);

        List<Message> messages = messageRepository
                .findAllByMessageRoomMessageRoomIdOrderByCreatedAtAsc(messageRoomId);

        return MessageListResponse.from(messageRoom, messages, memberId);
    }

    private MessageRoom findMessageRoom(Long messageRoomId) {
        return messageRoomRepository.findById(messageRoomId)
                .orElseThrow(() -> new CustomException(ErrorCode.MESSAGE_ROOM_NOT_FOUND));
    }

    private void validateParticipant(MessageRoom messageRoom, Long memberId) {
        if (messageRoom.isNotParticipant(memberId)) {
            throw new CustomException(ErrorCode.MESSAGE_ROOM_ACCOUNT_MISMATCH);
        }
    }
}

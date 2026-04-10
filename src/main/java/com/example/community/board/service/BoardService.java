package com.example.community.board.service;

import com.example.community.board.domain.Board;
import com.example.community.board.dto.request.CreateBoardRequest;
import com.example.community.board.dto.request.UpdateBoardOwnerRequest;
import com.example.community.board.dto.response.BoardListResponse;
import com.example.community.board.dto.response.BoardResponse;
import com.example.community.board.dto.summary.BoardSummary;
import com.example.community.board.repository.BoardRepository;
import com.example.community.global.exception.CustomException;
import com.example.community.global.exception.ErrorCode;
import com.example.community.member.domain.Member;
import com.example.community.member.service.MemberService;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberService memberService;

    @Transactional
    public BoardResponse createBoard(@Valid CreateBoardRequest request) {
        Member owner = memberService.findByMemberId(request.getMemberId());
        Board newBoard = request.toEntity(owner);
        Board savedBoard = boardRepository.save(newBoard);
        return BoardResponse.from(savedBoard);
    }

    @Transactional(readOnly=true)
    public BoardListResponse getAllBoards() {
        List<BoardSummary> boardSummaries = boardRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(BoardSummary::from)
                .toList();
        return new BoardListResponse(boardSummaries, boardRepository.count());
    }

    @Transactional(readOnly = true)
    public BoardResponse getBoard(Long boardId) {
        Board board = findByBoardId(boardId);
        return BoardResponse.from(board);
    }

    @Transactional
    public void updateBoardOwner(Long boardId, Long memberId, @Valid UpdateBoardOwnerRequest request) {
        Board board = findByBoardId(boardId);
        Member member = memberService.findByMemberId(memberId);
        Member newOwner = memberService.findByMemberId(request.newOwnerId());

        authorizeBoardOwner(board, member);
        board.changeOwner(newOwner);
    }

    @Transactional
    public void deleteBoard(Long boardId, Long memberId) {
        Board board = findByBoardId(boardId);
        Member member = memberService.findByMemberId(memberId);

        authorizeBoardOwner(board, member);
        boardRepository.delete(board);
    }

    private void authorizeBoardOwner(Board board, Member member) {
        if(!board.getOwner().equals(member)) {
            throw new CustomException(ErrorCode.BOARD_ACCOUNT_MISMATCH);
        }
    }

    public Board findByBoardId(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
    }

}

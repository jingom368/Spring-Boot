package com.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.dto.BoardDTO;
import com.board.dto.FileDTO;
import com.board.dto.LikeDTO;
import com.board.dto.ReplyDTO;
import com.board.mapper.BoardMapper;

@Service 
class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper mapper;
	
	// 게시물 목록 보기 - selectList
	@Override
	public List<BoardDTO> list(int startPoint, int endPoint, String keyword) throws Exception {
		Map<String, Object> data = new HashMap<>();
		data.put("startPoint", startPoint);
		data.put("endPoint", endPoint);
		data.put("keyword", keyword);
		return mapper.list(data);
	}
	
	// 게시물 전체 갯수 계산
	public int getTotalCount(String keyword) {
		return mapper.getTotalCount(keyword);
	}

	// 게시물 내용 보기 - selectOne
	@Override
	public BoardDTO view(int seqno) throws Exception {
		return mapper.view(seqno);
	}
	
	// 게시물 내용 이전 보기 - selectOne
	@Override
	public int pre_seqno(int seqno, String keyword) throws Exception {
		Map<String, Object> data = new HashMap<>();
		data.put("seqno", seqno);
		data.put("keyword", keyword);
		return mapper.pre_seqno(data);
	}

	// 게시물 내용 다음 보기 - selectOne
	@Override
	public int next_seqno(int seqno, String keyword) throws Exception {
		Map<String, Object> data = new HashMap<>();
		data.put("seqno", seqno);
		data.put("keyword", keyword);
		return mapper.next_seqno(data);
	}

	// 게시물 조회수 증가 - update
	@Override
	public void hitno(int seqno) throws Exception {
		mapper.hitno(seqno);
	}
	
	// 게시물 등록 하기
	@Override
	public void write(BoardDTO board) throws Exception {
		mapper.write(board);
	}
	
	// 게시물 번호 구하기(시퀀스의 nextval을 사용)
	@Override
	public int getSeqnoWithNextval() throws Exception {
		return mapper.getSeqnoWithNextval();
	}
	
	// 첨부파일 등록 하기
	@Override
	public void fileInfoRegistry(Map<String, Object> data) {
		mapper.fileInfoRegistry(data);
	}
	
	// 첨부 파일 목록 보기
	@Override
	public List<FileDTO> fileInfoView(int seqno) {
		return mapper.fileInfoView(seqno);
	}
	
	// 다운로드를 위한 파일 정보 가져 오기
	@Override
	public FileDTO fileInfo(int fileseqno) {
		return mapper.fileInfo(fileseqno);
	}
	
	// 첨부 파일 삭제를 위한 checkfile 정보 변경
	public void fileInfoUpdate(int seqno) {
		mapper.fileInfoUpdate(seqno);
	}
	
	// 게시물 수정 하기 - update
	@Override
	public void modify(BoardDTO board) throws Exception {
		mapper.modify(board);
	}

	// 게시물 삭제하기 - view
	@Override
	public void delete(int seqno) throws Exception {
		mapper.delete(seqno);
	}
	
	// 게시물 좋아요/싫어요 수정
	@Override
	public void boardlikeUpdate(BoardDTO board) throws Exception {
		mapper.boardlikeUpdate(board);
	}

	// 좋아요/싫어요 등록 여부 확인
	@Override
	public LikeDTO likeCheckView(LikeDTO like) {
		return mapper.likeCheckView(like);
	}

	// 좋아요/싫어요 신규 등록
	@Override
	public void likeCheckRegistry(LikeDTO like) {
		mapper.likeCheckRegistry(like);
	}

	// 좋아요/싫어요 수정
	@Override
	public void likeCheckUpdate(LikeDTO like) {
		mapper.likeCheckUpdate(like);
	}

	// 댓글 보기
	@Override
	public List<ReplyDTO> replyView(ReplyDTO reply) throws Exception {
		return mapper.replyView(reply);
	}

	// 댓글 등록
	@Override
	public void replyRegistry(ReplyDTO reply) throws Exception {
		mapper.replyRegistry(reply);
	}

	// 댓글 수정
	@Override
	public void replyUpdate(ReplyDTO reply) throws Exception {
		mapper.replyUpdate(reply);
	}

	// 게시물 수정 시 파일 정보 수정(checkfile을 X로 변경)
	@Override
	public void deleteFileList(int fileseqno) throws Exception {
		mapper.deleteFileList(fileseqno);
	}
	
	// 댓글 삭제
	@Override
	public void replyDelete(ReplyDTO reply) throws Exception {
		mapper.replyDelete(reply);
	}
}

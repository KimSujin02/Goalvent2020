package goalvent;

import java.util.Vector;

public interface Board {
	//글 쓰기
	//글 수정하기
	//글 삭제하기
	//좋아요 누르기
	//해당 카테고리의 게시글들 모두 보기(카테고리)
	//내가 팔로우한 사람이 올린 게시글들만 보기(카테고리, id)
	
	public int insertBoard(BoardDTO boardDTO);
	public int updateBoard(BoardDTO boardDTO);
	public int deleteBoard(int num);
	public int updateLike(int num);
	public Vector<BoardDTO> allBoard(int category);
	public Vector<BoardDTO> filterBoard(int category, String id); //illuwa코드 참고하기
}

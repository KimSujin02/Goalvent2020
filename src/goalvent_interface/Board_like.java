package goalvent_interface;

import java.util.ArrayList;

public interface Board_like {
	//게시물에 좋아요 추가
	//게시물에 좋아요 취소
	//내가 이 게시물에 좋아요를 했는지 안했는지 검사
	// 내가 좋아요 누른 게시물들의 넘버
	
	public int boardLike(int num, String id); //게시물의 번호와 좋아요를 누른 아이디 그리고 BoardDAO와 연결해서 like의 컬럼의 카운트 수도 추가해야한다. 삭제는 반대로 -1
	public int deleteLike(int num, String id);
	public int confirmLike(int num, String id);
	public ArrayList<Integer> myLike(String id);
}

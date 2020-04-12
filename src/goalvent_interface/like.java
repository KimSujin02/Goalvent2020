package goalvent_interface;

import java.util.ArrayList;

public interface like {
	// 게시물에 좋아요 추가
	// 게시물에 좋아요 취소
	// 내가 이 게시물에 좋아요를 했는지 안했는지 검사
	// 해당 게시물을 좋아하는 사람들 리스트
	// 내가 좋아요 누른 게시물들의 넘버

	public int boardLike(int num, String liker); // 게시물의 번호와 좋아요를 누른 아이디 그리고 게시물 like의 컬럼의 카운트 수도 추가해야한다. 삭제는 반대로 -1
	public int deleteLike(int num, String liker);
	public int confirmLike(int num, String liker);
	public ArrayList<String> likers(int num);
	public ArrayList<Integer> myLike(String liker);
}

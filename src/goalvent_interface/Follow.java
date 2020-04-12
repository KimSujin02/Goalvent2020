package goalvent_interface;

import java.util.*;

public interface Follow {
	//팔로우하기
	//팔로우취소하기
	//나를 팔로우한 사람들
	//내가 팔로우한 사람들
	//내 팔로워 수
	//내 팔로우 수
	//나를 팔로우한 사람인가?
	
	public int follow(String id, String follow);
	public int upFollow(String id, String follow);
	public ArrayList<String> follower(String id);
	public ArrayList<String> following(String id);
	public int confirmFollow(String myId, String otherId);
}

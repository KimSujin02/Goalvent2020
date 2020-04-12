package goalvent_interface;

import java.util.*;

public interface Follow {
	//팔로우하기
	//팔로우취소하기
	//나를 팔로우한 사람들
	//내가 팔로우한 사람들
	
	public int follow(String id, String follow);
	public int cancleFollow(String id, String follow);
	public ArrayList<String> follower(String id);
	public ArrayList<String> following(String id);
}

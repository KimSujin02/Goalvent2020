package goalvent_interface;

import goalvent.CheerDTO;

public interface Cheer {
	//응원의 글 쓰기
	//응원의 글 삭제하기
	//내가 받은 응원의 글의 수 카운트로 다 가져오기
	
	public int insertCheer(CheerDTO cheerDTO);
	public int deleteCheer(int num);
	public int countCheer(String id);
}

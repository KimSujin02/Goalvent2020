package goalvent_interface;

import java.util.Vector;

import goalvent.EventDTO;

public interface Event {
	// 이벤트 글 쓰기
	// 이벤트 글 수정하기
	// 이벤트 글 삭제하기
	// 이벤트 글 가져오기
	// 내가 팔로우한 사람의 이벤트 글만 필터링하기
	// 좋아요 누르기
	
	public int insertEvent(EventDTO eventDTO);
	public int updateEvent(EventDTO eventDTO);
	public int deleteEvent(EventDTO eventDTO);
	public Vector<EventDTO> allEvent();
	public Vector<EventDTO> filterEvent(String id);
	public int updateLike(int num);
	
}

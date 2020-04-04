package goalvent_interface;

import goalvent.GoalDTO;

public interface Goal {
	// 목표 글 올리기
	// 목표 글 수정하기
	
	public int insertGoal(GoalDTO goalDTO);
	public int updateGoal(GoalDTO goalDTO);
}

package goalvent_interface;

import goalvent.GoalDTO;

public interface Goal {
	// ��ǥ �� �ø���
	// ��ǥ �� �����ϱ�
	
	public int insertGoal(GoalDTO goalDTO);
	public int updateGoal(GoalDTO goalDTO);
}

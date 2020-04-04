package goalvent_interface;

import java.util.Vector;

import goalvent.EventDTO;

public interface Event {
	// �̺�Ʈ �� ����
	// �̺�Ʈ �� �����ϱ�
	// �̺�Ʈ �� �����ϱ�
	// �̺�Ʈ �� ��������
	// ���� �ȷο��� ����� �̺�Ʈ �۸� ���͸��ϱ�
	// ���ƿ� ������
	
	public int insertEvent(EventDTO eventDTO);
	public int updateEvent(EventDTO eventDTO);
	public int deleteEvent(EventDTO eventDTO);
	public Vector<EventDTO> allEvent();
	public Vector<EventDTO> filterEvent(String id);
	public int updateLike(int num);
	
}

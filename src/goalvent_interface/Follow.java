package goalvent_interface;

import java.util.*;

public interface Follow {
	//�ȷο��ϱ�
	//�ȷο�����ϱ�
	//���� �ȷο��� �����
	//���� �ȷο��� �����
	//�� �ȷο� ��
	//�� �ȷο� ��
	//���� �ȷο��� ����ΰ�?
	
	public int follow(String id, String follow);
	public int upFollow(String id, String follow);
	public ArrayList<String> follower(String id);
	public ArrayList<String> following(String id);
	public int confirmFollow(String myId, String otherId);
}

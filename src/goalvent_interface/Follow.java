package goalvent;

import java.util.Vector;

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
	public Vector<String> follower(String id);
	public Vector<String> following(String id);
	public int confirmFollow(String myId, String otherId);
}

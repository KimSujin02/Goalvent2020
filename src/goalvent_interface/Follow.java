package goalvent_interface;

import java.util.*;

public interface Follow {
	//�ȷο��ϱ�
	//�ȷο�����ϱ�
	//���� �ȷο��� �����
	//���� �ȷο��� �����
	
	public int follow(String id, String follow);
	public int cancleFollow(String id, String follow);
	public ArrayList<String> follower(String id);
	public ArrayList<String> following(String id);
}

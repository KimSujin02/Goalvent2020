package goalvent_interface;

import java.util.ArrayList;

public interface like {
	// �Խù��� ���ƿ� �߰�
	// �Խù��� ���ƿ� ���
	// ���� �� �Խù��� ���ƿ並 �ߴ��� ���ߴ��� �˻�
	// ���� ���ƿ� ���� �Խù����� �ѹ�

	public int boardLike(int num, String id); // �Խù��� ��ȣ�� ���ƿ並 ���� ���̵� �׸��� �Խù� like�� �÷��� ī��Ʈ ���� �߰��ؾ��Ѵ�. ������ �ݴ�� -1
	public int deleteLike(int num, String id);
	public int confirmLike(int num, String id);
	public ArrayList<Integer> myLike(String id);
}

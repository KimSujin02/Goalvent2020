package goalvent_interface;

import java.util.ArrayList;

public interface like {
	// �Խù��� ���ƿ� �߰�
	// �Խù��� ���ƿ� ���
	// ���� �� �Խù��� ���ƿ並 �ߴ��� ���ߴ��� �˻�
	// �ش� �Խù��� �����ϴ� ����� ����Ʈ
	// ���� ���ƿ� ���� �Խù����� �ѹ�

	public int boardLike(int num, String liker); // �Խù��� ��ȣ�� ���ƿ並 ���� ���̵� �׸��� �Խù� like�� �÷��� ī��Ʈ ���� �߰��ؾ��Ѵ�. ������ �ݴ�� -1
	public int deleteLike(int num, String liker);
	public int confirmLike(int num, String liker);
	public ArrayList<String> likers(int num);
	public ArrayList<Integer> myLike(String liker);
}

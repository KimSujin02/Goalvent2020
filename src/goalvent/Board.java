package goalvent;

import java.util.Vector;

public interface Board {
	//�� ����
	//�� �����ϱ�
	//�� �����ϱ�
	//���ƿ� ������
	//�ش� ī�װ��� �Խñ۵� ��� ����(ī�װ�)
	//���� �ȷο��� ����� �ø� �Խñ۵鸸 ����(ī�װ�, id)
	
	public int insertBoard(BoardDTO boardDTO);
	public int updateBoard(BoardDTO boardDTO);
	public int deleteBoard(int num);
	public int updateLike(int num);
	public Vector<BoardDTO> allBoard(int category);
	public Vector<BoardDTO> filterBoard(int category, String id); //illuwa�ڵ� �����ϱ�
}

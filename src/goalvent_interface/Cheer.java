package goalvent_interface;

import goalvent.CheerDTO;

public interface Cheer {
	//������ �� ����
	//������ �� �����ϱ�
	//���� ���� ������ ���� �� ī��Ʈ�� �� ��������
	
	public int insertCheer(CheerDTO cheerDTO);
	public int deleteCheer(CheerDTO cheerDTO);
	public int countCheer(String id);
}

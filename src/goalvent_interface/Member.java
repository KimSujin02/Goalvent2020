package goalvent_interface;

import goalvent.MemberDTO;

public interface Member {
	// ȸ�� �����ϱ�
	// �α����ϱ�
	// ȸ�� Ż���ϱ�
	// ȸ������ �����ϱ�
	// �ߺ����̵� Ȯ���ϱ�
	// ȸ�� ���� ��������
	
	public int join(MemberDTO memberDTO);
	public int login(String id, String pw);
	public int deleteMember(String id);
	public int updateMember(MemberDTO memberDTO);
	public int confirmId(String id);
	public MemberDTO memberInfo(String id);
}

package goalvent_interface;

import goalvent.MemberDTO;

public interface Member {
	// 회원 가입하기
	// 로그인하기
	// 회원 탈퇴하기
	// 회원정보 수정하기
	// 중복아이디 확인하기
	// 회원 정보 가져오기
	
	public int join(MemberDTO memberDTO);
	public int login(String id, String pw);
	public int deleteMember(String id);
	public int updateMember(MemberDTO memberDTO);
	public int confirmId(String id);
	public MemberDTO memberInfo(String id);
}

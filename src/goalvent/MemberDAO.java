package goalvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import goalvent_interface.Member;

public class MemberDAO implements Member {
	
	Connection conn;
	PreparedStatement pstmt;
	String sql;
	ResultSet rs;
	
	DBConnectionMgr dbcp;
	
	public MemberDAO() { //������ 
		dbcp = DBConnectionMgr.getInstance();
	}
	
	@Override
	public int join(MemberDTO memberDTO) {
		int result = 1;
		sql = "INSERT INTO member VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, 0, 0)";
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getId());
			pstmt.setString(2, memberDTO.getPw());
			pstmt.setString(3, memberDTO.getName());
			pstmt.setString(4, memberDTO.getNickname());
			pstmt.setString(5, memberDTO.getEmail());
			pstmt.setString(6, memberDTO.getTel());
			pstmt.setString(7, memberDTO.getGoal());
			pstmt.setString(8, memberDTO.getExplain());
			pstmt.setString(9, memberDTO.getImage());
			pstmt.executeUpdate();
			sql = "UPDATE info SET member=member+1 WHERE num=1";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ȸ�����Խ���");
			result = 0;
		}
		return result;
	}

	@Override
	public int login(String id, String pw) {
		int result = 1;
		String pwdb = "";
		sql = "SELECT id, pw FROM member WHERE id=?";
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				pwdb = rs.getString("pw");
				if(pwdb.equals(pw)){
					System.out.println("�α��� ����");
					result = 1;
	 	   		}else {
	 	   			System.out.println("��й�ȣ�� ��ġ���� ����");
	 	   			result = 0;
	 	   		}
			} else {
				System.out.println("�ش� ���̵� ������ ����");
				result = 2;
			}
	     }catch(Exception e){
	     	e.printStackTrace();
	     	System.out.println("�α��� ���� �Ұ�");
	     	result = 0;
	     	}
		return result;
	}

	@Override
	public int deleteMember(String id) {
		int result = 1;
		sql = "DELETE FROM member WHERE id=?";
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
	    }catch(Exception e){
        	e.printStackTrace();
        	System.out.println("ȸ��Ż�� ����");
        	result = 0;
	    }
		return result;

	}

	@Override
	public int updateMember(MemberDTO memberDTO) {
		int result = 1;
		sql="UPDATE member SET pw=?, nickname=?, goal=?, explain=?, image=? WHERE id = ?";
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getPw());
			pstmt.setString(2, memberDTO.getNickname());
			pstmt.setString(3, memberDTO.getGoal());
			pstmt.setString(4, memberDTO.getExplain());
			pstmt.setString(5, memberDTO.getImage());
			pstmt.setString(6, memberDTO.getId());
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("������Ʈ ����");
			result = 0;
		}
		return result;
	}

	@Override
	public int confirmId(String id) {
		int result = 1;
		sql = "SELECT * FROM member WHERE id=?";
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("������ ���̵� ����(�ش� ���̵� ���Ұ�)");
				result = 0;
			} else {
				System.out.println("���̵� �� �� ����");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���̵� Ȯ�� �� ����");
		}
		return result;
	}

	@Override
	public MemberDTO memberInfo(String id) {
		MemberDTO memberDTO = new MemberDTO();
		sql = "SELECT * FROM member WHERE id=?";
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  memberDTO.getId());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberDTO.setId(rs.getString("id"));
				memberDTO.setPw(rs.getString("pw"));
				memberDTO.setName(rs.getString("name"));
				memberDTO.setNickname(rs.getString("nickname"));
				memberDTO.setEmail(rs.getString("nickname"));
				memberDTO.setNickname(rs.getString("nickname"));
				memberDTO.setNickname(rs.getString("nickname"));
				memberDTO.setNickname(rs.getString("nickname"));
				
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("ȸ�� ���� �ҷ����� �� ����");
		}
		return memberDTO;
	}

}

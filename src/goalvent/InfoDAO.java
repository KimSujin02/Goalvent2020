package goalvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import goalvent_interface.Info;

public class InfoDAO implements Info{

	Connection conn;
	PreparedStatement pstmt;
	String sql;
	ResultSet rs;
	
	DBConnectionMgr dbcp;
	
	public InfoDAO() { //생성자 
		dbcp = DBConnectionMgr.getInstance();
	}
	
	@Override
	public InfoDTO getInfo() {
		InfoDTO dto = new InfoDTO();
		sql = "SELECT * FROM member WHERE num=1";
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setMember(rs.getInt("member"));
				dto.setBoard(rs.getInt("board"));
				dto.setEvent(rs.getInt("event"));
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원 정보 불러오기 중 에러");
		}
		return dto;
	}

}

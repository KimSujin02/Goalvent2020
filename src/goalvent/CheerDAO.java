package goalvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import goalvent_interface.Cheer;

public class CheerDAO implements Cheer {
	
	Connection conn;
	PreparedStatement pstmt;
	String sql;
	ResultSet rs;
	
	DBConnectionMgr dbcp;
	
	public CheerDAO() { //생성자 
		dbcp = DBConnectionMgr.getInstance();
	}

	@Override
	public int insertCheer(CheerDTO cheerDTO) {
		int result = 1;
		sql = "INSERT INTO cheer VALUES(?, ?, ?, ?, ?)";
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setString(2, cheerDTO.getContent());
			pstmt.setString(3, cheerDTO.getWriter());
			pstmt.setString(4,  cheerDTO.getId());
			pstmt.setString(5, cheerDTO.getIp());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("응원의 글 올리기 실패");
			result = 0;
		}
		return result;
	}

	@Override
	public int deleteCheer(int num) {
		int result = 1;
		sql = "DELETE FROM cheer WHERE num=?";
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("응원의 글 삭제 실패");
			result = 0;
		}
		return result;
	}

	@Override
	public int countCheer(String id) {
		int result = 0;
		sql = "SELECT COUNT(num) FROM cheer WHERE id=?";
		try {
			conn =  dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("num");
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("응원의 글 개수 불러오기 실패");
		}
		return result;
	}

}

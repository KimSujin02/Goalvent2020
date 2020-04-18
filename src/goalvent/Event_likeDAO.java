package goalvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import goalvent_interface.like;

public class Event_likeDAO implements like {

	Connection conn;
	PreparedStatement pstmt;
	String sql;
	ResultSet rs;
	
	DBConnectionMgr dbcp;
	
	public Event_likeDAO() { //생성자 
		dbcp = DBConnectionMgr.getInstance();
	}
	
	@Override
	public int boardLike(int num, String liker) {
		int result = 1; // 성공 1 실패 0
		sql = "INSERT INTO board_like VALUES(?, ?, date_format(NOW(), '%Y%m%d%H%i%s'))";
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, liker);
			pstmt.executeUpdate();
			sql = "UPDATE event SET likecount=likecount+1 WHERE num=?";
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("좋아요 누르기 실패");
			result = 0;
		}
		return result;
	}

	@Override
	public int deleteLike(int num, String liker) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int confirmLike(int num, String liker) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<String> likers(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Integer> myLike(String liker) {
		// TODO Auto-generated method stub
		return null;
	}

}

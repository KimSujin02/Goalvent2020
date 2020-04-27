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
		sql = "INSERT INTO event_like VALUES(?, ?, date_format(NOW(), '%Y%m%d%H%i%s'))";
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
		int result = 1; // 성공 1 실패 0
		sql = "DELETE FROM event_like WHERE num=?, liker=?";
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, liker);
			pstmt.executeUpdate();
			sql = "UPDATE event SET likecount=likecount-1 WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("좋아요 삭제 실패");
			result = 0;
		}
		return result;
	}

	@Override
	public int confirmLike(int num, String liker) {
		int result = 1; // 좋아요를 눌렀으면 1 아니면 0 정보 가져오기 실패하면 18
		sql = "SELECT * FROM event_like WHERE num=?, liker=?";
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, liker);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
			} else {
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("사용자가 해당게시물에 좋아요를 눌렀는지 확인하지 못함.");
			result = 18;
		}
		return result;
	}

	@Override
	public ArrayList<String> likers(int num) {
		ArrayList<String> liker = new ArrayList<String>();
		String id = "";
		sql = "SELECT liker FROM event_like WHERE num=?";
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				id = rs.getString("liker");
				liker.add(id);
			} else {
				System.out.println("좋아요를 누른 게시물의 넘버를 가져오지 못함.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("좋아요를 누른 게시물의 넘버를 가져오지 못함.");
		}
		return liker;
	}

	@Override
	public ArrayList<Integer> myLike(String liker) {
		ArrayList<Integer> likelist = new ArrayList<Integer>();
		int num;
		sql = "SELECT num FROM event_like WHERE liker=? ORDER BY date DESC";
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, liker);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt("num");
				likelist.add(num);
			} else {
				System.out.println("사용자가 좋아요를 누른 게시물의 넘버를 가져오지 못함.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("사용자가 좋아요를 누른 게시물의 넘버를 가져오지 못함.");
		}
		return likelist;
	}

}

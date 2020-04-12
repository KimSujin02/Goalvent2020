package goalvent;

import java.sql.*;
import java.util.*;

import goalvent_interface.Follow;

public class FollowDAO implements Follow {
	Connection conn;
	PreparedStatement pstmt;
	String sql;
	ResultSet rs;
	
	DBConnectionMgr dbcp;
	
	public FollowDAO() { //생성자 
		dbcp = DBConnectionMgr.getInstance();
	}
	
	@Override
	public int follow(String id, String follow) {
		int result = 1;
		sql = "INSERT INTO follow VALUES(?, ?)";
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, follow);
			pstmt.executeUpdate();
			sql = "UPDATE member SET following=following+1 WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			sql = "UPDATE member SET follower=follower+1 WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, follow);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("팔로우 실패");
			result = 0;
		}
		return result;
	}

	@Override
	public int cancleFollow(String id, String follow) {
		int result = 1;
		sql = "DELETE FROM follow WHERE id=?, following=?";
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2,  follow);
			pstmt.executeUpdate();
			sql = "UPDATE member SET following=following-1 WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			sql = "UPDATE member SET follower=follower-1 WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, follow);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("팔로우 취소 실패입니다.");
			result = 0;
		}
		return result;
	}

	@Override
	public ArrayList<String> follower(String id) {
		ArrayList<String> followList = new ArrayList<String>();
		sql = "SELECT id FROM follow WHERE following=?";
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				followList.add(rs.getString("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("팔로워 리스트 불러오기 실패입니다.");
		}
		return followList;
	}
	
	@Override
	public ArrayList<String> following(String id) {
		ArrayList<String> followList = new ArrayList<String>();
		sql = "SELECT following FROM follow WHERE id=?";
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				followList.add(rs.getString("following"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("팔로잉 리스트 불러오기 실패입니다.");
		}
		return followList;
	}

}

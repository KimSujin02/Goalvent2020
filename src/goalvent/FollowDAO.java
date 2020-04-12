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
	
	public FollowDAO() { //»ý¼ºÀÚ 
		dbcp = DBConnectionMgr.getInstance();
	}
	
	@Override
	public int follow(String id, String follow) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int upFollow(String id, String follow) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<String> follower(String id) {
		// TODO Auto-generated method stub
		return null;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return followList;
	}

	@Override
	public int confirmFollow(String myId, String otherId) {
		// TODO Auto-generated method stub
		return 0;
	}

}

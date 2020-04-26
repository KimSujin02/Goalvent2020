package goalvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import goalvent_interface.Goal;

public class GoalDAO implements Goal {
	
	Connection conn;
	PreparedStatement pstmt;
	String sql;
	ResultSet rs;
	
	DBConnectionMgr dbcp;
	
	public GoalDAO() { //생성자 
		dbcp = DBConnectionMgr.getInstance();
	}
	
	@Override
	public int insertGoal(GoalDTO goalDTO) {
		int result = 1;
		sql = "INSERT INTO goal VALUES(?, ?, ?, ?, ?, ?)";
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, goalDTO.getId());
			pstmt.setString(2, goalDTO.getFinal_goal());
			pstmt.setString(3, goalDTO.getEffort1());
			pstmt.setString(4, goalDTO.getEffort2());
			pstmt.setString(5, goalDTO.getEffort3());
			pstmt.setString(6, goalDTO.getEffort4());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("목표올리기 실패");
			result = 0;
		}
		return result;
	}

	@Override
	public int updateGoal(GoalDTO goalDTO) {
		int result =1;
		sql = "UPDATE goal SET final_goal=?, effort1=?, effort2=?, effort3=?, effort4=? WHERE id=?";
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, goalDTO.getFinal_goal());
			pstmt.setString(2, goalDTO.getEffort1());
			pstmt.setString(3, goalDTO.getEffort2());
			pstmt.setString(4, goalDTO.getEffort3());
			pstmt.setString(5, goalDTO.getEffort4());
			pstmt.setString(6, goalDTO.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("목표 수정 실패");
			result = 0;
		}
		return result;
	}

}

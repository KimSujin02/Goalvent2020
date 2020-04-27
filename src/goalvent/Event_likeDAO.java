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
	
	public Event_likeDAO() { //������ 
		dbcp = DBConnectionMgr.getInstance();
	}
	
	@Override
	public int boardLike(int num, String liker) {
		int result = 1; // ���� 1 ���� 0
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
			System.out.println("���ƿ� ������ ����");
			result = 0;
		}
		return result;
	}

	@Override
	public int deleteLike(int num, String liker) {
		int result = 1; // ���� 1 ���� 0
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
			System.out.println("���ƿ� ���� ����");
			result = 0;
		}
		return result;
	}

	@Override
	public int confirmLike(int num, String liker) {
		int result = 1; // ���ƿ並 �������� 1 �ƴϸ� 0 ���� �������� �����ϸ� 18
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
			System.out.println("����ڰ� �ش�Խù��� ���ƿ並 �������� Ȯ������ ����.");
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
				System.out.println("���ƿ並 ���� �Խù��� �ѹ��� �������� ����.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ƿ並 ���� �Խù��� �ѹ��� �������� ����.");
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
				System.out.println("����ڰ� ���ƿ並 ���� �Խù��� �ѹ��� �������� ����.");
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("����ڰ� ���ƿ並 ���� �Խù��� �ѹ��� �������� ����.");
		}
		return likelist;
	}

}

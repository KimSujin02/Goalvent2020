package goalvent;

import java.sql.*;
import java.util.*;

import goalvent_interface.like;

public class Board_liikeDAO implements like {
	
	Connection conn;
	PreparedStatement pstmt;
	String sql;
	ResultSet rs;
	
	DBConnectionMgr dbcp;
	
	public Board_liikeDAO() { //������ 
		dbcp = DBConnectionMgr.getInstance();
		try {
			conn = dbcp.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int boardLike(int num, String liker, String date) {
		int result = 1; // ���� 1 ���� 0
		sql = "INSERT INTO board_like VALUES(?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, liker);
			pstmt.setString(3, date);
			pstmt.executeUpdate();
			sql = "UPDATE board SET like=like+1 WHERE num=?";
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
		sql = "DELETE * FROM board_like WHERE num=?, liker=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, liker);
			pstmt.executeUpdate();
			sql = "UPDATE board SET like=like-1 WHERE num=?";
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
		sql = "SELECT * FROM board_like WHERE num=?, liker=?";
		try {
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
		sql = "SELECT liker FROM board_like WHERE num=?";
		try {
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
		sql = "SELECT num FROM board_like WHERE liker=? ORDER BY date DESC";
		try {
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

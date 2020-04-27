package goalvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import goalvent_interface.Event;

public class EventDAO implements Event{
	
	Connection conn;
	PreparedStatement pstmt;
	String sql;
	ResultSet rs;
	
	DBConnectionMgr dbcp;
	
	public EventDAO() { //생성자 
		dbcp = DBConnectionMgr.getInstance();
	}
	
	@Override
	public int insertEvent(EventDTO eventDTO) {
		int result = 1;
		sql = "INSERT INTO event VALUES(?, ?, ?, date_format(NOW(), '%Y%m%d%H%i%s'), 0, ?, ?, ?)";
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setString(2, eventDTO.getContent());
			pstmt.setString(3, eventDTO.getWriter());
			pstmt.setString(4, eventDTO.getImage1());
			pstmt.setString(5, eventDTO.getImage2());
			pstmt.setString(6, eventDTO.getIp());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("게시물 올리기 실패");
			result = 0;
		}
		return result;
	}

	@Override
	public int updateEvent(EventDTO eventDTO) {
		int result =1;
		sql = "UPDATE event SET content=? WHERE num=?";
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, eventDTO.getContent());
			pstmt.setInt(2, eventDTO.getNum());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("게시물 수정 실패");
			result = 0;
		}
		return result;
	}

	@Override
	public int deleteEvent(EventDTO eventDTO) {
		int result = 1;
		sql = "DELETE FROM event WHERE num=?";
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, eventDTO.getNum());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("게시물 삭제 실패");
			result = 0;
		}
		return result;
	}

	@Override
	public Vector<EventDTO> allEvent() {
		sql = "SELECT * FROM event ORDER BY num DESC";
		Vector<EventDTO> vlist = new Vector<EventDTO>();
		EventDTO dto = new EventDTO();
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto.setNum(rs.getInt("num"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("writer"));
				dto.setDate(rs.getString("date"));
				dto.setLikecount(rs.getInt("likecount"));
				dto.setImage1(rs.getString("image1"));
				dto.setImage2(rs.getString("image2"));
				vlist.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return vlist;
	}

	@Override
	public Vector<EventDTO> filterEvent(String id) {
		ArrayList<String> followList = new ArrayList<String>();
		Vector<EventDTO> vlist = new Vector<EventDTO>();
		FollowDAO followDAO = new FollowDAO();
		followList = followDAO.follower(id); //사용자가 팔로우한 사람들의 리스트를 가져온다.
		EventDTO dto = new EventDTO();
		try {
			sql = "SELECT * FROM event WHERE writer=?";
			for(int i = 0; i < followList.size(); i++) {
				conn = dbcp.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, followList.get(i));
				rs = pstmt.executeQuery();
				while(rs.next()) {
					dto.setNum(rs.getInt("num"));
					dto.setContent(rs.getString("content"));
					dto.setWriter(rs.getString("writer"));
					dto.setDate(rs.getString("date"));
					dto.setLikecount(rs.getInt("likecount"));
					dto.setImage1(rs.getString("image1"));
					dto.setImage2(rs.getString("image2"));
					vlist.add(dto);
				}
			}
			Collections.sort(vlist, new EventComparator());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return vlist;
	}

}

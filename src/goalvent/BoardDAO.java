package goalvent;

import java.sql.*;
import java.util.*;

import goalvent_interface.Board;

public class BoardDAO implements Board{
	
	Connection conn;
	PreparedStatement pstmt;
	String sql;
	ResultSet rs;
	
	DBConnectionMgr dbcp;
	
	public BoardDAO() { //생성자 
		dbcp = DBConnectionMgr.getInstance();
	}
	
	@Override
	public int insertBoard(BoardDTO boardDTO) {
		int result = 1;
		sql = "INSERT INTO board VALUES(?, ?, ?, ?, ?, ?, date_format(NOW(), '%Y%m%d%H%i%s'), 0, ?)";
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setString(2, boardDTO.getContent());
			pstmt.setString(3, boardDTO.getImage1());
			pstmt.setString(4, boardDTO.getImage2());
			pstmt.setInt(5, boardDTO.getCategory());
			pstmt.setString(6, boardDTO.getWriter());
			pstmt.setString(7, boardDTO.getDate());
			pstmt.setString(8, boardDTO.getIp());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("게시물 올리기 실패");
			result = 0;
		}
		return result;
	}

	@Override
	public int updateBoard(BoardDTO boardDTO) {
		int result =1;
		sql = "UPDATE board SET content=?, image1=?, image2=?, category=?, writer=? WHERE num=?";
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardDTO.getContent());
			pstmt.setString(2, boardDTO.getImage1());
			pstmt.setString(3, boardDTO.getImage2());
			pstmt.setInt(4, boardDTO.getCategory());
			pstmt.setString(5, boardDTO.getWriter());
			pstmt.setInt(6, boardDTO.getNum());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("게시물 수정 실패");
			result = 0;
		}
		return result;
	}

	@Override
	public int deleteBoard(int num) {
		int result = 1;
		sql = "DELETE FROM board WHERE num=?";
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("게시물 삭제 실패");
			result = 0;
		}
		return result;
	}
	
	@Override
	public Vector<BoardDTO> allBoard(int category) {
		sql = "SELECT * FROM board WHERE category=? ORDER BY num DESC";
		Vector<BoardDTO> vlist = new Vector<BoardDTO>();
		BoardDTO dto = new BoardDTO();
		try {
			conn = dbcp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, category);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto.setNum(rs.getInt("num"));
				dto.setContent(rs.getString("content"));
				dto.setImage1(rs.getString("image1"));
				dto.setImage2(rs.getString("image2"));
				dto.setWriter(rs.getString("writer"));
				dto.setLikecount(rs.getInt("likecount"));
				dto.setDate(rs.getString("date"));
				vlist.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return vlist;
	}

	@Override
	public Vector<BoardDTO> filterBoard(int category, String id) {
		ArrayList<String> followList = new ArrayList<String>();
		Vector<BoardDTO> vlist = new Vector<BoardDTO>();
		FollowDAO followDAO = new FollowDAO();
		followList = followDAO.follower(id); //사용자가 팔로우한 사람들의 리스트를 가져온다.
		BoardDTO dto = new BoardDTO();
		try {
			sql = "SELECT * FROM board WHERE category=?, writer=?";
			for(int i = 0; i < followList.size(); i++) {
				conn = dbcp.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, category);
				pstmt.setString(2, followList.get(i));
				rs = pstmt.executeQuery();
				while(rs.next()) {
					dto.setNum(rs.getInt("num"));
					dto.setContent(rs.getString("content"));
					dto.setImage1(rs.getString("image1"));
					dto.setImage2(rs.getString("image2"));
					dto.setWriter(rs.getString("writer"));
					dto.setLikecount(rs.getInt("likecount"));
					dto.setDate(rs.getString("date"));
					vlist.add(dto);
				}
			}
			Collections.sort(vlist, new BoardComparator());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return vlist;
	}

}

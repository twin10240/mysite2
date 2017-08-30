package com.bigdata2017.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bigdata2017.mysite.vo.BoardVo;
import com.bigdata2017.mysite.vo.GuestbookVo;
import com.bigdata2017.mysite.vo.UserVo;

public class BoardDao {
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			// JDBC 드라이버 로딩(JDBC 클래스 로딩)
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Connection 가져오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;
	}
	
	public BoardVo getView(Long no) {
		BoardVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select title, content, no, member_no from board where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new BoardVo();
				vo.setTitle(rs.getString(1));
				vo.setContent(rs.getString(2));
				vo.setNo(rs.getLong(3));
				vo.setMemberNo(rs.getLong(4));
			}
		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			// 자원 정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return vo;
	}
	
	// 게시판 갯수
	public int getListAll() {
		int count = 0;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			
			stmt = conn.createStatement();

			String sql = "select count(*) from board";
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			// 자원 정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return count;
	}
	
	
	// board 메인화면
	public List<BoardVo> getList(int page) {
		List<BoardVo> list = new ArrayList<BoardVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select * from (select no, title, hit, reg_date, depth, member_no, name, rownum as rn\r\n" + 
					"					from (select a.no, a.title, a.hit, to_char(reg_date, 'yyyy-mm-dd hh:mi:ss') as reg_date, a.depth, a.member_no, b.name \r\n" + 
					"							from board a, member b \r\n" + 
					"							where a.member_no = b.no\r\n" + 
					"							order by g_no desc, o_no asc))\r\n" + 
					"	where (?-1)*5+1 <= rn and rn <= ?*5";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, page);
			pstmt.setInt(2, page);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setHit(rs.getInt(3));
				vo.setRegDate(rs.getString(4));
				vo.setDepth(rs.getInt(5));
				vo.setMemberNo(rs.getLong(6));
				vo.setName(rs.getString(7));
				vo.setRowNum(rs.getInt(8));
				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			// 자원 정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public int newInsert(BoardVo vo) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "insert into board values(seq_board.nextval, ?, ?, sysdate, 0, nvl((select max(g_no) from board), 0) + 1, 1, 1, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getMemberNo());

			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			// 자원 정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return count;
	}
	
	public int updateInfo( BoardVo vo ) {
		int count = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "update board set title = ?, content = ? where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getNo());
			
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("연결 실패 : " + e);
		} finally {
			// 자원 정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (conn != null) {
					conn.close();	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return count;
	}
	
	public int delete( Long no ) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "delete from board where no = ?";
			pstmt = conn.prepareStatement( sql );

			pstmt.setLong( 1, no );

			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error :" + e);
		} finally {
			// 자원 정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return count;		
	}
	
}

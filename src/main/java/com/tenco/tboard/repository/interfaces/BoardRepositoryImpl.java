package com.tenco.tboard.repository.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tenco.tboard.model.Board;
import com.tenco.tboard.util.DBUtil;

public class BoardRepositoryImpl implements BoardRepository {

	private static final String SELECT_ALL_BOARDS = " select * from board order by created_at desc limit ? offset ?  ";
	private static final String COUNT_ALL_BOARDS = " SELECT COUNT(*) AS COUNT FROM BOARD ";
	private static final String INSERT_BOARD_SQL = " INSERT INTO board(user_id,title,content) values ( ? , ? , ? ) ";

	@Override
	public void addBoard(Board board) {
		// 트랜잭션에 대해 설명해보세요.
		// 트랜잭션 = 하나의 논리적인 작업의 단위
		try (Connection conn = DBUtil.getConnection()) {
			conn.setAutoCommit(false);
			try (PreparedStatement pstmt = conn.prepareStatement(INSERT_BOARD_SQL)) {
				pstmt.setInt(1, board.getUserId());
				pstmt.setString(2, board.getTitle());
				pstmt.setString(3, board.getContent());
				pstmt.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateBoard(Board board, int principalId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteBoard(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Board selectBoardById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Board> getAllBoards(int limit, int offset) {
		List<Board> boardList = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_BOARDS)) {
			pstmt.setInt(1, limit);
			pstmt.setInt(2, offset);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Board board = Board.builder().id(rs.getInt("id")).title(rs.getString("title"))
						.userId(rs.getInt("user_id")).content(rs.getString("content"))
						.createdAt(rs.getTimestamp("created_at")).build();
				boardList.add(board);
			}
			System.out.println("BoardRepositoryImpl - 로깅 : " + boardList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardList;
	}

	@Override
	public int getTotalBoardCount() {
		int count = 0;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(COUNT_ALL_BOARDS)) {
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("로깅 totalCount : " + count);
		return count;
	}

}

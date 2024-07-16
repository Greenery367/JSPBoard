package com.tenco.tboard.repository.interfaces;

import java.util.List;

import com.tenco.tboard.model.Board;

public interface BoardRepository {
	// CRUD
	void addBoard(Board board);
	void updateBoard(Board board);
	void deleteBoard(int id);
	Board selectBoardById(int id);
	List<Board> getAllBoards(int limit,int offset);
	int getTotalBoardCount();
}

package com.cos.crud.repository;

import java.util.List;
import java.util.Optional;

import com.cos.crud.model.Board;

public interface BoardRepository {

		List<Board> findAll(); //전체보기
		void save(Board board); //글쓰기
		void update(Board board); //수정하기
		Board findById(int id); //상세 보기
		void delete(int id); //삭제 하기
	}

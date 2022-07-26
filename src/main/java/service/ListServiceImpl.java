package service;

import java.util.Collection;

import domain.BoardInfo;
import domain.BoardVO;
import mapper.ListMapper;

public class ListServiceImpl implements ListService {

	@Override
	public Collection<BoardVO> read() {
		return new ListMapper().read();
	}


	public int totalRow() {
		return new ListMapper().totalRow();
	}

	public BoardInfo boardInfo() {
		BoardInfo boardInfo = new BoardInfo();
		boardInfo.setList(new ListMapper().read());
		boardInfo.setTotalRow(new ListMapper().totalRow());
		return boardInfo;
	}


	public BoardInfo boardInfo(int startPage, int pageRow, String field, String keyWord) {
		BoardInfo boardInfo = new BoardInfo();
		boardInfo.setList(new ListMapper().read(startPage, pageRow, field, keyWord));
		boardInfo.setTotalRow(new ListMapper().totalRow(field, keyWord));
		return boardInfo;
	}

}





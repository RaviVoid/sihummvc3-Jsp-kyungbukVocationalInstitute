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


	public BoardInfo boardInfo(int startPage, int pageRow, String keyWord, String keyWord2, String keyWord3) {
		BoardInfo boardInfo = new BoardInfo();
		boardInfo.setList(new ListMapper().read(startPage, pageRow, keyWord, keyWord2, keyWord3));
		boardInfo.setTotalRow(new ListMapper().totalRow(keyWord, keyWord2, keyWord3));
		return boardInfo;
	}

}





package com.kh.mvc.board.model.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Board extends BoardEntity implements Serializable {

	private int attachCount;
	private List<Attachment> attachments;
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Board(int no, String title, String writer, String content, int readCount, Date regDate) {
		super(no, title, writer, content, readCount, regDate);
		// TODO Auto-generated constructor stub
	}
	public Board(int no, String title, String writer, String content, int readCount, Date regDate, int attachCount,
			List<Attachment> attachments) {
		super(no, title, writer, content, readCount, regDate);
		this.attachCount = attachCount;
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		return "Board [" + super.toString() 
		     + ", attachCount=" + attachCount 
		     + ", attachments=" + attachments + "]";
	}
}

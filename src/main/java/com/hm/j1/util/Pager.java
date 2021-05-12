package com.hm.j1.util;

public class Pager {

	private Long curPage;
	private Long perPage;
	
	private Long startRow; //시작번호
	
	private Long startNum;
	private Long lastNum;
	
	private boolean pre;
	private boolean next;
	
	//search
	private String kind;
	private String search;
	
	public void makeNum(Long totalCount) {
		int perBlock = 5;
		//1. totalCount
		
		//2. totalCount를 이용해서 totalPage 계산 
		Long totalPage = totalCount / this.getPerPage();
		if(totalCount % this.getPerPage() != 0) {
			totalPage++;
		}
		
		//3. totalPage를 이용해서 totalBlock 계산
		Long totalBlock = totalPage / perBlock;
		if(totalPage % perBlock != 0) {
			totalBlock++;
		}
		
		//4. curPage를 이용해서 curBlock 계산
		Long curBlock = this.getCurPage() / perBlock;
		if(this.curPage % perBlock != 0) {
			curBlock++;
		}
		
		//5. curBlock을 이용해서 startNum, lastNum 계산
		this.startNum = (curBlock-1)*perBlock+1;
		this.lastNum = curBlock * perBlock;
		
		//6. curBlock(현재블록)이 totalBlock(마지막블록)일 때
		this.pre = true;
		this.next = true;
		
		if(curBlock == totalBlock) {
			lastNum = totalPage;
			this.next = false;
		}	
		
		if(curBlock == 1) {
			this.pre = false;
		}
	}
	
	public void makeRow() {
		// curPage 1이면 StartRow는 0
		// curPage 2이면 StartRow는 10
		// curPage 3이면 StartRow는 20
		this.startRow = (this.getCurPage()-1)*this.getCurPage();
	}

	public Long getCurPage() {
		if(this.curPage == null || this.curPage == 0) {
			this.curPage = 1L;
		}
		return curPage;
	}

	public void setCurPage(Long curPage) {
		if(curPage == null || curPage == 0) {
			this.curPage = 1L;
		} else {
			this.curPage = curPage;
		}	
	}

	public Long getPerPage() {
		if(this.perPage == null || this.perPage == 0) {
			this.perPage = 10L;
		}
		return perPage;
	}

	public void setPerPage(Long perPage) {
		if(perPage == null || perPage == 0) {
			this.perPage = 10L;
		} else {
			this.perPage = perPage;
		}
	}

	public Long getStartRow() {
		return startRow;
	}

	public void setStartRow(Long startRow) {
		this.startRow = startRow;
	}

	public Long getStartNum() {
		return startNum;
	}

	public void setStartNum(Long startNum) {
		this.startNum = startNum;
	}

	public Long getLastNum() {
		return lastNum;
	}

	public void setLastNum(Long lastNum) {
		this.lastNum = lastNum;
	}

	public boolean isPre() {
		return pre;
	}

	public void setPre(boolean pre) {
		this.pre = pre;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getSearch() {
		if(this.search == null) {
			this.search="";
		}
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
}

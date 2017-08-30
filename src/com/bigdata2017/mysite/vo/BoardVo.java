package com.bigdata2017.mysite.vo;

public class BoardVo {
	private Long no;
	private String title;
	private String content;
	private String regDate;
	private int hit;
	private int gNo;
	private int oNo;
	private int depth;
	private Long memberNo;
	
	
	private String name;
	private int rowNum;
	
	public BoardVo() {
	}
	
	// 새 글 insert
	public BoardVo(String title, String content, Long memberNo) {
		this.title = title;
		this.content = content;
		this.memberNo = memberNo;
	}
	
	// 게시판 들어갔을 때 리스트 가져오기
	public BoardVo(Long no, String title, int hit, String regDate, int depth, Long memberNo, String name, int rowNum) {
		this.no = no;
		this.title = title;
		this.hit = hit;
		this.regDate = regDate;
		this.depth = depth;
		this.memberNo = memberNo;
		this.name = name;
		this.rowNum = rowNum;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getgNo() {
		return gNo;
	}
	public void setgNo(int gNo) {
		this.gNo = gNo;
	}
	public int getoNo() {
		return oNo;
	}
	public void setoNo(int oNo) {
		this.oNo = oNo;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", regDate=" + regDate + ", hit="
				+ hit + ", gNo=" + gNo + ", oNo=" + oNo + ", depth=" + depth + ", memberNo=" + memberNo + "]";
	}
}

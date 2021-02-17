package com.cafe.crawl.model;

public class CafeDto {
	private int no;
	private String title;
	private String writer;
	private String date;
	private boolean isImg;
	private boolean isFile;
	
	private int fullPage;
	
	
	
	public int getFullPage() {
		return fullPage;
	}
	public void setFullPage(int fullPage) {
		this.fullPage = fullPage;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public boolean isImg() {
		return isImg;
	}
	public void setImg(boolean isImg) {
		this.isImg = isImg;
	}
	public boolean isFile() {
		return isFile;
	}
	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	@Override
	public String toString() {
		return "CafeDto [no=" + no + ", title=" + title + ", writer=" + writer + ", date=" + date + ", isImg=" + isImg
				+ ", isFile=" + isFile + "]";
	}

	
	
	
}

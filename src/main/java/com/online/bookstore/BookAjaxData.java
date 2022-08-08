package com.online.bookstore;


public class BookAjaxData {
	  private String bookName;
	  private String bookPDF;
	  private String bookImage;
	private String bookPrice;
	private String bookAuthor;
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public void setBookPDF(String bookPDF) {
		this.bookPDF = bookPDF;
	}

	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}
	public void setBookPrice(String bookPrice) {
		this.bookPrice = bookPrice;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getBookName() {
		return bookName;
	}
	public String getBookPDF() {
		return bookPDF;
	}
	public String getBookImage() {
		return bookImage;
	}
	public String getBookPrice() {
		return bookPrice;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	@Override
	public String toString() {
		return "BookAjaxData [bookName=" + bookName + ", bookPDF=" + bookPDF + ", bookImage=" + bookImage
				+ ", bookPrice=" + bookPrice + ", bookAuthor=" + bookAuthor + "]";
	}
	
}

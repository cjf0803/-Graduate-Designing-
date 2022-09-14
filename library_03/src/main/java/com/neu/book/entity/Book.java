package com.neu.book.entity;

import com.neu.book.bookstatus.entity.BookStatus;

public class Book {
	private String id;
	private String bookname;
	private String author;
	private String pressname;
	private BookStatus bookStatus= new BookStatus();
	private String path;

	public Book(String id, String bookname, String author, String pressname, BookStatus bookStatus, String path) {
		this.id = id;
		this.bookname = bookname;
		this.author = author;
		this.pressname = pressname;
		this.bookStatus = bookStatus;
		this.path = path;
	}

	public Book(String bookname, String author, String pressname, BookStatus bookStatus, String path) {
		this.bookname = bookname;
		this.author = author;
		this.pressname = pressname;
		this.bookStatus = bookStatus;
		this.path = path;
	}

	public Book() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPressname() {
		return pressname;
	}

	public void setPressname(String pressname) {
		this.pressname = pressname;
	}

	public BookStatus getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(BookStatus bookStatus) {
		this.bookStatus = bookStatus;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}

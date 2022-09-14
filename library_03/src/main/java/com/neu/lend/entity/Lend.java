package com.neu.lend.entity;

import com.neu.book.bookstatus.entity.BookStatus;

import java.sql.Date;

/**
 * @author 陈锦房
 * book1.getId(), book1.getBookname(), book1.getAuthor(), user.getId(), user.getRealname(), user.getUsername()
 */
public class Lend {
	private String id;
	private String bookid;
	private String bookname;
	private String author;
	private String userid;
	private String realname;
	private Date lenddate;
	private Date returnDate;
	private int debit;
	private BookStatus bookStatus=new BookStatus();
	private String username;

	public Lend(String bookid, String bookname, String author, String userid, String realname, String username) {
		this.bookid = bookid;
		this.bookname = bookname;
		this.author = author;
		this.userid = userid;
		this.realname = realname;
		this.username = username;
	}

	public Lend(String bookid, BookStatus bookStatus, String username) {
		this.bookid = bookid;
		this.bookStatus = bookStatus;
		this.username = username;
	}

	public Lend(String id, String bookid, String bookname, String author, String userid, String realname, Date lenddate, Date returnDate, int debit, BookStatus bookStatus, String username) {
		this.id = id;
		this.bookid = bookid;
		this.bookname = bookname;
		this.author = author;
		this.userid = userid;
		this.realname = realname;
		this.lenddate = lenddate;
		this.returnDate = returnDate;
		this.debit = debit;
		this.bookStatus = bookStatus;
		this.username = username;
	}

	public Lend(String id, BookStatus bookStatus) {
		this.id = id;
		this.bookStatus = bookStatus;
	}

	public Lend() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookid() {
		return bookid;
	}

	public void setBookid(String bookid) {
		this.bookid = bookid;
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Date getLenddate() {
		return lenddate;
	}

	public void setLenddate(Date lenddate) {
		this.lenddate = lenddate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public int getDebit() {
		return debit;
	}

	public void setDebit(int debit) {
		this.debit = debit;
	}

	public BookStatus getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(BookStatus bookStatus) {
		this.bookStatus = bookStatus;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}

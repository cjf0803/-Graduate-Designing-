package com.neu.lend.controller;

import java.sql.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import com.neu.book.bookstatus.entity.BookStatus;
import com.neu.user.biz.UserBiz;
import com.neu.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.book.biz.BookBiz;
import com.neu.book.entity.Book;
import com.neu.lend.biz.LendBiz;
import com.neu.lend.entity.Lend;


/**
 * @author 陈锦房
 */
@Controller
@RequestMapping("lend")
@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
public class LendController {
	@Autowired
	private LendBiz lendBiz;
	@Autowired
	private BookBiz bookBiz;
	@Autowired
	private UserBiz userBiz;

	@RequestMapping(value = "/findLend")
	@ResponseBody
	public Map<String,Object> findLend(Integer index, HttpServletRequest request) {
		if (index == null) {
			index = 1;
		}
		int size = 10;

		List<Lend> list = lendBiz.findLendByUsername(index, size,(String) request.getSession().getAttribute("username"));

		int count = lendBiz.count();


		Map<String,Object> map=new HashMap<String,Object>();
		map.put("list", list);
		map.put("index", index);

		map.put("count", count);
		return map;

	}
	@RequestMapping(value = "/findAllLend")
	@ResponseBody
	public  Map<String,Object> findAllLend(Integer index) {
		if (index == null) {
			index = 1;
		}
		int size = 3;

		List<Lend> list = lendBiz.findLend(index, size);
		Map<String,Object> map=new HashMap<String,Object>();
		int count = lendBiz.count();



		map.put("list", list);
		map.put("index", index);
		map.put("count", count);
		return map;

	}
	@RequestMapping(value = "/deleteLend")
	@ResponseBody
	public boolean deleteLend(String id) {

		
		return lendBiz.deleteLend(id);

	}
	@RequestMapping(value = "/findById")
	@ResponseBody
	public  List<HashMap<String, Object>> findById(String id) {
		System.out.println(id);

		return lendBiz.findById(id);
	}



	@RequestMapping(value = "/updateLend")
	@ResponseBody
	public boolean updateLend(String id, String bookid, String bookname, String author, String userid,String realname,Date lenddate ,Date returnDate,int debit,String status,String username ) {
		BookStatus bookStatus=new BookStatus();
		bookStatus.setStatus_id(status);
		Lend lend = new Lend(id, bookid, bookname, author, userid,realname,lenddate,returnDate,debit,bookStatus,username);
		return lendBiz.updateLend(lend);


	}
	
	@RequestMapping(value = "/reback")
	@ResponseBody
	public boolean reback(String bookid, HttpServletRequest request) {
		List<HashMap<String, Object>> list = bookBiz.findById(bookid);
		BookStatus bookStatus=new BookStatus();
		bookStatus.setStatus_id("1");
		Book book1 = new Book((String)list.get(0).get("ID"), (String)list.get(0).get("BOOKNAME"), (String)list.get(0).get("AUTHOR"),  (String)list.get(0).get("PRESSNAME"),bookStatus,(String)list.get(0).get("PATH"));
		bookBiz.updateBook(book1);
		User user = userBiz.findByUsername((String) request.getSession().getAttribute("username"));
		Lend lend=new Lend(bookid,bookStatus,user.getUsername());
		return lendBiz.reback(lend);

	}
	@RequestMapping(value = "/findLendMo")
	@ResponseBody
	public Map<String, Object> findLendMo(Integer index, String username) {
		if (index == null) {
			index = 1;

		}
		int size = 3;
		List<Lend> list = lendBiz.findLendMo(index, size, username);
		Map<String, Object> map = new HashMap<String, Object>();
		//调用查询总记录的方法
		int count = lendBiz.countMo(username);
		map.put("list", list);
		map.put("index", index);
		map.put("username", username);
		map.put("count", count);
		return map;

	}
}
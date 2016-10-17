package com.yeon.guestbookTest;

import java.util.List;

import com.yeon.guestbook.GuestBookDao;
import com.yeon.guestbook.GuestbookVo;

public class GuestBookTest {

	public static void main(String[] args) {
		insertTest();
		showTest();
		System.out.println("=================");
		deleteTest();
		showTest();
	}
	public static void insertTest(){
		GuestbookVo gvo = new GuestbookVo();
		gvo.setName("홍길동");
		gvo.setPassword("1234");
		gvo.setContent("배고파 배고파 배고파");
		
		GuestBookDao dao = new GuestBookDao();
		dao.insert(gvo);
	}
	public static void showTest(){
		GuestBookDao dao = new GuestBookDao();
		List<GuestbookVo> list = dao.getlist();
		for(GuestbookVo vo : list){
			System.out.println(vo);
		}
		
	}
	public static void deleteTest(){
		GuestbookVo gvo = new GuestbookVo();
		gvo.setNo(2);
		
		GuestBookDao dao = new GuestBookDao();
		dao.delete(gvo);
	}
}

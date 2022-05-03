package com.model2.mvc.service.board.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.service.board.BoardService;
import com.model2.mvc.service.domain.Board;
import com.model2.mvc.service.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml"})
public class BoardServiceTest {
	@Autowired
	@Qualifier("boardServiceImpl")
	private BoardService boardService;
	
	//@Test
	public void testAddBaord() throws Exception {
		Board board = new Board();
		
		board.setUserId("user11");
		board.setRegion("����� ����");		
		board.setBoardCategory("����2");
		board.setBoardTitle("�׽�Ʈ��!!");
		board.setBoardContent("��������������");
		board.setStatus(0);
		
		boardService.addBoard(board);
	}
	
	@Test
	public void testGetBoard() throws Exception {
		User user = new User();
		user.setAddr("����� ����");
		
		List<Board> list=boardService.getBoard(user);
		for (Board board : list) {
			System.out.println("====================");
			System.out.println(board);
		}
	}
	
	//@Test
	public void testUpdateBorad() throws Exception {
		Board board = new Board();
		
		board.setBoardNo(10000);
		board.setUserId("user11");
		board.setRegion("�ڴ�2��");		
		board.setBoardCategory("�׽�Ʈ���Դϴ�");
		board.setBoardTitle("�׽�Ʈ��");
		board.setBoardContent("�����123�ٰ� �������� �����⸴");
		board.setStatus(0);
		
		boardService.updateBoard(board);
		
		User user = new User();
		user.setAddr("�ڴ�2��");
		
		List<Board> list=boardService.getBoard(user);
		for (Board board2 : list) {
			System.out.println("====================");
			System.out.println(board2);
		}
	}
}

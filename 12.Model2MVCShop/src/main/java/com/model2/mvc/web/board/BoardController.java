package com.model2.mvc.web.board;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.board.BoardService;
import com.model2.mvc.service.domain.Board;
import com.model2.mvc.service.domain.User;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	@Qualifier("boardServiceImpl")
	private BoardService boardService;
	
	public BoardController() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}
	
	
	@RequestMapping(value="getBoard", method=RequestMethod.GET)
	public String getBoard(@RequestParam("boardNo") int boardNo, Model model) throws Exception {
		Board board = boardService.oneBoard(boardNo);
		model.addAttribute("board",board);
		
		return "/board/getBoard.jsp";
	}
	
	@RequestMapping(value="listBoard", method=RequestMethod.GET)
	public String listBoard(@ModelAttribute("search") Search search , Model model, HttpSession session) throws Exception {
		User user = (User)session.getAttribute("user");
		
		
		model.addAttribute("board",boardService.getBoard(user));
		
		return "/board/listBoard.jsp";
	}
	
	@RequestMapping(value="addBoard", method=RequestMethod.GET)
	public String addBoardView() throws Exception {
		System.out.println("GET/addBoard");
		return "redirect:/board/addBoard.jsp";
	}
	
	@RequestMapping(value="addBoard", method=RequestMethod.POST)
	public String addBoard(@ModelAttribute("board")Board board) throws Exception {
		boardService.addBoard(board);
		
		return "redirect:/board/listBoard";
	}
}

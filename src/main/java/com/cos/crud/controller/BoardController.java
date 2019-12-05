package com.cos.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.crud.model.Board;
import com.cos.crud.repository.BoardRepository;

@Controller
@RequestMapping("/")
public class BoardController {

	@Autowired
	BoardRepository bRepo;
	
	// GET => http://localhost:8080/post
		// Model은 데이터를 Controller에서 Presentation계층(jsp 파일) 까지 들고간다.
		@GetMapping("")
		public String BoardList(Model model) {
			List<Board> boards = bRepo.findAll();

			model.addAttribute("boards", boards);

			// webapp/WEB-INF/views/board/list.jsp
			return "/board/list";
		}

		// POST => http://localhost:8080/post/update
		@PostMapping("/board/update")
		public String update(Board board) { // param, form으로 보내야함

			try {
				bRepo.update(board);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "redirect:/";
		}

		// GET => http://localhost:8080/post/delete/id
		@GetMapping("/board/delete/{id}") // id를 받으려면 @PathVariable을 쓴다 // form, get방식 = @RequestParam("id")
		public String delete(@PathVariable int id) {

			try {
				bRepo.delete(id);
			} catch (Exception e) {
				e.printStackTrace(); 
			}
			
			return "redirect:/";
		}

		// POST => http://localhost:8080/board/write
		@PostMapping("/board/write")
		public String save(Board board) {
			
			try {
				bRepo.save(board);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "redirect:/";
		}

		// GET => http://localhost:8080/post/id
		@GetMapping("/board/{id}")
		public String post(@PathVariable int id, Model model) {

			Board board = bRepo.findById(id);
			model.addAttribute("board", board);
			return "board/detail";
			
		}

		// GET => http://localhost:8080/post/writeForm
		@GetMapping("/board/writeForm")
		public String writeForm() {
			return "board/writeForm";
		}

		@GetMapping("board/updateForm/{id}")
		public String updateForm(@PathVariable int id, Model model) {

			Board board = bRepo.findById(id);

			model.addAttribute("board", board);

			return "board/updateForm";
		}

}

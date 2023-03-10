package com.bs.spring.board.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.board.model.vo.Attachment;
import com.bs.spring.board.model.vo.Board;
import com.bs.spring.common.PageFactory;
import com.bs.spring.member.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
	
	private BoardService service;

	@Autowired
	public BoardController(BoardService service) {
		this.service = service;
	}
	
	@RequestMapping("/boardList.do")
	public ModelAndView boardList(ModelAndView mv, 
			@RequestParam (value = "cPage", defaultValue = "1") int cPage, 
			@RequestParam (value = "numPerpage", defaultValue = "5") int numPerpage) {
		
		List<Board> list = service.selectBoardList(Map.of("cPage", cPage, "numPerpage", numPerpage));
		mv.addObject("boardlist", list);
		
		int totalData = service.selectBoardCount();
		mv.addObject("pageBar", PageFactory.getPage(cPage, numPerpage, totalData, "boardList.do"));
		mv.addObject("totalContents", totalData);
		
		mv.setViewName("board/boardList");
		return mv;
	}
	
	@RequestMapping("/boardView.do")
	public ModelAndView BoardView(ModelAndView mv, int boardNo) {
		Board board = service.selectBoardByNo(boardNo);
		mv.addObject("board", board);
		mv.setViewName("board/boardView");
		return mv;
	}
	
	@RequestMapping("/boardWrite.do")
	public String BoardWrite() {
		return "board/boardWrite";
	}
	
	@RequestMapping("/insertBoard.do")
	public ModelAndView insertBoard(ModelAndView mv, MultipartFile[] upFile, 
			String boardTitle, String boardContent, String boardWriter, HttpSession session) {
//		log.debug(upFile.getName[0]());
//		log.debug(upFile.getOriginalFilename[0]());
//		log.debug(upFile.getName[1]());
//		log.debug(upFile.getOriginalFilename[1]());
		log.debug(boardTitle+boardContent+boardWriter);
		
		//?????????? ???????? 
		
		//????????????????
		String path = session.getServletContext().getRealPath("/resources/upload/board/");
		
		//?????????? ???????? ?????? ??????????
		File dir = new File(path);
		if(!dir.exists()) dir.mkdirs();
		
		List<Attachment> files = new ArrayList<Attachment>();
		
		for(MultipartFile f : upFile) {
			//?????????????? ????????
			if(!f.isEmpty()) {
				//?????? ?????? ??????...
				//???? ??????????????
				String originalFileName = f.getOriginalFilename();
				String ext = originalFileName.substring(originalFileName.lastIndexOf(".")); //???????? ?????? ???????? ????????
				
				//???????? ???? ???? ???????? ?? ????????
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
				int rnd = (int)(Math.random()*10000)+1;
				String renameFile = sdf.format(System.currentTimeMillis())+"_"+rnd+ext;
				
				//???? ??????????
				try {
					//MultipartFile???????? ?????????? ?????? ???????? ????????
					f.transferTo(new File(path+renameFile));
					files.add(Attachment.builder()
							.originalFilename(originalFileName)
							.renamedFilename(renameFile).build());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		Board b = Board.builder()
				.boardTitle(boardTitle)
				.boardContent(boardContent)
				.boardWriter(Member.builder().userId(boardWriter).build())
				.files(files)
				.build();
		
		int result = service.insertBoard(b);
		mv.addObject("msg",result>0 ? "?????? ???? ????" : "?????? ???? ????" );
		mv.addObject("loc", "/board/boardList.do");
		
		mv.setViewName("common/msg");
		return mv;
	}
	
	@RequestMapping("/filedown.do")
	public void fileDownload(String ori, String re, HttpServletResponse response, HttpSession session, 
								@RequestHeader(value="User-agent") String header) {
		
		String path = session.getServletContext().getRealPath("/resources/upload/board/");
		File downloadFile = new File(path+re);
		
		try(FileInputStream fis = new FileInputStream(downloadFile); BufferedInputStream bis = new BufferedInputStream(fis);) {
			ServletOutputStream sos = response.getOutputStream();
			
			//?????? ??????????
			boolean isMS = header.contains("Trident") || header.contains("MSIE");
			String encodeFilename = "";
			if(isMS) {
				encodeFilename = URLEncoder.encode(ori, "UTF-8");
				encodeFilename = encodeFilename.replaceAll("\\+", "%20");
			}else {
				encodeFilename = new String(ori.getBytes("UTF-8"), "ISO-8859-1");
			}
			
			response.setContentType("application/octet-stream; charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename=\""+encodeFilename+"\"");
			
			int read = -1;
			while((read=bis.read())!=-1) {
				sos.write(read);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

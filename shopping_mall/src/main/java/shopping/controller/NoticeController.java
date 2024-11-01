package shopping.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import shopping.daoClass.NoticeDAO;
import shopping.service.NoticeService;
import shoppingDao.noticeDao;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	//공지사항 리스트 페이지로 이동
    @GetMapping("/noticeList")
    public String noticeList(Model model) {
        model.addAttribute("noticeList", noticeService.getNoticeList());
        return "/shop_source/notice_list";
    }
    
    //공지사항 등록 폼으로 이동
    @GetMapping("/noticeWrite")
    public String noticeWrite() {
        return "/shop_source/notice_write";
    }
    
    // 공지사항 등록 처리
    @PostMapping("/noticeRegister")
    public String noticeRegister(noticeDao noticeDao, HttpServletRequest request) throws IOException {
        MultipartFile file = noticeDao.getAttachmentFile();  // MultipartFile로 파일 처리
        if (file != null && !file.isEmpty()) {  // 파일이 있을 때만 처리
            String uploadDir = request.getServletContext().getRealPath("/uploads/");
            File saveFile = new File(uploadDir, file.getOriginalFilename());
            file.transferTo(saveFile);
            noticeDao.setAttachment(saveFile.getName());  // 파일 이름 저장
        } else {
            noticeDao.setAttachment(null);  // 파일이 없을 때는 null로 설정
        }

        noticeService.registerNotice(noticeDao);
        return "redirect:/noticeList";
    }
    
    //공지사항 상세보기
    
    // 공지사항 상세보기
    @GetMapping("/noticeView")
    public String noticeView(int id, Model model) {
        model.addAttribute("notice", noticeService.getNoticeById(id));
        return "/shop_source/notice_view";
    }
}

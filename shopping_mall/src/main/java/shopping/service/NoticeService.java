package shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shopping.daoClass.NoticeDAO;
import shoppingDao.noticeDao;

@Service
public class NoticeService {

	@Autowired
	private NoticeDAO noticeDAO;
	
    public void registerNotice(noticeDao noticeDao) {
       noticeDAO.insertNotice(noticeDao);
    }

    public List<noticeDao> getNoticeList() {
        return noticeDAO.selectNoticeList();
    }

    public noticeDao getNoticeById(int id) {
        return noticeDAO.selectNoticeById(id);
    }
}

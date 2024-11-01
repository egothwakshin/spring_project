package shopping.daoClass;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import shoppingDao.noticeDao;

@Repository
public class NoticeDAO {

	@Autowired
	@Qualifier("template2")
	private SqlSession sqlSession;
	
	private static final String NAMESPACE = "shop_source";
	
	public void insertNotice(noticeDao noticeDao) {
		sqlSession.insert(NAMESPACE + ".insertNotice", noticeDao);
	}
	
	public List<noticeDao> selectNoticeList(){
		return sqlSession.selectList(NAMESPACE + ".selectNoticeList");
	}
	
	public noticeDao selectNoticeById(int id) {
		return sqlSession.selectOne(NAMESPACE + ".selectNoticeById", id);
	}
	
	
}

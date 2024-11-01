package shoppingDao;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class noticeDao {

    private int id;
    private String title;
    private String content;
    private String writer;
    private MultipartFile attachmentFile;
    private String attachment;
    private Date createdAt;
    private int views;
}

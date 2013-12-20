package dd2.local.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 10. 23
 * Time: 오전 11:10
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TB_UPLOADFILE"
)
public class UploadFile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FILENAME", nullable = false)
    private String fileName;

    @Column(name = "ORIGNAI_FILENAME", nullable = false)
    private String orignalFileName;

    @Column(name = "FILESIZE", nullable = false)
    private Long fileSize;

    @Column(name = "CONTENT_TYPE", nullable = true)
    private String contentType;

    @Column(name = "CREATEDATE", nullable = false)
    private Date createDate;

    @Column(name = "UPDATEDATE", nullable = true)
    private Date updateDate;

    @OneToOne(targetEntity = Member.class)
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "ID", nullable = false, insertable = true, updatable = true)
    private Member member;

    @ManyToOne(targetEntity = Board.class)
    @JoinColumn(name = "BOARD_ID", referencedColumnName = "ID", nullable = false, insertable = true, updatable = true)
    private Board board;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOrignalFileName() {
        return orignalFileName;
    }

    public void setOrignalFileName(String orignalFileName) {
        this.orignalFileName = orignalFileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}

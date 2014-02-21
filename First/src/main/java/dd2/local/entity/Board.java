package dd2.local.entity;

import org.hibernate.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 10. 4
 * Time: 오후 1:58
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TB_BOARD")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Board implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "BOARDNAME", nullable = false)
    private String boardName;

    @Column(name = "LEVEL", nullable = false)
    private Integer level;

    @ManyToOne(targetEntity = Board.class)
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID", nullable = true)
    private Board parentBoard;

    @OneToMany(cascade = {CascadeType.ALL}, targetEntity = Board.class, mappedBy = "parentBoard")
    private Set<Board> childrenBoard = new HashSet<>();

    @Column(name = "REPLYCOUNT", nullable = false)
    private Integer replyCount = 0;

    @Column(name = "COMMENTCOUNT", nullable = false)
    private Integer commentCount = 0;

    @OneToOne(targetEntity = Member.class)
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "ID", nullable = false, insertable = true, updatable = true)
    private Member member;

    @Column(name = "CREATEDATE", nullable = false)
    private Date createDate;

    @Column(name = "UPDATEDATE", nullable = false)
    private Date updateDate;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "CONTENTS", nullable = false, columnDefinition = "TEXT")
    private String contents;

    @OneToMany(cascade = {CascadeType.ALL}, targetEntity = Comment.class, mappedBy = "board")
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(cascade = {CascadeType.ALL}, targetEntity = UploadFile.class, mappedBy = "board")
    private Set<UploadFile> uploadFiles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Board getParentBoard() {
        return parentBoard;
    }

    public void setParentBoard(Board parentBoard) {
        this.parentBoard = parentBoard;
    }

    public Set<Board> getChildrenBoard() {
        return childrenBoard;
    }

    public void setChildrenBoard(Set<Board> childrenBoard) {
        this.childrenBoard = childrenBoard;
    }

    /**
     * 답글을 추가한다.
     * @param childBoard
     */
    public void addChildBoard(Board childBoard) {
        childBoard.setParentBoard(this);
        this.getChildrenBoard().add(childBoard);
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    /**
     * 코멘트를 추가한다.
     * @param comment
     */
    public void addComment(Comment comment) {
        comment.setBoard(this);
        this.getComments().add(comment);
    }

    public Set<UploadFile> getUploadFiles() {
        return uploadFiles;
    }

    public void setUploadFiles(Set<UploadFile> uploadFiles) {
        this.uploadFiles = uploadFiles;
    }

    /**
     * 첨부 파일을 추가한다.
     * @param uploadFile
     */
    public void addUploadFile(UploadFile uploadFile) {
        uploadFile.setBoard(this);
        this.getUploadFiles().add(uploadFile);
    }
}

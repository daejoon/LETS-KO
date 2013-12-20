package dd2.local.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 10. 14
 * Time: 오후 3:42
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TB_COMMENT")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(targetEntity = Board.class)
    @JoinColumn(name = "BOARD_ID", referencedColumnName = "ID", nullable = false, insertable = true, updatable = true)
    private Board board;

    @OneToOne(targetEntity = Member.class)
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "ID", nullable = false, insertable = true, updatable = true)
    private Member member;

    @Column(name = "CONTENTS", nullable = false)
    private String contents;

    @Column(name = "CREATEDATE", nullable = false)
    private Date createDate;

    @Column(name = "UPDATEDATE", nullable = true)
    private Date updateDate;

    public Comment() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getContents() {
        return this.contents;
    }

    public void setContents(String contents) {
       this.contents = contents;
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
}

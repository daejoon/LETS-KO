package dd2.local.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 8. 16
 * Time: 오후 3:56
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TB_CODE_ROLE")
public class CodeRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "REMARK", nullable = true, length = 2048)
    private String remark;

    public CodeRole() {
    }

    public CodeRole(Long id) {
        this.setId(id);
    }

    public CodeRole(String name, String remark) {
        this.name = name;
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

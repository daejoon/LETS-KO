package dd2.local.entity;

import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 8. 9
 * Time: 오후 2:23
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TB_ROLE"
    , uniqueConstraints = { @UniqueConstraint( columnNames = {"MEMBER_ID", "CODEROLE_ID"} ) }
)
public class Role implements Serializable, GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "ID", nullable = false, insertable = true, updatable = true)
    private Member member;

    @OneToOne(targetEntity = CodeRole.class)
    @JoinColumn(name = "CODEROLE_ID", referencedColumnName = "ID", nullable = false, insertable = true, updatable = true)
    private CodeRole codeRole;

    public Role() {
    }

    public Role(Long codeRoleId) {
        this.setCodeRole(new CodeRole(codeRoleId));
    }

    public Role(CodeRole role) {
        this.setCodeRole(role);
    }

    public Role(CodeRole role, Member member) {
        this.setCodeRole(role);
        this.setMember(member);
    }

    public Long getId() {
        return id;
    }

    public CodeRole getCodeRole() {
        return codeRole;
    }

    public void setCodeRole(CodeRole codeRole) {
        this.codeRole = codeRole;
    }

    public String getRoleName() {
        return this.codeRole.getName();
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    /**
     * Spring Security Interface
     * @return
     */
    @Override
    public String getAuthority() {
        return this.getRoleName();
    }
}

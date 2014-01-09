package dd2.local.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 8. 1
 * Time: 오전 10:30
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TB_MEMBER", uniqueConstraints = { @UniqueConstraint(columnNames = {"USERNAME"}) })
public class Member implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ENABLED", nullable = false, columnDefinition = "TINYINT(1) NOT NULL")
    private Boolean enabled;

    @Column(name = "CREATE_DATE", nullable = false)
    private Date createDate;

    @Column(name = "UPDATE_DATE", nullable = true)
    private Date updateDate;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "member")
    private Set<Role> roles = new HashSet<Role>();

    public Long getId() {
        return id;
    }

    /**
     * Spring Security Interface
     * @return
     */
    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    /**
     * Spring Security Interface
     * @return
     */
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Spring Security Interface
     * @return
     */
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        role.setMember(this);
        this.roles.add(role);
    }

    /**
     * Spring Security Interface
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles();
    }

    /**
     * Spring Security Interface
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Spring Security Interface
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Spring Security Interface
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}

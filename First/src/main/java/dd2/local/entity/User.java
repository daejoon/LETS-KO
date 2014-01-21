package dd2.local.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by kdj on 2014. 1. 21..
 */
public class User {

    @Size(min = 1, max = 10, message = "이름은 1자 이상 10자 이하 입니다.")
    private String name;

    @Pattern(regexp = "^[1-9][0-9]{1,2}$", message = "나이에는 숫자만 올수 있습니다.")
    private String age;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$", message = "유효하지 않은 이메일 입니다.")
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    @NotEmpty(message = "값을 입력해 주세요.")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

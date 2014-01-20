package dd2.local.entity;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
}

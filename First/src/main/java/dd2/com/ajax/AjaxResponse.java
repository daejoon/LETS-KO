package dd2.com.ajax;

import java.io.Serializable;

/**
 * Created by KDJ on 13. 12. 9.
 */
public class AjaxResponse<T> implements Serializable {
    private boolean status = false;
    private String message = "";
    private T data;

    public boolean isStatus() {
        return status;
    }

    /**
     * true : 성공
     * false : 실패
     * @param status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    /**
     * 실패시 메세지
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

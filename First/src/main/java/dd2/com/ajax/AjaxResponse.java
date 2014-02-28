package dd2.com.ajax;

import java.io.Serializable;

/**
 * 일괄적인 Json 구조를 위한 Generic Class
 * @param <T> Json으로 생성하고자 하는 POJO 및 Collection의 Data Model
 */
public class AjaxResponse<T> implements Serializable {
    private boolean status = false;
    private String message = "";
    private T data;

    /**
     * 상태 Getter
     * @return ture: 성공, false: 실패
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * 상태 Setter
     * @param status 실패: true, 성공: false
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * 상태 메세지를 읽는다.
     * @return 메세지 리턴.
     */
    public String getMessage() {
        return message;
    }

    /**
     * 상태 메세지를 설정한다.
     * @param message 메세지를 설정한다.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Data Getter
     * @return Data 리턴한다.
     */
    public T getData() {
        return data;
    }

    /**
     * Data Setter
     * @param data Data 설정한다.
     */
    public void setData(T data) {
        this.data = data;
    }
}

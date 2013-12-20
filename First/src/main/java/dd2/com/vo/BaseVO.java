package dd2.com.vo;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 7. 23
 * Time: 오후 10:37
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseVO implements Serializable {
    private static final long serialVersionUID = 1L;

    public String toString() {
        try {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        }
        catch (Exception e) {
            return "";
        }
    }

    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}

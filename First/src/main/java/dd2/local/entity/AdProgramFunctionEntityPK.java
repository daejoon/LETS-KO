package dd2.local.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 11
 * Time: 오전 9:56
 * To change this template use File | Settings | File Templates.
 */
public class AdProgramFunctionEntityPK implements Serializable {
    private Long adProgramId;
    private Long adFunctionId;
    private Long adCompanyId;

    @Id
    @Column(name = "AD_Program_ID")
    public Long getAdProgramId() {
        return adProgramId;
    }

    public void setAdProgramId(Long adProgramId) {
        this.adProgramId = adProgramId;
        }

    @Id
    @Column(name = "AD_Function_ID")
    public Long getAdFunctionId() {
        return adFunctionId;
    }

    public void setAdFunctionId(Long adFunctionId) {
        this.adFunctionId = adFunctionId;
    }

    @Id
    @Column(name = "AD_Company_ID")
    public Long getAdCompanyId() {
        return adCompanyId;
    }

    public void setAdCompanyId(Long adCompanyId) {
        this.adCompanyId = adCompanyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdProgramFunctionEntityPK that = (AdProgramFunctionEntityPK) o;

        if (adCompanyId != null ? !adCompanyId.equals(that.adCompanyId) : that.adCompanyId != null) return false;
        if (adFunctionId != null ? !adFunctionId.equals(that.adFunctionId) : that.adFunctionId != null) return false;
        if (adProgramId != null ? !adProgramId.equals(that.adProgramId) : that.adProgramId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = adProgramId != null ? adProgramId.hashCode() : 0;
        result = 31 * result + (adFunctionId != null ? adFunctionId.hashCode() : 0);
        result = 31 * result + (adCompanyId != null ? adCompanyId.hashCode() : 0);
        return result;
    }
}

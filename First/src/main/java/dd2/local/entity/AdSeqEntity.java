package dd2.local.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 11
 * Time: 오후 5:12
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "AD_Seq", schema = "dbo", catalog = "lets_ko")
@Entity
public class AdSeqEntity {
    private String adTableName;
    private Date created;
    private Long createdBy;
    private Date updated;
    private Long updatedBy;
    private Long seq;

    @javax.persistence.Column(name = "AD_TableName")
    @Id
    public String getAdTableName() {
        return adTableName;
    }

    public void setAdTableName(String adTableName) {
        this.adTableName = adTableName;
    }

    @javax.persistence.Column(name = "Created")
    @Basic
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @javax.persistence.Column(name = "CreatedBy")
    @Basic
    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    @javax.persistence.Column(name = "Updated")
    @Basic
    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @javax.persistence.Column(name = "UpdatedBy")
    @Basic
    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    @javax.persistence.Column(name = "Seq")
    @Basic
    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdSeqEntity that = (AdSeqEntity) o;

        if (adTableName != null ? !adTableName.equals(that.adTableName) : that.adTableName != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (seq != null ? !seq.equals(that.seq) : that.seq != null) return false;
        if (updated != null ? !updated.equals(that.updated) : that.updated != null) return false;
        if (updatedBy != null ? !updatedBy.equals(that.updatedBy) : that.updatedBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (adTableName != null ? adTableName.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (updated != null ? updated.hashCode() : 0);
        result = 31 * result + (updatedBy != null ? updatedBy.hashCode() : 0);
        result = 31 * result + (seq != null ? seq.hashCode() : 0);
        return result;
    }
}

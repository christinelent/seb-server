package ch.ethz.seb.sebserver.webservice.datalayer.batis.model;

import javax.annotation.Generated;

public class ExamRecord {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-04-30T14:19:49.115+02:00", comments="Source field: exam.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-04-30T14:19:49.115+02:00", comments="Source field: exam.institution_id")
    private Long institutionId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-04-30T14:19:49.116+02:00", comments="Source field: exam.lms_setup_id")
    private Long lmsSetupId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-04-30T14:19:49.116+02:00", comments="Source field: exam.external_id")
    private String externalId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-04-30T14:19:49.116+02:00", comments="Source field: exam.owner")
    private String owner;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-04-30T14:19:49.116+02:00", comments="Source field: exam.supporter")
    private String supporter;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-04-30T14:19:49.116+02:00", comments="Source field: exam.type")
    private String type;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-04-30T14:19:49.116+02:00", comments="Source field: exam.quit_password")
    private String quitPassword;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-04-30T14:19:49.116+02:00", comments="Source field: exam.active")
    private Integer active;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-04-30T14:19:49.115+02:00", comments="Source Table: exam")
    public ExamRecord(Long id, Long institutionId, Long lmsSetupId, String externalId, String owner, String supporter, String type, String quitPassword, Integer active) {
        this.id = id;
        this.institutionId = institutionId;
        this.lmsSetupId = lmsSetupId;
        this.externalId = externalId;
        this.owner = owner;
        this.supporter = supporter;
        this.type = type;
        this.quitPassword = quitPassword;
        this.active = active;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-04-30T14:19:49.115+02:00", comments="Source field: exam.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-04-30T14:19:49.116+02:00", comments="Source field: exam.institution_id")
    public Long getInstitutionId() {
        return institutionId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-04-30T14:19:49.116+02:00", comments="Source field: exam.lms_setup_id")
    public Long getLmsSetupId() {
        return lmsSetupId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-04-30T14:19:49.116+02:00", comments="Source field: exam.external_id")
    public String getExternalId() {
        return externalId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-04-30T14:19:49.116+02:00", comments="Source field: exam.owner")
    public String getOwner() {
        return owner;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-04-30T14:19:49.116+02:00", comments="Source field: exam.supporter")
    public String getSupporter() {
        return supporter;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-04-30T14:19:49.116+02:00", comments="Source field: exam.type")
    public String getType() {
        return type;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-04-30T14:19:49.116+02:00", comments="Source field: exam.quit_password")
    public String getQuitPassword() {
        return quitPassword;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-04-30T14:19:49.117+02:00", comments="Source field: exam.active")
    public Integer getActive() {
        return active;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam
     *
     * @mbg.generated Tue Apr 30 14:19:49 CEST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", institutionId=").append(institutionId);
        sb.append(", lmsSetupId=").append(lmsSetupId);
        sb.append(", externalId=").append(externalId);
        sb.append(", owner=").append(owner);
        sb.append(", supporter=").append(supporter);
        sb.append(", type=").append(type);
        sb.append(", quitPassword=").append(quitPassword);
        sb.append(", active=").append(active);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam
     *
     * @mbg.generated Tue Apr 30 14:19:49 CEST 2019
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ExamRecord other = (ExamRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getInstitutionId() == null ? other.getInstitutionId() == null : this.getInstitutionId().equals(other.getInstitutionId()))
            && (this.getLmsSetupId() == null ? other.getLmsSetupId() == null : this.getLmsSetupId().equals(other.getLmsSetupId()))
            && (this.getExternalId() == null ? other.getExternalId() == null : this.getExternalId().equals(other.getExternalId()))
            && (this.getOwner() == null ? other.getOwner() == null : this.getOwner().equals(other.getOwner()))
            && (this.getSupporter() == null ? other.getSupporter() == null : this.getSupporter().equals(other.getSupporter()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getQuitPassword() == null ? other.getQuitPassword() == null : this.getQuitPassword().equals(other.getQuitPassword()))
            && (this.getActive() == null ? other.getActive() == null : this.getActive().equals(other.getActive()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exam
     *
     * @mbg.generated Tue Apr 30 14:19:49 CEST 2019
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getInstitutionId() == null) ? 0 : getInstitutionId().hashCode());
        result = prime * result + ((getLmsSetupId() == null) ? 0 : getLmsSetupId().hashCode());
        result = prime * result + ((getExternalId() == null) ? 0 : getExternalId().hashCode());
        result = prime * result + ((getOwner() == null) ? 0 : getOwner().hashCode());
        result = prime * result + ((getSupporter() == null) ? 0 : getSupporter().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getQuitPassword() == null) ? 0 : getQuitPassword().hashCode());
        result = prime * result + ((getActive() == null) ? 0 : getActive().hashCode());
        return result;
    }
}
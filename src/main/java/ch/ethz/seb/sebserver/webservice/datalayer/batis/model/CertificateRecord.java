package ch.ethz.seb.sebserver.webservice.datalayer.batis.model;

import java.util.Arrays;
import javax.annotation.Generated;

public class CertificateRecord {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-28T09:50:10.784+02:00", comments="Source field: certificate.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-28T09:50:10.784+02:00", comments="Source field: certificate.institution_id")
    private Long institutionId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-28T09:50:10.784+02:00", comments="Source field: certificate.aliases")
    private String aliases;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-28T09:50:10.784+02:00", comments="Source field: certificate.cert_store")
    private byte[] certStore;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-28T09:50:10.783+02:00", comments="Source Table: certificate")
    public CertificateRecord(Long id, Long institutionId, String aliases, byte[] certStore) {
        this.id = id;
        this.institutionId = institutionId;
        this.aliases = aliases;
        this.certStore = certStore;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-28T09:50:10.784+02:00", comments="Source field: certificate.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-28T09:50:10.784+02:00", comments="Source field: certificate.institution_id")
    public Long getInstitutionId() {
        return institutionId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-28T09:50:10.784+02:00", comments="Source field: certificate.aliases")
    public String getAliases() {
        return aliases;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-28T09:50:10.784+02:00", comments="Source field: certificate.cert_store")
    public byte[] getCertStore() {
        return certStore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table certificate
     *
     * @mbg.generated Wed Apr 28 09:50:10 CEST 2021
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", institutionId=").append(institutionId);
        sb.append(", aliases=").append(aliases);
        sb.append(", certStore=").append(certStore);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table certificate
     *
     * @mbg.generated Wed Apr 28 09:50:10 CEST 2021
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
        CertificateRecord other = (CertificateRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getInstitutionId() == null ? other.getInstitutionId() == null : this.getInstitutionId().equals(other.getInstitutionId()))
            && (this.getAliases() == null ? other.getAliases() == null : this.getAliases().equals(other.getAliases()))
            && (Arrays.equals(this.getCertStore(), other.getCertStore()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table certificate
     *
     * @mbg.generated Wed Apr 28 09:50:10 CEST 2021
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getInstitutionId() == null) ? 0 : getInstitutionId().hashCode());
        result = prime * result + ((getAliases() == null) ? 0 : getAliases().hashCode());
        result = prime * result + (Arrays.hashCode(getCertStore()));
        return result;
    }
}
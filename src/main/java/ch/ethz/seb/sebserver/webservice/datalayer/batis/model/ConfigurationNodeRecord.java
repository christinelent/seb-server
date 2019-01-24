package ch.ethz.seb.sebserver.webservice.datalayer.batis.model;

import javax.annotation.Generated;

public class ConfigurationNodeRecord {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-01-24T13:19:09.506+01:00", comments="Source field: configuration_node.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-01-24T13:19:09.506+01:00", comments="Source field: configuration_node.institution_id")
    private Long institutionId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-01-24T13:19:09.521+01:00", comments="Source field: configuration_node.owner")
    private String owner;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-01-24T13:19:09.521+01:00", comments="Source field: configuration_node.name")
    private String name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-01-24T13:19:09.521+01:00", comments="Source field: configuration_node.description")
    private String description;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-01-24T13:19:09.521+01:00", comments="Source field: configuration_node.type")
    private String type;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-01-24T13:19:09.521+01:00", comments="Source field: configuration_node.template")
    private String template;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-01-24T13:19:09.521+01:00", comments="Source field: configuration_node.active")
    private Integer active;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-01-24T13:19:09.506+01:00", comments="Source Table: configuration_node")
    public ConfigurationNodeRecord(Long id, Long institutionId, String owner, String name, String description, String type, String template, Integer active) {
        this.id = id;
        this.institutionId = institutionId;
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.type = type;
        this.template = template;
        this.active = active;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-01-24T13:19:09.506+01:00", comments="Source field: configuration_node.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-01-24T13:19:09.506+01:00", comments="Source field: configuration_node.institution_id")
    public Long getInstitutionId() {
        return institutionId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-01-24T13:19:09.521+01:00", comments="Source field: configuration_node.owner")
    public String getOwner() {
        return owner;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-01-24T13:19:09.521+01:00", comments="Source field: configuration_node.name")
    public String getName() {
        return name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-01-24T13:19:09.521+01:00", comments="Source field: configuration_node.description")
    public String getDescription() {
        return description;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-01-24T13:19:09.521+01:00", comments="Source field: configuration_node.type")
    public String getType() {
        return type;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-01-24T13:19:09.521+01:00", comments="Source field: configuration_node.template")
    public String getTemplate() {
        return template;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2019-01-24T13:19:09.521+01:00", comments="Source field: configuration_node.active")
    public Integer getActive() {
        return active;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table configuration_node
     *
     * @mbg.generated Thu Jan 24 13:19:09 CET 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", institutionId=").append(institutionId);
        sb.append(", owner=").append(owner);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", type=").append(type);
        sb.append(", template=").append(template);
        sb.append(", active=").append(active);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table configuration_node
     *
     * @mbg.generated Thu Jan 24 13:19:09 CET 2019
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
        ConfigurationNodeRecord other = (ConfigurationNodeRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getInstitutionId() == null ? other.getInstitutionId() == null : this.getInstitutionId().equals(other.getInstitutionId()))
            && (this.getOwner() == null ? other.getOwner() == null : this.getOwner().equals(other.getOwner()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getTemplate() == null ? other.getTemplate() == null : this.getTemplate().equals(other.getTemplate()))
            && (this.getActive() == null ? other.getActive() == null : this.getActive().equals(other.getActive()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table configuration_node
     *
     * @mbg.generated Thu Jan 24 13:19:09 CET 2019
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getInstitutionId() == null) ? 0 : getInstitutionId().hashCode());
        result = prime * result + ((getOwner() == null) ? 0 : getOwner().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getTemplate() == null) ? 0 : getTemplate().hashCode());
        result = prime * result + ((getActive() == null) ? 0 : getActive().hashCode());
        return result;
    }
}
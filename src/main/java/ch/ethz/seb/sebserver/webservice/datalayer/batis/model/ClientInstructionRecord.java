package ch.ethz.seb.sebserver.webservice.datalayer.batis.model;

import javax.annotation.Generated;

public class ClientInstructionRecord {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-28T09:50:10.749+02:00", comments="Source field: client_instruction.id")
    private Long id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-28T09:50:10.749+02:00", comments="Source field: client_instruction.exam_id")
    private Long examId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-28T09:50:10.749+02:00", comments="Source field: client_instruction.connection_token")
    private String connectionToken;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-28T09:50:10.749+02:00", comments="Source field: client_instruction.type")
    private String type;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-28T09:50:10.749+02:00", comments="Source field: client_instruction.attributes")
    private String attributes;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-28T09:50:10.750+02:00", comments="Source field: client_instruction.needs_confirmation")
    private Integer needsConfirmation;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-28T09:50:10.750+02:00", comments="Source field: client_instruction.timestamp")
    private Long timestamp;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-28T09:50:10.749+02:00", comments="Source Table: client_instruction")
    public ClientInstructionRecord(Long id, Long examId, String connectionToken, String type, String attributes, Integer needsConfirmation, Long timestamp) {
        this.id = id;
        this.examId = examId;
        this.connectionToken = connectionToken;
        this.type = type;
        this.attributes = attributes;
        this.needsConfirmation = needsConfirmation;
        this.timestamp = timestamp;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-28T09:50:10.749+02:00", comments="Source field: client_instruction.id")
    public Long getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-28T09:50:10.749+02:00", comments="Source field: client_instruction.exam_id")
    public Long getExamId() {
        return examId;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-28T09:50:10.749+02:00", comments="Source field: client_instruction.connection_token")
    public String getConnectionToken() {
        return connectionToken;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-28T09:50:10.749+02:00", comments="Source field: client_instruction.type")
    public String getType() {
        return type;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-28T09:50:10.749+02:00", comments="Source field: client_instruction.attributes")
    public String getAttributes() {
        return attributes;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-28T09:50:10.750+02:00", comments="Source field: client_instruction.needs_confirmation")
    public Integer getNeedsConfirmation() {
        return needsConfirmation;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2021-04-28T09:50:10.750+02:00", comments="Source field: client_instruction.timestamp")
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_instruction
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
        sb.append(", examId=").append(examId);
        sb.append(", connectionToken=").append(connectionToken);
        sb.append(", type=").append(type);
        sb.append(", attributes=").append(attributes);
        sb.append(", needsConfirmation=").append(needsConfirmation);
        sb.append(", timestamp=").append(timestamp);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_instruction
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
        ClientInstructionRecord other = (ClientInstructionRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getExamId() == null ? other.getExamId() == null : this.getExamId().equals(other.getExamId()))
            && (this.getConnectionToken() == null ? other.getConnectionToken() == null : this.getConnectionToken().equals(other.getConnectionToken()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getAttributes() == null ? other.getAttributes() == null : this.getAttributes().equals(other.getAttributes()))
            && (this.getNeedsConfirmation() == null ? other.getNeedsConfirmation() == null : this.getNeedsConfirmation().equals(other.getNeedsConfirmation()))
            && (this.getTimestamp() == null ? other.getTimestamp() == null : this.getTimestamp().equals(other.getTimestamp()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_instruction
     *
     * @mbg.generated Wed Apr 28 09:50:10 CEST 2021
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getExamId() == null) ? 0 : getExamId().hashCode());
        result = prime * result + ((getConnectionToken() == null) ? 0 : getConnectionToken().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getAttributes() == null) ? 0 : getAttributes().hashCode());
        result = prime * result + ((getNeedsConfirmation() == null) ? 0 : getNeedsConfirmation().hashCode());
        result = prime * result + ((getTimestamp() == null) ? 0 : getTimestamp().hashCode());
        return result;
    }
}
package com.aas.ssw.business.entity;

import java.io.Serializable;

/**
 * @author xl
 */
public class KpiInfo implements Serializable {
    private static final long serialVersionUID = -6560034559953903414L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kpi_info.id
     *
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kpi_info.name
     *
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kpi_info.frequency
     *
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    private String frequency;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kpi_info.level
     *
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    private String level;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kpi_info.target
     *
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    private Double target;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kpi_info.createTime
     *
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kpi_info.updateTime
     *
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    private String updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kpi_info.owner
     *
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    private String owner;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kpi_info.pageId
     *
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    private String pageId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kpi_info.targetPro
     *
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    private String targetPro;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kpi_info.description
     *
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kpi_info.id
     *
     * @return the value of kpi_info.id
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kpi_info.id
     *
     * @param id the value for kpi_info.id
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kpi_info.name
     *
     * @return the value of kpi_info.name
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kpi_info.name
     *
     * @param name the value for kpi_info.name
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kpi_info.frequency
     *
     * @return the value of kpi_info.frequency
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kpi_info.frequency
     *
     * @param frequency the value for kpi_info.frequency
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    public void setFrequency(String frequency) {
        this.frequency = frequency == null ? null : frequency.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kpi_info.level
     *
     * @return the value of kpi_info.level
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    public String getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kpi_info.level
     *
     * @param level the value for kpi_info.level
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kpi_info.target
     *
     * @return the value of kpi_info.target
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    public Double getTarget() {
        return target;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kpi_info.target
     *
     * @param target the value for kpi_info.target
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    public void setTarget(Double target) {
        this.target = target;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kpi_info.createTime
     *
     * @return the value of kpi_info.createTime
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kpi_info.createTime
     *
     * @param createTime the value for kpi_info.createTime
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kpi_info.updateTime
     *
     * @return the value of kpi_info.updateTime
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kpi_info.updateTime
     *
     * @param updateTime the value for kpi_info.updateTime
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kpi_info.owner
     *
     * @return the value of kpi_info.owner
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    public String getOwner() {
        return owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kpi_info.owner
     *
     * @param owner the value for kpi_info.owner
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kpi_info.pageId
     *
     * @return the value of kpi_info.pageId
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kpi_info.pageId
     *
     * @param pageId the value for kpi_info.pageId
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    public void setPageId(String pageId) {
        this.pageId = pageId == null ? null : pageId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kpi_info.targetPro
     *
     * @return the value of kpi_info.targetPro
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    public String getTargetPro() {
        return targetPro;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kpi_info.targetPro
     *
     * @param targetPro the value for kpi_info.targetPro
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    public void setTargetPro(String targetPro) {
        this.targetPro = targetPro == null ? null : targetPro.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kpi_info.description
     *
     * @return the value of kpi_info.description
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kpi_info.description
     *
     * @param description the value for kpi_info.description
     * @mbg.generated Fri Mar 09 11:01:41 CST 2018
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}
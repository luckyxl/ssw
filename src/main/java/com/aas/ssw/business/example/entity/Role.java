package com.aas.ssw.business.example.entity;

public class Role {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.id
     *
     * @mbg.generated Wed Apr 18 15:25:40 CST 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.name
     *
     * @mbg.generated Wed Apr 18 15:25:40 CST 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.status
     *
     * @mbg.generated Wed Apr 18 15:25:40 CST 2018
     */
    private Integer status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.id
     *
     * @return the value of role.id
     *
     * @mbg.generated Wed Apr 18 15:25:40 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.id
     *
     * @param id the value for role.id
     *
     * @mbg.generated Wed Apr 18 15:25:40 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.name
     *
     * @return the value of role.name
     *
     * @mbg.generated Wed Apr 18 15:25:40 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.name
     *
     * @param name the value for role.name
     *
     * @mbg.generated Wed Apr 18 15:25:40 CST 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.status
     *
     * @return the value of role.status
     *
     * @mbg.generated Wed Apr 18 15:25:40 CST 2018
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.status
     *
     * @param status the value for role.status
     *
     * @mbg.generated Wed Apr 18 15:25:40 CST 2018
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}
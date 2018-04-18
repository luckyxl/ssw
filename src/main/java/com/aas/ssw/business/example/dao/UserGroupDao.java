package com.aas.ssw.business.example.dao;

import com.aas.ssw.business.example.entity.UserGroup;

import java.util.List;

public interface UserGroupDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_group
     *
     * @mbg.generated Wed Apr 18 15:26:59 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_group
     *
     * @mbg.generated Wed Apr 18 15:26:59 CST 2018
     */
    int insert(UserGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_group
     *
     * @mbg.generated Wed Apr 18 15:26:59 CST 2018
     */
    int insertSelective(UserGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_group
     *
     * @mbg.generated Wed Apr 18 15:26:59 CST 2018
     */
    UserGroup selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_group
     *
     * @mbg.generated Wed Apr 18 15:26:59 CST 2018
     */
    int updateByPrimaryKeySelective(UserGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_group
     *
     * @mbg.generated Wed Apr 18 15:26:59 CST 2018
     */
    int updateByPrimaryKey(UserGroup record);


    List<Integer> findGroupIdListByUserId(Integer userId);
}
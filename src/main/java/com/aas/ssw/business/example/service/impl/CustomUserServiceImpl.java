package com.aas.ssw.business.example.service.impl;

import com.aas.ssw.business.example.dao.GroupRoleDao;
import com.aas.ssw.business.example.dao.ResourceDao;
import com.aas.ssw.business.example.dao.RoleResourceDao;
import com.aas.ssw.business.example.dao.UserDao;
import com.aas.ssw.business.example.dao.UserGroupDao;
import com.aas.ssw.business.example.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.List;

public class CustomUserServiceImpl implements UserDetailsService {
    @Resource
    private UserDao userDao;
    @Resource
    private UserGroupDao userGroupDao;
    @Resource
    private GroupRoleDao groupRoleDao;
    @Resource
    private RoleResourceDao roleResourceDao;
    @Resource
    private ResourceDao resourceDao;



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findByLoginName(s);
        if(user == null){
            throw new UsernameNotFoundException("用户: " + s + " 不存在!");
        }
        List<Integer> groupIdList = userGroupDao.findGroupIdListByUserId(user.getId());
        List<Integer> roleIdList = groupRoleDao.findRoleIdListByGroupIdList(groupIdList);
        List<Integer> resourceIdList = roleResourceDao.findResourceIdListByRoleIdList(roleIdList);
        List<com.aas.ssw.business.example.entity.Resource> resourceList = resourceDao.findByResourceIdList(resourceIdList);
        return null;
    }
}

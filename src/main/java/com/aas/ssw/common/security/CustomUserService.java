package com.aas.ssw.common.security;

import com.aas.ssw.business.example.dao.*;
import com.aas.ssw.business.example.entity.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component("customUserService")
@ConditionalOnProperty(name = "security.enabled")
public class CustomUserService implements UserDetailsService {

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
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        User user = userDao.findByLoginName(loginName);
        if (user == null) {
            throw new UsernameNotFoundException("用户: " + loginName + " 不存在!");
        }
        List<Integer> groupIdList = userGroupDao.findGroupIdListByUserId(user.getId());
        List<Integer> roleIdList = groupRoleDao.findRoleIdListByGroupIdList(groupIdList);
        List<Integer> resourceIdList = roleResourceDao.findResourceIdListByRoleIdList(roleIdList);
        List<com.aas.ssw.business.example.entity.Resource> resourceList = resourceDao.findByResourceIdList(resourceIdList);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (com.aas.ssw.business.example.entity.Resource resource : resourceList) {
            if (resource != null && resource.getName() != null) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(resource.getName());
                //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                grantedAuthorities.add(grantedAuthority);
            }
        }
        return new org.springframework.security.core.userdetails.User(user.getLoginName(), user.getPassword(), grantedAuthorities);
    }
}

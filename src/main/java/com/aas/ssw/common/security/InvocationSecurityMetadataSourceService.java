package com.aas.ssw.common.security;

import com.aas.ssw.business.example.dao.ResourceDao;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

@Component("filterInvocationSecurityMetadataSource")
@ConditionalOnProperty(name = "security.enabled")
public class InvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
    @Resource
    private ResourceDao resourceDao;
    private Map<String, List<ConfigAttribute>> map;

    /**
     * 加载资源表中所有资源
     */
    @PostConstruct
    private void loadAllResource() {
        map = resourceDao.findAll().stream().collect(groupingBy(com.aas.ssw.business.example.entity.Resource::getUrl, mapping(res -> {
            ConfigAttribute securityConfig = new SecurityConfig(res.getName());
            return securityConfig;
        }, toList())));
        /*for (com.aas.ssw.business.example.entity.Resource resource : resourceList) {
            //此处只添加了资源的名字，其实还可以添加更多的信息，
            //例如请求方法到ConfigAttribute的集合中去。
            //此处添加的信息将会作为MyAccessDecisionManager类的decide的第三个参数。
            ConfigAttribute securityConfig = new SecurityConfig(resource.getName());
            List<ConfigAttribute> configAttributeList = map.get(resource.getUrl());
            if(configAttributeList == null || configAttributeList.size() == 0){
                configAttributeList = new ArrayList<>();
                configAttributeList.add(securityConfig);
                //用资源的getUrl() 作为map的key，用ConfigAttribute的集合作为 value，
                map.put(resource.getUrl(), configAttributeList);
            }else {
                configAttributeList.add(securityConfig);
            }
        }*/
    }

    /**
     * 此方法是为了判定用户请求的url是否在资源表中，
     * 如果在资源表中，则返回给 decide 方法，用来判定用户是否有此权限。
     * 如果不在权限表中则放行。
     *
     * @param o
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        for (String resourceUrl : map.keySet()) {
            AntPathRequestMatcher matcher = new AntPathRequestMatcher(resourceUrl);
            if (matcher.matches(request)) {
                return map.get(resourceUrl);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

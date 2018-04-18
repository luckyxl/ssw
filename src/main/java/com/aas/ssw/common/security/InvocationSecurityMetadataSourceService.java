package com.aas.ssw.common.security;

import com.aas.ssw.business.example.dao.ResourceDao;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
    @Resource
    private ResourceDao resourceDao;
    private ConcurrentHashMap<String, List<ConfigAttribute>> map = new ConcurrentHashMap<>();

    /**
     * 加载资源表中所有资源
     */
    @PostConstruct
    private void loadAllResource() {
        List<com.aas.ssw.business.example.entity.Resource> resourceList = resourceDao.findAll();
        for (com.aas.ssw.business.example.entity.Resource resource : resourceList) {
            List<ConfigAttribute> array = new ArrayList<>();
            ConfigAttribute cfg = new SecurityConfig(resource.getName());
            //此处只添加了资源的名字，其实还可以添加更多的信息，
            //例如请求方法到ConfigAttribute的集合中去。
            //此处添加的信息将会作为MyAccessDecisionManager类的decide的第三个参数。
            array.add(cfg);
            //用资源的getUrl() 作为map的key，用ConfigAttribute的集合作为 value，
            map.put(resource.getUrl(), array);
        }
    }

    /**
     * 此方法是为了判定用户请求的url是否在资源表中，
     * 如果在资源表中，则返回给 decide 方法，用来判定用户是否有此权限。
     * 如果不在权限表中则放行。
     * @param o
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        for(Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
            String resUrl = iter.next();
            AntPathRequestMatcher matcher = new AntPathRequestMatcher(resUrl);
            if(matcher.matches(request)) {
                return map.get(resUrl);
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

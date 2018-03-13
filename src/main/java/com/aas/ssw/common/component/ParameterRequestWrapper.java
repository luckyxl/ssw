package com.aas.ssw.common.component;

import org.apache.catalina.util.ParameterMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Map;

/**
 * 该类用于包装request
 *
 * @author xl
 */
@SuppressWarnings("unchecked")
public class ParameterRequestWrapper extends HttpServletRequestWrapper {

    private ParameterMap<String, String[]> params;

    @SuppressWarnings("all")
    public ParameterRequestWrapper(HttpServletRequest request) {
        super(request);
        params = (ParameterMap) request.getParameterMap();
    }

    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return params;
    }

    public void addParameter(String name, Object value) {//增加参数
        if (value != null) {
            params.setLocked(false);
            if (value instanceof String[]) {
                params.put(name, (String[]) value);
            } else if (value instanceof String) {
                params.put(name, new String[]{(String) value});
            } else {
                params.put(name, new String[]{String.valueOf(value)});
            }
            params.setLocked(true);
        }
    }
}

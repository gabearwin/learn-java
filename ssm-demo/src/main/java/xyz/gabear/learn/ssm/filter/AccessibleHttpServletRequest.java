package xyz.gabear.learn.ssm.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

public class AccessibleHttpServletRequest extends HttpServletRequestWrapper {
    private Map<String, String[]> params = new HashMap<>();

    public AccessibleHttpServletRequest(HttpServletRequest request) {
        super(request);
        this.params.putAll(request.getParameterMap());
    }

    public AccessibleHttpServletRequest(HttpServletRequest request, Map<String, String[]> paramMap) {
        super(request);
        this.params.putAll(paramMap);
    }

    @Override
    public String getParameter(String name) {
        if (params.get(name) != null) {
            return params.get(name)[0];
        }
        return super.getParameter(name);
    }

    @Override
    public String[] getParameterValues(String name) {
        return params.get(name);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return params;
    }
}

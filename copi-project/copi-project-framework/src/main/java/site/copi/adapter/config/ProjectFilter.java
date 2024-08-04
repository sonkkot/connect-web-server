package site.copi.adapter.config;


import org.springframework.stereotype.Component;
import site.copi.security.filter.CopiApiFilterProvider;

@Component
public class ProjectFilter implements CopiApiFilterProvider {
    private final String api = "/api/project";
    private final String[] targetApi = {
        api,
        api + "/**",
    };
    private final String[] allowList = {
        api + "/all"
    };
    private final String[] authenticationEndPoints = {
        api + "/none"
    };

    @Override
    public String[] targetApi() {
        return targetApi;
    }

    @Override
    public String[] allowList() {
        return allowList;
    }

    @Override
    public String[] authenticatedEndPoints() {
        return authenticationEndPoints;
    }
}
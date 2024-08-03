package site.copi.security.filter.domain;

public interface CopiApiFilterProvider {
    String targetApi();

    String[] allowList();

    String[] authenticatedEndPoints();
}
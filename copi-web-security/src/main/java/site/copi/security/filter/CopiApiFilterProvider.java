package site.copi.security.filter;

public interface CopiApiFilterProvider {
    String[] targetApi();

    String[] allowList();

    String[] authenticatedEndPoints();
}
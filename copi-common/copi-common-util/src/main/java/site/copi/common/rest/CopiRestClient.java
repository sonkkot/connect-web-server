package site.copi.common.rest;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClient;

import java.net.URI;

public class CopiRestClient {
    private final RestClient.RequestBodySpec restClient;

    private CopiRestClient(RestClient.RequestBodySpec restClient) {
        this.restClient = restClient;
    }

    public static CopiRestClientPerform perform(final HttpMethod method, final String uri) {
        final var copiRestClient = createRestClient(method, URI.create(uri));
        return CopiRestClientPerform.init(copiRestClient.restClient);
    }

    public static CopiRestClientPerform perform(final HttpMethod method, final URI uri) {
        final var copiRestClient = createRestClient(method, uri);
        return CopiRestClientPerform.init(copiRestClient.restClient);
    }

    private static CopiRestClient createRestClient(final HttpMethod method, final URI uri) {
        return new CopiRestClient(
            RestClient.create()
                .method(method)
                .uri(uri)
        );
    }
}
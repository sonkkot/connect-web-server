package site.copi.common.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
public class CopiRestClientPerform {
    private final RestClient.RequestBodySpec bodySpec;

    private CopiRestClientPerform(RestClient.RequestBodySpec bodySpec) {
        this.bodySpec = bodySpec;
    }

    public static CopiRestClientPerform init(RestClient.RequestBodySpec bodySpec) {
        return new CopiRestClientPerform(bodySpec);
    }

    public CopiRestClientPerform addHeader(final String key, final String value) {
        bodySpec.header(key, value);

        return this;
    }

    public <T, R> R retrieve(final T body, final Class<R> responseType) {
        return bodySpec
            .contentType(APPLICATION_JSON)
            .body(body)
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError, clientErrorHandle())
            .onStatus(HttpStatusCode::is5xxServerError, serverErrorHandle())
            .body(responseType);
    }

    private RestClient.ResponseSpec.ErrorHandler clientErrorHandle() {
        return (request, response) -> {
            log.warn("[   CopiRestClientPerform   ] ⏬️ 🟠 4xxClientError.Request Method: {}, URI: {}",
                request.getMethod().name(), request.getURI());

            log.warn("[   CopiRestClientPerform   ] ⏫️ 🟠 4xxClientError.Response status: {}, message: {}",
                response.getStatusCode(), readFromInputStream(response.getBody()));

            // TODO : throw..
        };
    }

    private RestClient.ResponseSpec.ErrorHandler serverErrorHandle() {
        return (request, response) -> {
            log.warn("[   CopiRestClientPerform   ] ⏬️ 🟠 5xxClientError.Request Method: {}, URI: {}",
                request.getMethod().name(), request.getURI());

            log.warn("[   CopiRestClientPerform   ] ⏫️ 🟠 5xxClientError.Response status: {}, message: {}",
                response.getStatusCode(), readFromInputStream(response.getBody()));

            // TODO : throw..
        };
    }

    private String readFromInputStream(final InputStream inputStream) throws IOException {
        try (var br = new BufferedReader(new InputStreamReader(inputStream))) {
            final var resultStringBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder
                    .append(line)
                    .append(System.lineSeparator());
            }

            return resultStringBuilder.toString();
        }
    }
}
package com.company.international.serenity.steps.backend;

import static com.company.core.common.EnvironmentProperty.BASE_MAIL_API_URL;
import static com.company.core.common.UrlController.buildUri;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.company.core.logging.Logger;

public class WebserviceSteps {

    private String defaultMailUrl;
    private HttpClient client;

    protected WebserviceSteps() {
        this.defaultMailUrl = BASE_MAIL_API_URL.readProperty();
        this.client = HttpClientBuilder.create().build();
    }

    protected HttpResponse sendGetRequest(final String extraPath) {
        final HttpGet httpRequest = new HttpGet(buildUri(defaultMailUrl, extraPath));
        try {
            return client.execute(httpRequest);
        } catch (final IOException e) {
            Logger.out.debug("Unable to send request! Details: {}", e.getMessage());
            return null;
        }
    }

    public String convertInputStreamToString(final InputStream stream) {
        try {
            return IOUtils.toString(stream, StandardCharsets.UTF_8);
        } catch (final IOException e) {
            Logger.out.debug("Unable to convert InputStream to String! Details: {}", e.getMessage());
            return StringUtils.EMPTY;
        }
    }
}

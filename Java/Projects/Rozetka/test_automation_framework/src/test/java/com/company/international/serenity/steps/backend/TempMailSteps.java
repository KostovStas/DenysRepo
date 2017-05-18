package com.company.international.serenity.steps.backend;

import static com.company.util.HtmlUtil.getActivationLinkFromHtml;
import static com.company.util.Md5HashCounter.countMd5HashFrom;
import static java.lang.String.format;

import java.io.IOException;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;

public class TempMailSteps extends WebserviceSteps {

    private static final String GET_MAIL_PARTIAL_PATH = "/request/mail/id/md5/%s/format/html/";

    public TempMailSteps() {
        super();
    }

    public String getActivationLink(final String mail) {
        final String md5Hash = countMd5HashFrom(mail);
        Objects.requireNonNull(md5Hash, "There is no 'MD5' hash found!");
        final HttpResponse httpResponse = sendGetRequest(format(GET_MAIL_PARTIAL_PATH, md5Hash));
        String stringFromInputStream;
        try {
            stringFromInputStream = convertInputStreamToString(httpResponse.getEntity().getContent());
            return getActivationLinkFromHtml(stringFromInputStream);
        } catch (final IOException e) {
            return StringUtils.EMPTY;
        }
    }
}

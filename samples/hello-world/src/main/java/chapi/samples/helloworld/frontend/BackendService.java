package chapi.samples.helloworld.frontend;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.json.JsonFactory;
import com.google.common.net.UrlEscapers;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

/**
 * Created by Matt on 12/01/14.
 */
public class BackendService {
  public class Greeting {
    public chapi.samples.helloworld.frontend.Greeting greet(String name) throws IOException {
      return requestFactory
          .buildGetRequest(new GenericUrl(
              baseUrl + "/backend/greeting/" + escapePath(name)))
          .setParser(jsonFactory.createJsonObjectParser())
          .execute()
          .parseAs(chapi.samples.helloworld.frontend.Greeting.class);
    }

    private String escapePath(String text) {
      return UrlEscapers.urlPathSegmentEscaper().escape(text);
    }
  }

  public class Name {
    public String generateName() throws IOException {
      return requestFactory.buildGetRequest(new GenericUrl(baseUrl + "/backend/name"))
                           .execute()
                           .parseAsString();
    }
  }

  private final String baseUrl;
  private final HttpRequestFactory requestFactory;
  private final JsonFactory jsonFactory;

  @Inject
  public BackendService(@Named("backend.base.url") String baseUrl, HttpRequestFactory requestFactory,
                        JsonFactory jsonFactory) {
    this.baseUrl = baseUrl;
    this.requestFactory = requestFactory;
    this.jsonFactory = jsonFactory;
  }

  public Greeting greeting() {
    return new Greeting();
  }

  public Name name() {
    return new Name();
  }
}

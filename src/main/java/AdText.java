import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by akjohri on 7/28/2017.
 */
public class AdText {
    private long id;

    private String heading;
    private String body;
    private String url;
    private String key;

    public AdText() {}
    public AdText(long id, String heading, String body, String url, String key) {
        this.id = id;
        this.heading = heading;
        this.body = body;
        this.url = url;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getHeading() {
        return heading;
    }

    @JsonProperty
    public String getBody() {
        return body;
    }

    @JsonProperty
    public String getUrl() {
        return url;
    }

    @JsonProperty
    public String getKey() {
        return key;
    }
}

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Created by akjohri on 7/28/2017.
 */
@Path("/service")
public class ServiceResource {
    public ServiceResource() {

    }

    @GET
    @Timed
    public AdText getAd(@QueryParam("id") Optional<Long> id) {
        return new AdText(11l, "Dummy Ad", "Random Ad body", "www.bing.com");
    }
}

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created by akjohri on 7/28/2017.
 */
@Path("/service")
@Produces(MediaType.APPLICATION_JSON)
public class ServiceResource {
    public ServiceResource() {

    }

    @GET
    @Timed
    public AdText getAd(@QueryParam("id") Optional<Long> id) {
        ReadBC.send();
        return new AdText(11l, "Dummy Ad", "Random Ad body", "www.bing.com");
    }
}

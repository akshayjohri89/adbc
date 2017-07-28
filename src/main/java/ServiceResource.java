import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.common.base.Optional;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import net.minidev.json.JSONObject;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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
        String result = ReadBC.send();
        System.out.println("Result:"+result);

        ObjectMapper om = new ObjectMapper();
        try {
            List hm = om.readValue(result, List.class);
            System.out.println("HM: " + hm.get(0));
        } catch (IOException e) {
            System.out.println("OM Error:"+ e.getMessage());
            e.printStackTrace();
        }
        return new AdText(11l, "Dummy Ad", "Random Ad body", "www.bing.com");
    }
}

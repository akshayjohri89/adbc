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
        if (result!= null) {
            System.out.println("Result:"+result);
        }
        org.json.JSONObject jsonObject = new org.json.JSONObject(result);

        AdText toReturn =  new AdText(111,jsonObject.get("heading").toString(),
                jsonObject.getString("body").toString(),
                jsonObject.get("url").toString(),
                jsonObject.get("key").toString());
        if (jsonObject.get("score")!=null) {
            toReturn.setScore(jsonObject.get("score").toString());
        }
        return toReturn;
        //return new AdText(11l, "Dummy Ad", "Random Ad body", "www.bing.com");
    }
}

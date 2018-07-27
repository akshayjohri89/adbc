import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.common.base.Optional;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import net.minidev.json.JSONObject;
import org.json.JSONException;


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
        org.json.JSONObject jsonObject = new org.json.JSONObject();
        String score = "";
        int count = 0;
        while ((score.equals("")||score.equals("0"))&&(count<5)) {
            String result = ReadBC.send();
            jsonObject = new org.json.JSONObject(result);
            try {
                score = jsonObject.get("score").toString();
            } catch (JSONException e) {
            } finally {
                count++;
            }
            System.out.println("getad: Got:"+jsonObject);
        }

        if (score.equals("")||score.equals("0")) {
            System.out.println("getad: Returning blank");
            return new AdText();
        }


        String imps="",clicks = "",advertiser="";
        try {
            clicks = jsonObject.get("clicks").toString();
        } catch (JSONException ex) {
        }
        try {
            imps = jsonObject.get("imps").toString();
        } catch (JSONException ex) {
        }
        try {
            advertiser = jsonObject.get("advertiser").toString();
        } catch (JSONException ex) {
        }

        AdText toReturn =  new AdText(111,
                advertiser,
                jsonObject.get("heading").toString(),
                jsonObject.getString("body").toString(),
                jsonObject.get("url").toString(),
                jsonObject.get("key").toString());
        if (jsonObject.get("score")!=null) {
            toReturn.setScore(jsonObject.get("score").toString());
        }
        toReturn.setImps(imps);
        toReturn.setClicks(clicks);
        return toReturn;
        //return new AdText(11l, "Dummy Ad", "Random Ad body", "www.bing.com");
    }
}

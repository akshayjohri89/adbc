import com.thetransactioncompany.jsonrpc2.JSONRPC2ParseException;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by akjohri on 7/28/2017.
 */
public class ReadBC {

    public static void send() {
        String method = "liststreamitems adstream1";
        String id = "";
        Map<String, Object> params = new HashMap<String, Object>();


        JSONRPC2Request request = new JSONRPC2Request(method, params, id);
        SocketStuff.sendData(request.toString());
    }
}

        // Parse response string
//        JSONRPC2Response response = null;
//        try {
//            response = JSONRPC2Response.parse(jsonString);
//        } catch (JSONRPC2ParseException e) {
//            System.out.println(e.getMessage());
//            // Handle exception...
//        }


// Check for success or error
//        if (response.indicatesSuccess()) {
//
//            System.out.println("The request succeeded :");
//
//            System.out.println("\tresult : " + response.getResult());
//            System.out.println("\tid     : " + response.getID());
//        }
//        else {
//            System.out.println("The request failed :");
//
//            JSONRPC2Error err = response.getError();
//
//            System.out.println("\terror.code    : " + err.getCode());
//            System.out.println("\terror.message : " + err.getMessage());
//            System.out.println("\terror.data    : " + err.getData());
//        }



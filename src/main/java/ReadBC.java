import com.thetransactioncompany.jsonrpc2.JSONRPC2ParseException;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by akjohri on 7/28/2017.
 */
public class ReadBC {

    public static String send() {
        try {
            Process process =
                    new ProcessBuilder(new String[] {"multichain-cli adchain1 liststreamitems adstream1"})
                            .redirectErrorStream(true)
                            .start();

            ArrayList<String> output = new ArrayList<String>();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line = null;
            while ( (line = br.readLine()) != null ) {
                output.add(line);
                System.out.println("Process:read:"+line);
            }

            //There should really be a timeout here.
            if (0 != process.waitFor())
                return null;

            return output.toString();

        } catch (Exception e) {
            //Warning: doing this is no good in high quality applications.
            //Instead, present appropriate error messages to the user.
            //But it's perfectly fine for prototyping.

            return null;
        }



//        String method = "liststreamitems adstream1";
//        String id = "multichainrpc";
//        Map<String, Object> params = new HashMap<String, Object>();
//
//
//        JSONRPC2Request request = new JSONRPC2Request(method, params, id);
//        SocketStuff.sendData(request.toString());
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



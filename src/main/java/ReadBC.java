import com.thetransactioncompany.jsonrpc2.JSONRPC2ParseException;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by akjohri on 7/28/2017.
 */
public class ReadBC {

    public static String send() {
                String method = "liststreamitems adstream1";
        String id = "1";
        Map<String, Object> params = new HashMap<String, Object>();
        JSONObject jsonObject = invokeRPC(id,method,null,"adchain1");
        return jsonObject.toString();
    }
//        try {
//            System.out.println("Trying to execute command");
//            Process process =
//                    new ProcessBuilder(new String[] {"multichain-cli", "adchain1", "liststreamitems", "adstream1"})
//                            .redirectErrorStream(true)
//                            .directory(new File("/home/akjohri/"))
//                            .start();
//
//            ArrayList<String> output = new ArrayList<String>();
//            BufferedReader br = new BufferedReader(
//                    new InputStreamReader(process.getInputStream()));
//            String line = null;
//            while ( (line = br.readLine()) != null ) {
//                output.add(line);
//                System.out.println("Process:read:"+line);
//            }
//
//            //There should really be a timeout here.
//            if (0 != process.waitFor())
//                return null;
//
//            output.subList(2,)
//            return output.toString();
//
//        } catch (Exception e) {
//            //Warning: doing this is no good in high quality applications.
//            //Instead, present appropriate error messages to the user.
//            //But it's perfectly fine for prototyping.
//            System.out.println("Exec error"+e.getMessage());
//            return null;
//        }


//
//        String method = "liststreamitems adstream1";
//        String id = "multichainrpc";
//        Map<String, Object> params = new HashMap<String, Object>();
//
//
//        JSONRPC2Request request = new JSONRPC2Request(method, params, id);
//        SocketStuff.sendData(request.toString());
//    }
//}
//
//         Parse response string
//        JSONRPC2Response response = null;
//        try {
//            response = JSONRPC2Response.parse(jsonString);
//        } catch (JSONRPC2ParseException e) {
//            System.out.println(e.getMessage());
//            // Handle exception...
//        }
//
//
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


        public static JSONObject invokeRPC (String id, String method, List< Object > params, String chainName){
            HttpClient httpClient = HttpClientBuilder.create().build();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", id);
            jsonObject.put("method", method);
            if (params != null && params.size() != 0) {
                jsonObject.put("params", params);
            }
            jsonObject.put("chain_name", chainName);
            JSONObject responseJSONObject = new JSONObject();
            try {
                CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                credentialsProvider.setCredentials(
                        new AuthScope("localhost", 7174),
                        new UsernamePasswordCredentials("multichainrpc", "7H3pMurCfk3jJKpUUmcqZDd74KkPzMJQffvXSBZ19v6U")
                );
                StringEntity myEntity = new StringEntity(jsonObject.toString());

                HttpPost httpPost = new
                        HttpPost("http://localhost:7174");       //HttpPost("http://multichainrpc:EZikv3MtoKA2yjrG6T7eTkPZMXntwr9k1ft7ja3jLLaA@192.168.1.6:9732");
                httpPost.setEntity(myEntity);

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();

                System.out.println("------------------");
                System.out.println(httpResponse.getStatusLine());

                if (httpEntity != null) {
                    System.out.println("Response content length: " + httpEntity.getContentLength());
                }

                responseJSONObject = new JSONObject().getJSONObject(EntityUtils.toString(httpEntity));

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                httpClient.getConnectionManager().shutdown();
            }

            return responseJSONObject;
        }
    }


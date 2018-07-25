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
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
/**
 * Created by akjohri on 7/28/2017.
 */
public class ReadBC {

    public static String send() {
        String method = "liststreamitems";
//        Random randomNum = new Random();
//        int index = min + randomNum.nextInt(2);
//        String id = String.valueOf(index);
        String id = "1";
        List<Object> params = new ArrayList<Object>();
        params.add("adstream1");
        return invokeRPC(id,method,params,"adchain1");
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


        public static String invokeRPC (String id, String method, List< Object > params, String chainName){
            String toReturn = null;
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
                        new AuthScope("localhost", 4356),
                        new UsernamePasswordCredentials("multichainrpc", "3WsnHdSsUFgp3umeQbd3Hd3mrvNbQQpPoPTs285Up8eV")
                );

                StringEntity myEntity = new StringEntity(jsonObject.toString());

                HttpPost httpPost = new
                        HttpPost("http://multichainrpc:3WsnHdSsUFgp3umeQbd3Hd3mrvNbQQpPoPTs285Up8eV@localhost:4356");       //HttpPost("http://multichainrpc:EZikv3MtoKA2yjrG6T7eTkPZMXntwr9k1ft7ja3jLLaA@192.168.1.6:9732");
                httpPost.setEntity(myEntity);

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();

                System.out.println("------------------");
                System.out.println(httpResponse.getStatusLine());

                if (httpEntity != null) {
                    System.out.println("Response content length: " + httpEntity.getContentLength());
                    String retSrc = EntityUtils.toString(httpEntity);
                    // parsing JSON
                    JSONObject result = new JSONObject(retSrc); //Convert String to JSON Object

                    JSONArray tokenList = result.getJSONArray("result");
                    int index = (int)(Math.random()*tokenList.length());
                    JSONObject oj = tokenList.getJSONObject(index);
                    System.out.println("original Object:"+oj.toString());
                    String data = oj.getString("data");
                    //Add Key

//                    toReturn = oj.getString("data");


                    StringBuilder output = new StringBuilder();
                    for (int i = 0; i < toReturn.length(); i+=2) {
                        String str = toReturn.substring(i, i+2);
                        output.append((char)Integer.parseInt(str, 16));
                    }
                    JSONObject returnJson = new JSONObject(output);
                    returnJson.put("key",oj.getString("key"));
                    return returnJson.toString();
//                    return output.toString();

                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                httpClient.getConnectionManager().shutdown();
            }

            return toReturn;
        }
    }


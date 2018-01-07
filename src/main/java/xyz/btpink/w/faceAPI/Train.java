package xyz.btpink.w.faceAPI;

import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.util.EntityUtils;

public class Train{

public final String subscriptionKey = "a3b2643bceee45c89a8f16f4457bf8b1";
public final String uriBase = "https://westcentralus.api.cognitive.microsoft.com/face/v1.0/persongroups/example-group-00/train";

    public void train(){
    	System.out.println("Train 진입");
    	String personGroupId = "example-group-00";
    	
        HttpClient httpclient = new DefaultHttpClient();

        try
        {
            URIBuilder builder = new URIBuilder(uriBase);


            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            
            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);
            String body = "{\"personGroupId\":\"" + personGroupId+ "\"}";


            // Request body
            StringEntity reqEntity = new StringEntity(body);
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
            	System.out.println(entity);
                System.out.println(EntityUtils.toString(entity).trim());
                System.out.println("train 응답 받음");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
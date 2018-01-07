package xyz.btpink.w.faceAPI;

import java.net.URI;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class Addperson {

	public final String subscriptionKey = "a3b2643bceee45c89a8f16f4457bf8b1";
	public final String uriBase = "https://westcentralus.api.cognitive.microsoft.com/face/v1.0/persongroups/example-group-00/persons";

	public String addPerson(String name) {
		HttpClient httpclient = new DefaultHttpClient();

		String personId = null;

		try {
			URIBuilder builder = new URIBuilder(uriBase);

			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);

			request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

			String jsonname = "{\"name\":\"" + name + "\"}";

			// Request body
			StringEntity reqEntity = new StringEntity(jsonname, "utf8");
			request.setEntity(reqEntity);

			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				System.out.println("REST Response:\n");
				String jsonstring = EntityUtils.toString(entity).trim();

				System.out.println(jsonstring);

				String arrayedJSON = "[" + jsonstring + "]";

				JSONArray jsonArray = null;
				JSONObject item = null;

				jsonArray = new JSONArray(arrayedJSON);

				for (int i = 0; i < jsonArray.length(); i++) {
					System.out.println("for문 진입!");
					item = jsonArray.getJSONObject(i);

					System.out.println(item);
					System.out.println(item.getString("personId"));
					personId = item.getString("personId");

				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return personId;

	}

}
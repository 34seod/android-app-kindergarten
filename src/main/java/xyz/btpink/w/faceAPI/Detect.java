package xyz.btpink.w.faceAPI;

//This sample uses the Apache HTTP client library(org.apache.httpcomponents:httpclient:4.2.4)
//and the org.json library (org.json:json:20170516).

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Detect {
	public final String subscriptionKey = "a3b2643bceee45c89a8f16f4457bf8b1";
	public final String uriBase = "https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect";
	public String faceId[];
	public String faceResult;
	public int len;
	
	String url;
	
	public Detect(String url){
		this.url = url;
	}
	
	public TreeMap<String, Double> map;
	TreeMap<String, xyz.btpink.w.vo.IdentfyVO> identifyMap;
	public Map<String, xyz.btpink.w.vo.IdentfyVO> getFaceId(String image) {
		HttpClient httpclient = new DefaultHttpClient();
		try {
			xyz.btpink.w.vo.IdentfyVO identfy = new xyz.btpink.w.vo.IdentfyVO();
			HashMap<String, xyz.btpink.w.vo.IdentfyVO> detectMap = new HashMap<>();
			Identfy identy = new Identfy();
			URIBuilder builder = new URIBuilder(uriBase);

			// Request parameters. All of them are optional.
			builder.setParameter("returnFaceId", "true");
			builder.setParameter("returnFaceLandmarks", "false");
			builder.setParameter("returnFaceAttributes",
					"age,gender,headPose,smile,facialHair,glasses,emotion,hair,makeup,occlusion,accessories,blur,exposure,noise");

			// Prepare the URI for the REST API call.
			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);

			// Request headers.
			request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);
			// Request body.201708271503837703388.jpg
			// 마이크로 소프트로 이미지 주소 전달

			System.out.println(url);
			StringEntity reqEntity = new StringEntity(url);
			request.setEntity(reqEntity);

			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				// Format and display the JSON response.
				System.out.println("REST Response:\n");
				String jsonString = EntityUtils.toString(entity).trim();
				if (jsonString.charAt(0) == '[') {
					JSONArray jsonArray = new JSONArray(jsonString);
					System.out.println("json 길이 : "+jsonArray.length());
					len = jsonArray.length();
					ArrayList<String> list = new ArrayList<>();
					String[][] faceId = new String[len][2];
					
					//사람수만큼 for문으로 돌림
					for (int i = 0; i < len; i++) {
						//identfy 할 faeceid 저장 
						list.add("\"" + (String) jsonArray.getJSONObject(i).get("faceId") + "\"");
						//json 얼굴 데이터를 맵으로 넘김 
						map = new ObjectMapper().readValue(jsonArray.getJSONObject(0).getJSONObject("faceAttributes").getJSONObject("emotion").toString(), TreeMap.class);
						identfy.setFaceId((String) jsonArray.getJSONObject(i).get("faceId"));
						//크기별로 정렬 후 저장
						if(sortByValue(map) == null || sortByValue(map).equals("")){
							identfy.setEmotion("neutral");
							System.out.println("감정 뉴트럴 기본값 저장 완료");
						}else{
							identfy.setEmotion(sortByValue(map));
							System.out.println("감정 저장 완료!");
						}
						//faceId와 identfy 이모션 저장
						detectMap.put(identfy.getFaceId(), identfy);
					}
					
					//faceId Identfy클래스로 보냄
					identifyMap = identy.identfy(list);
					for (String merge : identifyMap.keySet()) {
						if(detectMap.containsKey(identifyMap.get(merge).getFaceId())){
							
							identifyMap.put(merge, new xyz.btpink.w.vo.IdentfyVO(merge, detectMap.get(identifyMap.get(merge).getFaceId()).getEmotion()));
						}
					}
					System.out.println(identifyMap);
					
				} else if (jsonString.charAt(0) == '{') {
					JSONObject jsonObject = new JSONObject(jsonString);
					System.out.println(jsonObject.toString(2));
				} else {
					System.out.println(jsonString);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.err.println("abs");
		}
		 return identifyMap;
	}
//이모션 정렬 메서드
	public String sortByValue(final Map<String, Double> map) {
		String temp = "";
		double d = 0.0;
		System.out.println(map);
			for ( String s : map.keySet()) {
				try{
				if(map.get(s)>d){
					d = map.get(s);
					temp = s;
				}
				}catch(Exception e){}
			}
			System.out.println(temp);
		return temp;
	}

}
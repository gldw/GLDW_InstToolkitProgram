package vdab.extnodes.envirodiy;

import java.io.InputStream;
import java.net.HttpURLConnection;

import com.lcrc.af.AnalysisData;
import com.lcrc.af.AnalysisEvent;
import com.lcrc.af.constants.HTTPMethodType;
import com.lcrc.af.util.IconUtility;

import vdab.api.node.HTTPService_A;

public class MyWatershedService extends HTTPService_A {
	private static final String API_ENDPOINT = "http://data.envirodiy.org/api/data-stream/"; 
//	private static final String API_ENDPOINT = "http://lcrc.com:31161/vdab/testdiy"; 


	private String c_Token;
	private String c_SamplingFeature;
	private String c_DataID;
	
	
	public Integer get_IconCode(){
		return  IconUtility.getIconHashCode("node_envirodiy");
	}
	public String get_Token(){
		return c_Token;
	}
	public void set_Token(String id){
		c_Token = id;
	}
	public String get_SamplingFeature(){
		return c_SamplingFeature;
	}
	public void set_SamplingFeature(String key){
		c_SamplingFeature = key;
	}
	
	public String get_DataID(){
		return c_DataID;
	}
	public void set_DataID(String id){
		c_DataID = id;
	}
	public void _init(){
		super._init();
		set_HTTPMethod(Integer.valueOf(HTTPMethodType.POST));
	}
	@Override
	public String buildCompleteURL(AnalysisEvent ev) {
		StringBuilder sb = new StringBuilder();
		sb.append(API_ENDPOINT);	
		return sb.toString();
	}
	public void processReturnStream(AnalysisEvent inEvent, int retCode, InputStream is) {
		serviceResponse(inEvent, new AnalysisEvent(this, new AnalysisData("RetCode", Integer.valueOf(retCode))));
		/*
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		String line;
		StringBuilder sb = new StringBuilder();
		try {
			while ((line = in.readLine()) != null)
				sb.append(line);	
			AnalysisData ad = JsonUtility.convertJsonToAnalysisData("M2XResponse",sb.toString());
			serviceResponse(inEvent, new AnalysisEvent(this, ad));
		}
		catch (Exception e){
			serviceFailed(inEvent, 3);
		}
		*/
	}
	@Override
	public String buildPost(AnalysisEvent inEvent){		
		long ts = inEvent.getTimestamp();
		AnalysisData ad = getSelectedData(inEvent);
		if (!ad.isSimple()){
			setWarning("Could not build post for complex event, change selection to simple data event DATA="+ad);
			return null;
		}
		String post = MyWatershedDataUtility.createMyWatershedJson(c_SamplingFeature, ts, c_DataID, ad);
		logTrace("JSON="+post);
		return post;
	}
	@Override
	public void setupHTTPConnection(HttpURLConnection con)  {
		con.setRequestProperty("TOKEN", c_Token);
		con.setRequestProperty("Content-Type","application/json");	
	}
}

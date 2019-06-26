package vdab.extnodes.envirodiy;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import com.lcrc.af.AnalysisDataDef;
import com.lcrc.af.constants.SpecialText;
import com.lcrc.af.util.ControlDataBuffer;
import com.lcrc.af.util.IconUtility;

import vdab.api.node.HTTPService_A;
import vdab.core.nodes.http.HTTPResponseHandler_I;

public class MayflyControlService extends HTTPService_A implements HTTPResponseHandler_I{
	static {
		DataHubType.getEnum();
		MayflySensorType.getEnum();
	}
	private String c_Token;
	private String c_SamplingFeature;
	private String c_MayflyLocation;
	private Integer c_DataHubType;


	private ControlDataBuffer c_cdb_EnabledSensors = new ControlDataBuffer("MayflyServer.EnabledSensors");
	private ControlDataBuffer c_cdb_DataIDs = new ControlDataBuffer("MayflyServer.DataIDs");
	
	public Integer get_IconCode(){
		return  IconUtility.getIconHashCode("node_mayfly");
	}
	public String get_NodeLabel(){
		return super.get_NodeLabel();
	}
	public String get_MayflyLocation(){
		return c_MayflyLocation;
	}
	public void set_MayflyLocation(String loc){
		c_MayflyLocation = loc;
	}
	
	public Integer get_DataHubType(){
		return c_DataHubType;		
	}
	public void set_DataHubType(Integer type){
		c_DataHubType = type;		
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
	public String get_EnabledSensors(){
		return c_cdb_EnabledSensors.getAllSet(",");
	}
	public void set_EnabledSensors(String s){
		if (s.contains(",")){
			 c_cdb_EnabledSensors.setOnly(s,","); 	
		} 
		// Clear command from option picker
		else if (s.equals(SpecialText.CLEAR)){
			c_cdb_EnabledSensors.clear();
		}
		// One value to add.
		else {
			c_cdb_EnabledSensors.set(s);
		}
	}
	public String get_DataIDs(){
		return c_cdb_DataIDs.getAllSet(",");
	}
	public void set_DataIDs(String s){
		if (s.contains(",")){
			 c_cdb_DataIDs.setOnly(s,","); 	
		} 
		// Clear command from option picker
		else if (s.equals(SpecialText.CLEAR)){
			 c_cdb_DataIDs.clear();
		}
		// One value to add.
		else {
			 c_cdb_DataIDs.set(s);
		}
	}
	public AnalysisDataDef def_DataIDs(AnalysisDataDef theDataDef){
		
		ArrayList<String> l = new ArrayList<String>();
		if (!c_cdb_DataIDs.isEmpty() )
			l.add(SpecialText.CLEAR);

		theDataDef.setAllPickValues(l.toArray(new String[l.size()]));
		return theDataDef;
	}

	@Override
	public void processRunnerResponse(int reqCode, String retMsg, InputStream retStream) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHTTPRequestRunnerConnection(int reqCode, HttpURLConnection connection) {
		// TODO Auto-generated method stub
		
	}

}

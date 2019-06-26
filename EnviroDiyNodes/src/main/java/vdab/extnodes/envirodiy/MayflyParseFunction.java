package vdab.extnodes.envirodiy;

import com.lcrc.af.function.AnalysisFunction;
import com.lcrc.af.util.IconUtility;

public class MayflyParseFunction extends AnalysisFunction {
	static {
		DataHubType.getEnum();
		MayflySensorType.getEnum();
	}
	private String c_Token;
	private String c_SamplingFeature;
	private String c_MayflyLocation;
	private Integer c_DataHubType;

	
	public Integer get_IconCode(){
		return  IconUtility.getIconHashCode("node_mayfly");
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

}

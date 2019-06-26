package vdab.extnodes.envirodiy;

import com.lcrc.af.datatypes.AFEnum;

public class DataHubType {
	public final static int UNDEFINED = 0;
	public final static int ENVIRODIY_HTTP = 1;
	public final static int VDAB_HTTP = 2;
	public final static int VDAB_MQTT = 3;
	public final static int VDAB_SERIAL = 4;
	public final static int THINGSPEAK_MQTT = 5;
	public final static int M2X_HTTP = 6;
	
	private static AFEnum s_EnumDataHubType = new AFEnum("DataHubType")
	.addEntry(DataHubType.ENVIRODIY_HTTP, "EnviroDIY Server")
	.addEntry(DataHubType.VDAB_HTTP, "VDAB HTTP")
	.addEntry(DataHubType.VDAB_MQTT, "VDAB MQTT")
	.addEntry(DataHubType.VDAB_SERIAL, "VDAB Serial")
	.addEntry(DataHubType.THINGSPEAK_MQTT, "ThingSpeak MQTT")
	.addEntry(DataHubType.M2X_HTTP, "AT&T M2X HTTP")
	;
	public static AFEnum getEnum(){
		return s_EnumDataHubType ;
	}
}

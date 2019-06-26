package vdab.extnodes.envirodiy;

import com.lcrc.af.datatypes.AFEnum;

public class MayflySensorType {
	public final static int UNDEFINED = 0;
	public final static int PRESSURE = 1;
	public final static int TEMPERATURE = 2;
	public final static int HUMIDITY = 3;
	public final static int CONDUCTIVITY = 4;
	public final static int ANALOG_1 = 5;
	public final static int ANALOG_2 = 6;
	public final static int ANALOG_3 = 7;	
	public final static int ANALOG_4 = 8;	
	
	private static AFEnum s_EnumMayflySensorType= new AFEnum("MayflySensorType")
	.addEntry(MayflySensorType.PRESSURE, "Barometric Pressure")
	.addEntry(MayflySensorType.TEMPERATURE, "Temperature")
	.addEntry(MayflySensorType.HUMIDITY, "Humidity")
	.addEntry(MayflySensorType.CONDUCTIVITY, "Conductivity")
	.addEntry(MayflySensorType.ANALOG_1, "Analog_1")
	.addEntry(MayflySensorType.ANALOG_2, "Analog_2")
	.addEntry(MayflySensorType.ANALOG_3, "Analog_3")
	.addEntry(MayflySensorType.ANALOG_4, "Analog_4")
	;
	public static AFEnum getEnum(){
		return s_EnumMayflySensorType;
	}
}

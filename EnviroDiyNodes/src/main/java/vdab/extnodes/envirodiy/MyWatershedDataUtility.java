/*LICENSE*
 * Copyright (C) 2013 - 2018 MJA Technology LLC 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package vdab.extnodes.envirodiy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.lcrc.af.AnalysisData;

public class MyWatershedDataUtility {
	private final static String SPACES ="\n                                                           ";
	private static DateFormat DATEFORMAT = getMyWatershedDateFormat();
	private static DateFormat getMyWatershedDateFormat() {
		// Use ISO8601 date format.
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");	
	//	df.setTimeZone(TimeZone.getTimeZone("UTC"));	
		return df;
	}
	private static String getFormattedTime(long ts) {
		String timeStr = DATEFORMAT.format(new Date(ts));
		return timeStr;
	}
	public static String createMyWatershedJson(String feature,  long ts, String id, AnalysisData ad) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n{");
		sb.append("\n\t\"sampling_feature\": \"").append(feature).append("\",");
		sb.append("\n\t\"timestamp\": \"").append(getFormattedTime(ts)).append("\",");
		addDataItem(sb, id, ad);
		sb.append("\n}");
		return sb.toString();
	}
	public static void addDataItem(StringBuilder sb, String id, AnalysisData ad) {
		sb.append("\n\t\"").append(id).append("\": \"");
		sb.append(ad.getDataAsString()).append("\"");
	}


	
	// AD to M2X JSON ------------------------------------------------------------
	private static void buildJSON(StringBuilder sb, String timeStr, int level, AnalysisData ad){
		level++;
		if (ad.isSimple()){
			addJSONForAD(sb, timeStr, level, ad.getLabel(), ad.getDataAsString());
		}
		else {
			//addJSONStartForACD(sb, level, ad.getLabel());
			AnalysisData[] childAds = ad.getChildData();
			for (int n = 0; n < childAds.length; n++) {
				if (n > 0)
					sb.append(",");
				buildJSON(sb, timeStr, level, childAds[n]);
				
			}
			//addJSONEndForACD(sb, level, ad.getLabel());
		}
	}
	
	private static void addIndent(StringBuilder sb, int level){
		sb.append(SPACES.substring(0,level*4+1));
	}
	private static void addJSONStartForACD(StringBuilder sb, int level, String tag){
		addIndent(sb, level);
		sb.append("\"");
		sb.append(tag);
		sb.append("\": {");
	}
	private static void addJSONEndForACD(StringBuilder sb, int level, String tag){
		addIndent(sb, level);
		sb.append("}");
	}
	private static void addJSONForAD(StringBuilder sb, String timeStr, int level, String tag, String value){
		addIndent(sb, level);

		sb.append(" \"");
		sb.append(tag);
		sb.append("\": [ { ");
		sb.append("\"timestamp\": \"");
		sb.append(timeStr).append("Z");
		sb.append("\" , \"value\": \"");
		sb.append(value);
		sb.append("\" } ]");
	}

}

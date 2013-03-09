package com.mariaruchko.etldiff;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Transformation {
	private final static String TABLE_INPUT = "TableInput";
	private final static String DIMENSION_LOOKUP = "DimensionLookup";
	private final static String SCRIPT_VALUE_LOOKUP="ScriptValueMod";
	private final static String DB_LOOKUP="DBLookup";
	private final static String SYSTEM_INFO="SystemInfo";
	private final static String TABLE_OUTPUT="TableOutput";
	private final static String SELECT_VALUES="SelectValues";
	private String name;
	private String directory;
	private List<Step> mSteps;



	public Transformation(Element element) {
		Element transformationInfo = element.select("info").first();
		if (transformationInfo != null){
			Element transformationName = transformationInfo.select("name").first();
			name = transformationName.text();
			
			Element transformationDir = transformationInfo.select("directory").first();
			directory = transformationDir.text();
		}
		
		Step newStep = null;
		Elements stepElements = element.select("step");
		for (Element stepElement : stepElements) {
			String type = stepElement.select("type").first().text();

			if (type.equalsIgnoreCase(TABLE_INPUT)) {
				newStep = new TableInputStep(stepElement);
			} else if (type.equalsIgnoreCase(DIMENSION_LOOKUP)) {
				newStep = new DimensionLookup(stepElement);
			} else if (type.equalsIgnoreCase(SCRIPT_VALUE_LOOKUP)) {
				newStep = new ScriptValueMod(stepElement);
			}else if (type.equalsIgnoreCase(DB_LOOKUP)) {
				newStep = new DBLookup(stepElement);
			}else if (type.equalsIgnoreCase(SYSTEM_INFO)) {
				newStep = new SystemInfo(stepElement);
			}else if (type.equalsIgnoreCase(TABLE_OUTPUT)) {
				newStep = new TableOutput(stepElement);
			}else if (type.equalsIgnoreCase(SELECT_VALUES)) {
				newStep = new SelectValues(stepElement);
			}else {
				newStep = new Step();
			}

			newStep.setName(stepElement.select("name").first().text());
			newStep.setType(stepElement.select("type").first().text());
			
			if (mSteps == null){
				this.mSteps = new ArrayList<Step>();
			}

			mSteps.add(newStep);	
		}
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDirectory() {
		return directory;
	}



	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public List<Step> getSteps(){
		return mSteps;
	}
}

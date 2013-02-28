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
	private String name;
	private String directory;
	private List<Step> mSteps;



	public Transformation(Element transformation) {
		Element transformationInfo = transformation.getElementsByTag("info").first();
		if (transformationInfo!=null){
			Element transformationName = transformationInfo.getElementsByTag("name").first();
			name = transformationName.text();
			Element transformationDir = transformationInfo.getElementsByTag("directory").first();
			directory = transformationDir.text();

		}
		Elements stepsFromXML = transformation.getElementsByTag("step");
		for(Element stepFromXML : stepsFromXML){
			String type = stepFromXML.getElementsByTag("type").first().text();
			Step newStep = null;

			if (type.equalsIgnoreCase(TABLE_INPUT)) {
				newStep = new TableInputStep(stepFromXML);
			} else if (type.equalsIgnoreCase(DIMENSION_LOOKUP)) {
				newStep = new DimensionLookup(stepFromXML);
			} else if (type.equalsIgnoreCase(SCRIPT_VALUE_LOOKUP)) {
				newStep = new ScriptValueMod(stepFromXML);
			}else if (type.equalsIgnoreCase(DB_LOOKUP)) {
				newStep = new DBLookup(stepFromXML);
			}else {
				newStep = new Step();
			}

			newStep.setName(stepFromXML.getElementsByTag("name").first().text());
			newStep.setType(stepFromXML.getElementsByTag("type").first().text());
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

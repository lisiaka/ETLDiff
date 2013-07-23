package com.mariaruchko.etldiff;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
	private final static String STREAM_LOOKUP="StreamLookup";
	private final static String TEXT_FILE_OUTPUT="TextFileOutput";
	private final static String FILTER_ROWS="FilterRows";
	private final static String EXEC_SQL="ExecSQL";
	private final static String GET_VARIABLE="GetVariable";
	
	private String name;
	private String directory;
	private Map<String,Step> mSteps;



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
			}else if (type.equalsIgnoreCase(STREAM_LOOKUP)) {
				newStep = new StreamLookup(stepElement);
			}else if (type.equalsIgnoreCase(TEXT_FILE_OUTPUT)) {
				newStep = new TextFileOutput(stepElement);
			}else if (type.equalsIgnoreCase(FILTER_ROWS)) {
				newStep = new FilterRows(stepElement);
			}else if (type.equalsIgnoreCase(EXEC_SQL)) {
				newStep = new ExecSQL(stepElement);
			}else {
				newStep = new Step();
			}

			newStep.setName(stepElement.select("name").first().text());
			newStep.setType(type);
			newStep.setTransformationName(this.getName());
			newStep.setStepElement(stepElement);

			if (mSteps == null){
				this.mSteps = new HashMap<String,Step>();
			}

			mSteps.put(newStep.getName(),newStep);	
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

	public Map<String,Step> getSteps(){
		return mSteps;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mSteps.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;

		if (obj == this) {
			result = true;
		} else if (obj == null || obj.getClass() != this.getClass()) {
			result = false;
		} else {
			Transformation trans = (Transformation) obj;

			Map<String,Step> otherSteps = trans.getSteps();
			result = mSteps.equals(otherSteps);
		}

		return result;
		/*
                 (name == trans.getName() 
                     || (name != null && name.equals(trans.getName())))
                && (directory == trans.getDirectory() 
                     || (directory != null && directory.equals(trans.getDirectory())));
		 */
	}

	public Boolean hasThesameName(Transformation transformation){
		Boolean result=false;
		if (name!=null){
			result=name.equals(transformation.getName());
		}
		return result;
	}



	public String compare(Transformation transformation) {
		String result="";
		if(this.equals(transformation)){
			result="Transformations are identical";
		}
		else{
			System.out.println("Compare transformation "+this.name+" to transformation "
					+transformation.getName()+" - "+this.getSteps().size()+ " steps against "+transformation.getSteps().size());



			Set<String> sameSteps = new HashSet<String>();

			for(String stepName: transformation.getSteps().keySet()){
				Step step=transformation.getSteps().get(stepName);

				if(this.getSteps().get(stepName)!=null){
					result=result+step.compare(this.getSteps().get(stepName))+(step.compare(this.getSteps().get(stepName))==""?"":"\n");
					sameSteps.add(stepName);
				}

			}



			if(sameSteps.size()!=transformation.getSteps().keySet().size()){
				result=result+Format.formatAsHeader("New steps: ",4);
				String tempResult="";
			for(String stepName: transformation.getSteps().keySet() ){
				
				if(!sameSteps.contains(stepName)){
					String divId=this.name+"_"+stepName;
					tempResult=tempResult+Format.formatAsListItem(Format.formatAsLinkToUnfold(stepName+" ("+transformation.getSteps().get(stepName).getType()+")",divId)
							+Format.formatAsDiv(transformation.getSteps().get(stepName).printProperties(), divId))+"\n";
				}
			}
			tempResult=Format.formatAsList(tempResult);
			result=result+tempResult;
			}

			if(sameSteps.size()!=this.getSteps().keySet().size()){
			result=result+Format.formatAsHeader("Missing steps: ",4);
			String tempResult="";
			for(String stepName: this.getSteps().keySet() ){
				if(!sameSteps.contains(stepName)){
					String divId=this.name+"_"+stepName;
					tempResult=tempResult+Format.formatAsListItem(Format.formatAsLinkToUnfold(stepName+" ("+this.getSteps().get(stepName).getType()+")",divId)
							+Format.formatAsDiv(this.getSteps().get(stepName).printProperties(), divId))+"\n";
				}
			}
			tempResult=Format.formatAsList(tempResult);
			result=result+tempResult;
			}

		}


		// TODO Auto-generated method stub
		return result;
	}

}

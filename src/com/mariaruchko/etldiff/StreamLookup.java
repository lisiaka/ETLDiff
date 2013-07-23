package com.mariaruchko.etldiff;

import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class StreamLookup extends Step {
	String fromStep;
	Set<TheKey> keys;
	Set<LookupField> values;
	
	class TheKey{

		public String getName() {
			return name;
		}

		public String getField() {
			return field;
		}



		String name;
		String field;


		public TheKey(Element valueFromXML) {

			name=valueFromXML.getElementsByTag("name").first().text();
			if(valueFromXML.getElementsByTag("field").first()!=null){
				field=valueFromXML.getElementsByTag("field").first().text();
			}

			// TODO Auto-generated constructor stub
		}

		public String printTheKey(){
			return "key: "+Format.formatWordInsideParagraph(name+"<->"+field,Format.getIdCodeInText());
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((field == null) ? 0 : field.hashCode());

			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			if (obj == null || obj.getClass() != this.getClass()) {
				return false;
			}

			TheKey mTheKey=(TheKey)obj;
			return 

			(name == mTheKey.getName()
					|| (name != null && name.equals(mTheKey.getName())))
					&& (field == mTheKey.getField()
							|| (field != null && field.equals(mTheKey.getField())))
							;

		}

		public String compare(TheKey theKey){
			String result=null;
			if(!field.equals(theKey.getField())){
				result=result+"Different keys "+Format.formatWordInsideParagraph(this.printTheKey(),Format.getIdCodeInText())+ " vs "
				+Format.formatWordInsideParagraph(theKey.printTheKey(),Format.getIdCodeInText());
			}
			return result;

		}

	}

	public StreamLookup(Element stepFromXML) {
		fromStep=stepFromXML.getElementsByTag("from").first().text();
		Elements valuesFromXML=stepFromXML.getElementsByTag("value");
		Elements keysFromXML=stepFromXML.getElementsByTag("key");
		
		if (values==null){
			values=new HashSet<LookupField>();
		}
		for(Element valueFromXML: valuesFromXML){
			LookupField value= new LookupField(valueFromXML);
			values.add(value);

		};
		
		
		if (keys==null){
			keys=new HashSet<TheKey>();
		}
		for(Element keyFromXML:keysFromXML){
			TheKey key= new TheKey(keyFromXML);
			keys.add(key);
		}
		
		
	}
	
	public String printProperties() {
		String printValues="";
		for(LookupField value:this.values){
			printValues+=Format.formatAsListItem(value.printLookupField());
		}
		printValues=Format.formatAsList(printValues);
		
		String printKeys="";
		for(TheKey key:this.keys){
			printKeys+=Format.formatAsListItem(key.printTheKey());
		}
		printKeys=Format.formatAsList(printKeys);
		
		return Format.formatAsParagraph(this.getName()+": "+this.getType()+"; lookup from step: "
				+Format.formatWordInsideParagraph(fromStep,Format.getIdCodeInText())+Format.formatAsHeader("keys: ",5)+printKeys
				+Format.formatAsHeader("values: ",5)+printValues,"");
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}

		StreamLookup mStreamLookup=(StreamLookup)obj;
		return 

		(fromStep == mStreamLookup.getFromStep()
						|| (fromStep != null && fromStep.equals(mStreamLookup.getFromStep())))
						&& (keys == mStreamLookup.getKeys()
								|| (keys != null && keys.equals(mStreamLookup.getKeys())))
								&& (values == mStreamLookup.getValues()
										|| (values != null && values.equals(mStreamLookup.getValues())))
										;

	}
	

	public String getFromStep() {
		return fromStep;
	}

	public Set<TheKey> getKeys() {
		return keys;
	}

	public Set<LookupField> getValues() {
		return values;
	}

	@Override
	public String compare(Step step) {
		String result="";
		if (step == this) {
			//     result = step.getName()+" and "+this.getName() +" steps are identical ";
		}
		if (step == null || step.getClass() != this.getClass()) {
			result = "Steps are of different types";
		}

		StreamLookup mStreamLookup=(StreamLookup)step;

		
		if(!fromStep.equals(mStreamLookup.getFromStep())){
			result=result+"Different \"lookup from\" step "+Format.formatWordInsideParagraph(fromStep,Format.getIdNameInText())
			+ " vs "+Format.formatWordInsideParagraph(mStreamLookup.getFromStep(),Format.getIdNameInText())+" in "+mStreamLookup.getName();
		}
		if(!keys.equals(mStreamLookup.getKeys())){
			result=result+"Different keys  in "+mStreamLookup.getName();

			Set<TheKey> sameKeys = new HashSet<TheKey>();

			for(TheKey key: mStreamLookup.getKeys()){			
				if(!this.getKeys().contains(key)){					
					sameKeys.add(key);
				}

			}

			if(sameKeys.size()!=mStreamLookup.getKeys().size()){
				result=result+"New keys: ";
				String tempResult="";
				for(TheKey key: mStreamLookup.getKeys() ){
					
					if(!sameKeys.contains(key)){
						tempResult=tempResult+Format.formatAsListItem(key.printTheKey())+"\n";
					}
				}
				result=result+Format.formatAsList(tempResult);
			}

			if(sameKeys.size()!=this.getKeys().size()){
				result=result+"Missing keys: ";
				String tempResult="";
				for(TheKey key: this.getKeys()){
					if(!sameKeys.contains(key)){
						tempResult=tempResult+Format.formatAsListItem(key.printTheKey())+"\n";
					}
				}
				result=result+Format.formatAsList(tempResult);
			}

		}



		if(!values.equals(mStreamLookup.getValues())){
			result=result+"Different values in "+mStreamLookup.getName();
		}
		else{
			//result=mDBLookup.getName()+" steps are identical.";
		}
		// TODO Auto-generated method stub
		return result;
	}
}

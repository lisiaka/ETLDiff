package com.mariaruchko.etldiff;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class SystemInfo extends Step {
	List<Field> fields;
	
	class Field{
		String name;
		String type;
		
		Field(Element fieldFromXML){
			if(fieldFromXML.getElementsByTag("name").first()!=null){
				name=fieldFromXML.getElementsByTag("name").first().text();
			}
			if(fieldFromXML.getElementsByTag("type").first()!=null){
				type=fieldFromXML.getElementsByTag("type").first().text();
			}
		}
		
		public String getField(){
			return "field: "+name+"; type: "+type+" ";
		}
	}
	
	SystemInfo(Element stepFromXML){
		Elements fieldsFromXML=stepFromXML.getElementsByTag("field");
		if (fields==null){
			fields=new ArrayList<Field>();
		}
		for(Element fieldFromXML: fieldsFromXML){
			Field field= new Field(fieldFromXML);
			fields.add(field);

		};
	}
	
	public String printProperties() {
		String printFields="";
		for(Field field:this.fields){
			printFields+=field.getField();
		}
		
		return this.getName()+": "+this.getType()+"; fields: "+printFields;
	}
}

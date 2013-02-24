package com.mariaruchko.etldiff;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class DimensionLookup extends Step {


	String table;
	String connection;
	String dateFromField;
	String dateToField;
	List<Field> fields;

	class Field{

		String name;
		String lookup;
		String update;
		String key;

		public Field(Element fieldFromXML, String string) {

			name=fieldFromXML.getElementsByTag("name").first().text();
			lookup=fieldFromXML.getElementsByTag("lookup").first().text();
			if(fieldFromXML.getElementsByTag("update").first()!=null){
				update=fieldFromXML.getElementsByTag("update").first().text();
			}
			key=string;
			// TODO Auto-generated constructor stub
		}

		public String getField(){
			return "name: "+name+"; "+lookup+"; update: "+update+"; key: "+key;
		}
	}

	public DimensionLookup(Element stepFromXML) {
		connection=stepFromXML.getElementsByTag("connection").first().text();
		table=stepFromXML.getElementsByTag("table").first().text();
		Elements fieldsFromXML=stepFromXML.getElementsByTag("field");
		if (fields==null){
			fields=new ArrayList<Field>();
		}
		for(Element fieldFromXML: fieldsFromXML){
			Field field= new Field(fieldFromXML, "");
			fields.add(field);

		};

		dateFromField=stepFromXML.getElementsByTag("fields").first().getElementsByTag("date").first().getElementsByTag("from").first().text();
		dateToField=stepFromXML.getElementsByTag("fields").first().getElementsByTag("date").first().getElementsByTag("to").first().text();
		Elements keysFromXML=stepFromXML.getElementsByTag("key");
		for(Element keyFromXML:keysFromXML){
			Field field= new Field(keyFromXML, "key");
			fields.add(field);
		}

		// TODO Auto-generated constructor stub
	}

	public String getProperties() {
		String fields="";
		for(Field field:this.fields){
			fields+=field.getField();
		}
		return this.getName()+": "+this.getType()+"; connection: "+connection+"; table: "+table+"; fields: "+fields;
	}
}

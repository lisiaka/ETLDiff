package com.mariaruchko.etldiff;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;




public class DBLookup extends Step {
	private String connection;
	private String table;
	List<TheKey> keys;
	List<TheValue> values;

	class TheKey{

		String name;
		String field;
		String condition;

		public TheKey(Element valueFromXML) {

			name=valueFromXML.getElementsByTag("name").first().text();
			if(valueFromXML.getElementsByTag("field").first()!=null){
				field=valueFromXML.getElementsByTag("field").first().text();
			}
			if(valueFromXML.getElementsByTag("condition").first()!=null){
				condition=valueFromXML.getElementsByTag("condition").first().text();
			}
			// TODO Auto-generated constructor stub
		}

		public String getTheKey(){
			return "key: "+name+condition+field;
		}
	}

	class TheValue{

		String name;
		String rename;

		public TheValue(Element valueFromXML) {

			name=valueFromXML.getElementsByTag("name").first().text();
			if(valueFromXML.getElementsByTag("rename").first()!=null){
				rename=valueFromXML.getElementsByTag("rename").first().text();
			}

			// TODO Auto-generated constructor stub
		}

		public String getTheValue(){
			return "name: "+name+"; rename: "+rename;
		}
	}

	public DBLookup(Element stepFromXML) {
		connection=stepFromXML.getElementsByTag("connection").first().text();
		table=stepFromXML.getElementsByTag("table").first().text();
		Elements valuesFromXML=stepFromXML.getElementsByTag("value");
		if (values==null){
			values=new ArrayList<TheValue>();
		}
		for(Element valueFromXML: valuesFromXML){
			TheValue value= new TheValue(valueFromXML);
			values.add(value);

		};

		Elements keysFromXML=stepFromXML.getElementsByTag("key");
		if (keys==null){
			keys=new ArrayList<TheKey>();
		}
		for(Element keyFromXML:keysFromXML){
			TheKey key= new TheKey(keyFromXML);
			keys.add(key);
		}
	}
	
	public String getProperties() {
		String printValues="";
		for(TheValue value:this.values){
			printValues+=value.getTheValue();
		}
		String printKeys="";
		for(TheKey key:this.keys){
			printKeys+=key.getTheKey();
		}
		return this.getName()+": "+this.getType()+"; connection: "+connection+"; table: "+table+"; keys: "+printKeys+"; values: "+printValues;
	}
}

package com.mariaruchko.etldiff;

import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DBLookup extends Step {
	private String connection;
	private String table;
	Set<TheKey> keys;
	Set<LookupField> values;

	class TheKey{

		public String getName() {
			return name;
		}

		public String getField() {
			return field;
		}

		public String getCondition() {
			return condition;
		}

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

		public String printTheKey(){
			return "key: "+name+condition+field;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((field == null) ? 0 : field.hashCode());
			result = prime * result + ((condition == null) ? 0 : condition.hashCode());

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
							&& (condition == mTheKey.getCondition()
									|| (condition != null && condition.equals(mTheKey.getCondition())))
									;

		}

		public String compare(TheKey theKey){
			String result=null;
			if((!field.equals(theKey.getField()))||(!condition.equals(theKey.getCondition()))){
				result=result+"Different keys "+this.printTheKey()+ " vs "+theKey.printTheKey();
			}
			return result;

		}

	}

	public DBLookup(Element stepFromXML) {
		connection=stepFromXML.getElementsByTag("connection").first().text();
		table=stepFromXML.getElementsByTag("table").first().text();
		Elements valuesFromXML=stepFromXML.getElementsByTag("value");
		if (values==null){
			values=new HashSet<LookupField>();
		}
		for(Element valueFromXML: valuesFromXML){
			LookupField value= new LookupField(valueFromXML);
			values.add(value);

		};

		Elements keysFromXML=stepFromXML.getElementsByTag("key");
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
			printValues+=value.printLookupField();
		}
		String printKeys="";
		for(TheKey key:this.keys){
			printKeys+=key.printTheKey();
		}
		return this.getName()+": "+this.getType()+"; connection: "+connection+"; table: "+table+"; keys: "+printKeys+"; values: "+printValues;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((connection == null) ? 0 : connection.hashCode());
		result = prime * result + ((table == null) ? 0 : table.hashCode());
		result = prime * result + ((keys == null) ? 0 : keys.hashCode());
		result = prime * result + ((values == null) ? 0 : values.hashCode());
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

		DBLookup mDBLookup=(DBLookup)obj;
		return 

		(connection == mDBLookup.getConnection() 
				|| (connection != null && connection.equals(mDBLookup.getConnection())))
				&& (table == mDBLookup.getTable()
						|| (table != null && table.equals(mDBLookup.getTable())))
						&& (keys == mDBLookup.getKeys()
								|| (keys != null && keys.equals(mDBLookup.getKeys())))
								&& (values == mDBLookup.getValues()
										|| (values != null && values.equals(mDBLookup.getValues())))
										;

	}

	public String getConnection() {
		return connection;
	}

	public String getTable() {
		return table;
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

		DBLookup mDBLookup=(DBLookup)step;

		if(!connection.equals(mDBLookup.getConnection())){
			result=result+"Different connections "+connection+ " vs "+mDBLookup.getConnection()+" in "+mDBLookup.getName();
		}
		if(!table.equals(mDBLookup.getTable())){
			result=result+"Different tables "+table+ " vs "+mDBLookup.getTable()+" in "+mDBLookup.getName();
		}
		if(!keys.equals(mDBLookup.getKeys())){
			result=result+"Different keys  in "+mDBLookup.getName();

			Set<TheKey> sameKeys = new HashSet<TheKey>();

			for(TheKey key: mDBLookup.getKeys()){			
				if(!this.getKeys().contains(key)){					
					sameKeys.add(key);
				}

			}

			if(sameKeys.size()!=mDBLookup.getKeys().size()){
				result=result+"New keys: ";
				String tempResult="";
				for(TheKey key: mDBLookup.getKeys() ){
					
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



		if(!values.equals(mDBLookup.getValues())){
			result=result+"Different values in "+mDBLookup.getName();
		}
		else{
			//result=mDBLookup.getName()+" steps are identical.";
		}
		// TODO Auto-generated method stub
		return result;
	}
}

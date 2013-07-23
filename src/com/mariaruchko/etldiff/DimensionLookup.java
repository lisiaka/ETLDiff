package com.mariaruchko.etldiff;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class DimensionLookup extends Step {


	String table;
	String connection;
	String dateFromField;
	String dateToField;
	Set<Field> fields;

	class Field{

		public String getName() {
			return name;
		}

		public String getLookup() {
			return lookup;
		}

		public String getUpdate() {
			return update;
		}

		public String getKey() {
			return key;
		}


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
		
		@Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((name == null) ? 0 : name.hashCode());
	        result = prime * result + ((lookup == null) ? 0 : lookup.hashCode());
	        result = prime * result + ((update == null) ? 0 : update.hashCode());
	        result = prime * result + ((key == null) ? 0 : key.hashCode());
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

	        Field mField=(Field)obj;
	        return 
	        
	                 (name == mField.getName() 
	                     || (name != null && name.equals(mField.getName())))
	                && (lookup == mField.getLookup()
	                     || (lookup != null && lookup.equals(mField.getLookup())))
	                && (update == mField.getUpdate()
	                     || (update != null && update.equals(mField.getUpdate())))
	                && (key == mField.getKey()
	                     || (key != null && key.equals(mField.getKey())))
	               
	                     ;
	                     
	    }
		

		public String printField(){
			return "name: "+name+"; "+lookup+"; update: "+update+"; key: "+key;
		}
	}

	public DimensionLookup(Element stepFromXML) {
		connection=stepFromXML.getElementsByTag("connection").first().text();
		table=stepFromXML.getElementsByTag("table").first().text();
		Elements fieldsFromXML=stepFromXML.getElementsByTag("field");
		if (fields==null){
			fields=new HashSet<Field>();
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

	public String printProperties() {
		String fields="";
		for(Field field:this.fields){
			fields+=field.printField();
		}
		return this.getName()+": "+this.getType()+"; connection: "+connection+"; table: "+table+"; fields: "+fields;
	}

	public String getTable() {
		return table;
	}

	public String getConnection() {
		return connection;
	}

	public String getDateFromField() {
		return dateFromField;
	}

	public String getDateToField() {
		return dateToField;
	}

	public Set<Field> getFields() {
		return fields;
	}
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((connection == null) ? 0 : connection.hashCode());
        result = prime * result + ((table == null) ? 0 : table.hashCode());
        result = prime * result + ((fields == null) ? 0 : fields.hashCode());
        result = prime * result + ((dateFromField == null) ? 0 : dateFromField.hashCode());
        result = prime * result + ((dateToField == null) ? 0 : dateToField.hashCode());
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

        DimensionLookup mDimLookup=(DimensionLookup)obj;
        return 
        
                 (connection == mDimLookup.getConnection() 
                     || (connection != null && connection.equals(mDimLookup.getConnection())))
                && (table == mDimLookup.getTable()
                     || (table != null && table.equals(mDimLookup.getTable())))
                && (dateFromField == mDimLookup.getDateFromField()
                     || (dateFromField != null && dateFromField.equals(mDimLookup.getDateFromField())))
                && (dateToField == mDimLookup.getDateToField()
                     || (dateToField != null && dateToField.equals(mDimLookup.getDateToField())))
                && (fields == mDimLookup.getFields()
                     || (fields != null && fields.equals(mDimLookup.getFields())))
                     ;
                     
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

        DimensionLookup mDimensionLookup=(DimensionLookup)step;
       
		if(!connection.equals(mDimensionLookup.getConnection())){
			result=result+"Different connections "+connection+ " vs "+mDimensionLookup.getConnection()+" in "+mDimensionLookup.getName();
		}
		if(!table.equals(mDimensionLookup.getTable())){
			result=result+"Different tables "+table+ " vs "+mDimensionLookup.getTable()+" in "+mDimensionLookup.getName();
		}
		if(!fields.equals(mDimensionLookup.getFields())){
			result=result+"Different fields in "+mDimensionLookup.getName();
		}
		if(!dateFromField.equals(mDimensionLookup.getDateFromField())){
			result=result+"Different dateFrom "+dateFromField+" vs "+mDimensionLookup.getDateFromField()+" in "+mDimensionLookup.getName();
		}
		if(!dateToField.equals(mDimensionLookup.getDateToField())){
			result=result+"Different dateTo "+dateToField+" vs "+mDimensionLookup.getDateToField()+" in "+mDimensionLookup.getName();
		}
		else{
			//result=mDimensionLookup.getName()+" steps are identical.";
		}
		// TODO Auto-generated method stub
		return result;
	}
}

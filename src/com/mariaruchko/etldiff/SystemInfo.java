package com.mariaruchko.etldiff;

import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class SystemInfo extends Step {
	
	public Set<Field> getFields() {
		return fields;
	}

	Set<Field> fields;
	
	class Field{
		public String getName() {
			return name;
		}

		public String getType() {
			return type;
		}

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
		@Override
		public int hashCode() {
		    final int prime = 31;
		    int result = 1;
		    result = prime * result + ((name == null) ? 0 : name.hashCode());
		    result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		    
		             (name== mField.getName()
		                 || (name != null && name.equals(mField.getName())))
		            && (type == mField.getType()
		                 || (type != null && type.equals(mField.getType())));
		                 
		}
		
		public String printField(){
			return "field: "+name+"; type: "+type+" ";
		}
	}
	
	SystemInfo(Element stepFromXML){
		Elements fieldsFromXML=stepFromXML.getElementsByTag("field");
		if (fields==null){
			fields=new HashSet<Field>();
		}
		for(Element fieldFromXML: fieldsFromXML){
			Field field= new Field(fieldFromXML);
			fields.add(field);

		};
	}
	
	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((fields == null) ? 0 : fields.hashCode());
	    
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

	    SystemInfo mSystemInfo=(SystemInfo)obj;
	    return 
	    
	             (fields== mSystemInfo.getFields()
	                 || (fields != null && fields.equals(mSystemInfo.getFields())));
	            
	}
	
	public String printProperties() {
		String printFields="";
		for(Field field:this.fields){
			printFields+=field.printField();
		}
		
		return this.getName()+": "+this.getType()+"; fields: "+printFields;
	}
}

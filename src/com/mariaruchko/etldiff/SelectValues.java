package com.mariaruchko.etldiff;

import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;




public class SelectValues extends Step {
	
	public Set<LookupField> getmFields() {
		return mFields;
	}

	public Set<LookupField> getmMetaFields() {
		return mMetaFields;
	}

	Set<LookupField> mFields;
	Set<LookupField> mMetaFields;

	public SelectValues(Element element){
		Elements fieldElements = element.select("field");
		if (fieldElements != null) {
			for (Element fieldElement : fieldElements) {
				if (mFields == null){
					this.mFields = new HashSet<LookupField>();
				}

				LookupField field = new LookupField(fieldElement);
				mFields.add(field);
			}
		}

		Elements metaElements = element.select("meta");
		if (metaElements != null) {
			for (Element metaElement : metaElements){
				LookupField metaField = new LookupField(metaElement);

				if (mMetaFields == null){
					this.mMetaFields = new HashSet<LookupField>();
				}
				mMetaFields.add(metaField);
			}
		}
	}

	public String printProperties() {
		String result = null;
		
		StringBuilder sbFields = null;
		if (mFields != null) {
			for(LookupField field : this.mFields){
				if (sbFields == null) {
					sbFields = new StringBuilder();
				}
				sbFields.append(field.printLookupField());
			}
		}
		
		String fields = null;
		if (sbFields != null) {
			fields = sbFields.toString();
		}
		
		StringBuilder sbMetaFields = null;
		if (mMetaFields != null) {
			for(LookupField metaField : this.mMetaFields){
				if (sbMetaFields == null) {
					sbMetaFields = new StringBuilder();
				}
				sbMetaFields.append(metaField.printLookupField());
			}
		}
		
		String metaFields = null;
		if (sbMetaFields != null) {
			metaFields = sbMetaFields.toString();
		}
		result = this.getName() +
			": " +
			this.getType() +
			(fields != null ? "; fields:" + fields : "") +
			(metaFields != null ? "; meta:" + metaFields : "");
		return result;
	}
	
	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((mFields == null) ? 0 : mFields.hashCode());
	    result = prime * result + ((mMetaFields == null) ? 0 : mMetaFields.hashCode());
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

	    SelectValues mSelectValues=(SelectValues)obj;
	    return 
	    
	             (mFields== mSelectValues.getmFields()
	                 || (mFields != null && mSelectValues.getmFields()!=null && mFields.equals(mSelectValues.getmFields())))
	            && (mMetaFields == mSelectValues.getmMetaFields()
	                 || (mMetaFields != null && mSelectValues.getmMetaFields()!=null && mMetaFields.equals(mSelectValues.getmMetaFields())));
	                 
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

        SelectValues selectValues=(SelectValues)step;
       
		
		if(selectValues.getmFields()!=null && !mFields.equals(selectValues.getmFields())){
			String divId=this.getTransformationName()+"_"+this.getName()+"_fields";
			result=result+Format.formatAsLinkToUnfold("Different fields in "+this.getName(),divId);
			Set<LookupField> sameFields = new HashSet<LookupField>();
			for(LookupField field: selectValues.getmFields()){				
				if(this.getmFields().contains(field)){					
					sameFields.add(field);
				}
				
			}
			String fieldsSet1="";
			String fieldsSet2="";
			if(sameFields.size()!=selectValues.getmFields().size()){
				 fieldsSet1=fieldsSet1+Format.formatAsHeader("Missing fields: ",4);
				String tempResult="";
			for(LookupField field: selectValues.getmFields() ){
				if(!sameFields.contains(field)){
					tempResult=tempResult+Format.formatAsListItem(field.printLookupField())+"\n";
				}
			}
			tempResult=Format.formatAsList(tempResult);
			fieldsSet1=fieldsSet1+tempResult;
			}
			
			if(sameFields.size()!=this.getmFields().size()){
				 fieldsSet2=fieldsSet2+Format.formatAsHeader("New fields: ",4);
				String tempResult="";
				for(LookupField field: this.getmFields() ){
					if(!sameFields.contains(field)){
						tempResult=tempResult+Format.formatAsListItem(field.printLookupField())+"\n";
					}
				}
				tempResult=Format.formatAsList(tempResult);
				fieldsSet2=fieldsSet2+tempResult;
				}
			
			
			
			result=result+Format.formatAsDiv(fieldsSet1+fieldsSet2, divId);
			
			
		}
		if(selectValues.getmMetaFields()!=null&&!mMetaFields.equals(selectValues.getmMetaFields())){
			result=result+"Different meta fields in "+this.getName();
		}
		else{
			//result=selectValues.getName()+" steps are identical.";
		}
		// TODO Auto-generated method stub
		return result;
	}
}

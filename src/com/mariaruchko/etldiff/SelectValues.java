package com.mariaruchko.etldiff;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class SelectValues extends Step {
	List<LookupField> mFields;
	List<LookupField> mMetaFields;

	public SelectValues(Element element){
		Elements fieldElements = element.select("field");
		if (fieldElements != null) {
			for (Element fieldElement : fieldElements) {
				if (mFields == null){
					this.mFields = new ArrayList<LookupField>();
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
					this.mMetaFields = new ArrayList<LookupField>();
				}
				mMetaFields.add(metaField);
			}
		}
	}

	public String getProperties() {
		String result = null;
		
		StringBuilder sbFields = null;
		if (mFields != null) {
			for(LookupField field : this.mFields){
				if (sbFields == null) {
					sbFields = new StringBuilder();
				}
				sbFields.append(field.getLookupField());
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
				sbMetaFields.append(metaField.getLookupField());
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
}

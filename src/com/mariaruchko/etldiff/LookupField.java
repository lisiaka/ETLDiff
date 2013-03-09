package com.mariaruchko.etldiff;

import org.jsoup.nodes.Element;

public class LookupField {
		String name;
		String rename;

		public LookupField(Element valueFromXML) {
			if(valueFromXML.getElementsByTag("name").first()!=null){
			name=valueFromXML.getElementsByTag("name").first().text();
			}
			if(valueFromXML.getElementsByTag("rename").first()!=null){
				rename=valueFromXML.getElementsByTag("rename").first().text();
			}
		}

		public String getLookupField(){
			return "name: "+name+(rename!=null?"; rename: "+rename:"");
		}
	}


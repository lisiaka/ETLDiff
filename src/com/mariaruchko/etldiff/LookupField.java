package com.mariaruchko.etldiff;

import org.jsoup.nodes.Element;

public class LookupField {


		String name;
		String rename;

		public LookupField(Element valueFromXML) {

			name=valueFromXML.getElementsByTag("name").first().text();
			if(valueFromXML.getElementsByTag("rename").first()!=null){
				rename=valueFromXML.getElementsByTag("rename").first().text();
			}

			// TODO Auto-generated constructor stub
		}

		public String getLookupField(){
			return "name: "+name+"; rename: "+rename;
		}
	}


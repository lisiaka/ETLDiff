package com.mariaruchko.etldiff;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;




public class ScriptValueMod extends Step{
	List<JavaScript> javaScripts;
	List<LookupField> fields;

class JavaScript{
	String name;
	String script;
	
	public JavaScript(Element fieldFromXML){
		name=fieldFromXML.getElementsByTag("jsScript_name").first().text();
		if (fieldFromXML.getElementsByTag("jsScript_script")!=null){
			script=fieldFromXML.getElementsByTag("jsScript_script").first().text();
		}
		else
		{
			script="";
		}
	}
	
	public String getJavaScript(){
		return "name: "+name+"; script: "+script;
	}
}

public ScriptValueMod(Element stepFromXML) {
	Elements jscriptsFromXML=stepFromXML.getElementsByTag("jsScript");
	if (javaScripts==null){
		javaScripts=new ArrayList<JavaScript>();
	}
	for(Element jscriptFromXML: jscriptsFromXML){
		JavaScript javaScript= new JavaScript(jscriptFromXML);
		javaScripts.add(javaScript);

	};
	
	Elements fieldsFromXML=stepFromXML.getElementsByTag("field");
	if (fields==null){
		fields=new ArrayList<LookupField>();
	}
	for(Element fieldFromXML: fieldsFromXML){
		LookupField field= new LookupField(fieldFromXML);
		fields.add(field);

	};
	// TODO Auto-generated constructor stub
}

public String getProperties() {
	String printJavaScripts="";
	for(JavaScript javaScript:this.javaScripts){
		printJavaScripts+=javaScript.getJavaScript();
	}
	String printFields="";
	for(LookupField field:this.fields){
		printFields+=field.getLookupField();
	}
	return this.getName()+": "+this.getType()+"; Java Scripts: "+printJavaScripts+"; Fields: "+printFields;
 
}

}

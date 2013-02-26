package com.mariaruchko.etldiff;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;




public class ScriptValueMod extends Step{
	List<JavaScript> javaScripts;

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
	// TODO Auto-generated constructor stub
}

public String getProperties() {
	String javaScripts="";
	for(JavaScript javaScript:this.javaScripts){
		javaScripts+=javaScript.getJavaScript();
	}
	return this.getName()+": "+this.getType()+"; Java Scripts: "+javaScripts;
 
}

}

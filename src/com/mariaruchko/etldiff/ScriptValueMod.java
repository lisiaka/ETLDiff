package com.mariaruchko.etldiff;

import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;




public class ScriptValueMod extends Step{
	Set<JavaScript> javaScripts;
	Set<LookupField> fields;

class JavaScript{
	public String getName() {
		return name;
	}

	public String getScript() {
		return script;
	}

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
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((script == null) ? 0 : script.hashCode());
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

        JavaScript mJavaScript=(JavaScript)obj;
        return 
        
                 (name == mJavaScript.getName() 
                     || (name != null && name.equals(mJavaScript.getName())))
                && (script == mJavaScript.getScript()
                     || (script != null && script.equals(mJavaScript.getScript())));
                     
    }
	
	public String printJavaScript(){
		return "name: "+name+"; script: "+script;
	}
}

public ScriptValueMod(Element stepFromXML) {
	Elements jscriptsFromXML=stepFromXML.getElementsByTag("jsScript");
	if (javaScripts==null){
		javaScripts=new HashSet<JavaScript>();
	}
	for(Element jscriptFromXML: jscriptsFromXML){
		JavaScript javaScript= new JavaScript(jscriptFromXML);
		javaScripts.add(javaScript);

	};
	
	Elements fieldsFromXML=stepFromXML.getElementsByTag("field");
	if (fields==null){
		fields=new HashSet<LookupField>();
	}
	for(Element fieldFromXML: fieldsFromXML){
		LookupField field= new LookupField(fieldFromXML);
		fields.add(field);

	};
	// TODO Auto-generated constructor stub
}

@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((javaScripts == null) ? 0 : javaScripts.hashCode());
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

    ScriptValueMod mScriptValueMod=(ScriptValueMod)obj;
    return 
    
             (javaScripts == mScriptValueMod.getJavaScripts() 
                 || (javaScripts != null && javaScripts.equals(mScriptValueMod.getJavaScripts())))
            && (fields == mScriptValueMod.getFields()
                 || (fields != null && fields.equals(mScriptValueMod.getFields())));
                 
}

public Set<JavaScript> getJavaScripts() {
	return javaScripts;
}

public Set<LookupField> getFields() {
	return fields;
}

public String printProperties() {
	String printJavaScripts="";
	for(JavaScript javaScript:this.javaScripts){
		printJavaScripts+=javaScript.printJavaScript();
	}
	String printFields="";
	for(LookupField field:this.fields){
		printFields+=field.printLookupField();
	}
	return this.getName()+": "+this.getType()+"; Java Scripts: "+printJavaScripts+"; Fields: "+printFields;
 
}

}

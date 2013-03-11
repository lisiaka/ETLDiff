package com.mariaruchko.etldiff;

import org.jsoup.nodes.Element;


public class LookupField {
	
	public String getName() {
		return name;
	}

	public String getRename() {
		return rename;
	}

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

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((rename == null) ? 0 : rename.hashCode());
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

        LookupField mLookupField=(LookupField)obj;
        return 
        
                 (name == mLookupField.getName() 
                     || (name != null && name.equals(mLookupField.getName())))
                && (rename == mLookupField.getRename()
                     || (rename != null && rename.equals(mLookupField.getRename())))
               
                     ;
                     
    }
	
	
	public String printLookupField(){
		return "name: "+name+(rename!=null?"; rename: "+rename:"");
	}
}


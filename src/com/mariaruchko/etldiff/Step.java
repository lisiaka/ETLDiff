package com.mariaruchko.etldiff;

import org.jsoup.nodes.Element;



public class Step {
	private String name;
	private String type;
	private String transformationName;
	private Element stepElement;
	
	public Element getStepElement() {
		return stepElement;
	}
	public void setStepElement(Element stepElement) {
		this.stepElement = stepElement;
	}
	public Step() {
		
		
		// TODO Auto-generated constructor stub
	}
	
	public String getTransformationName() {
		return transformationName;
	}
	public void setTransformationName(String transformationName) {
		this.transformationName = transformationName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String printProperties() {
		return name + ": "+ type;
	}
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        
        result = prime * result + ((stepElement.ownText() == null) ? 0 : stepElement.ownText().hashCode());
        
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

       Step step=(Step)obj;
        return 
        
               (
                    stepElement.ownText().equals(step.getStepElement().ownText())
                    )
                    
                    ;
                     
    }
	
	public String compare(Step step) {
		String result="";
		
		if(type.equals(step.getType())){
			String stepsNames="";
			if(step.getName().equals(this.getName())){
				stepsNames=step.getName();
			}
			else
			{
				stepsNames=step.getName()+" and "+this.getName();
			}
			if(!type.equals("Dummy")&&!stepElement.ownText().equals(step.getStepElement().ownText())){
			result=Format.formatAsParagraph("Steps "+stepsNames+" are of the same type "+type+". Comparison for these types is not implemented",Format.getParagraphNotImplemented());
			}
		}else{
			result=Format.formatAsParagraph("Incompatible types "+type+" and "+step.getType(),Format.getParagraphDetails());
		}
		// TODO Auto-generated method stub
		return result;
	}

}

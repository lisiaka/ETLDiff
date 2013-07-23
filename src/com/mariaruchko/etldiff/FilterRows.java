package com.mariaruchko.etldiff;

import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;







public class FilterRows extends Step {

	private String sendTrueTo;
	private String sendFalseTo;
	private Set<Condition> conditions;
	
	class Condition{
		private String leftValue="";
		private String rightValue="";
		private String function="";
		private String value_name="";
		private String value_text="";
		
		public String getLeftValue() {
			return leftValue;
		}
		public void setLeftValue(String leftValue) {
			this.leftValue = leftValue;
		}
		public String getRightValue() {
			return rightValue;
		}
		public void setRightValue(String rightValue) {
			this.rightValue = rightValue;
		}
		public String getFunction() {
			return function;
		}
		public void setFunction(String function) {
			this.function = function;
		}

		public String getValue_name() {
			return value_name;
		}
		public void setValue_name(String value_name) {
			this.value_name = value_name;
		}
		public String getValue_text() {
			return value_text;
		}
		public void setValue_text(String value_text) {
			this.value_text = value_text;
		}
		
		
 		public Condition(Element conditionFromXML){
			leftValue=conditionFromXML.getElementsByTag("leftvalue").first().text();
			rightValue=conditionFromXML.getElementsByTag("rightvalue").first().text();
			function=conditionFromXML.getElementsByTag("function").first().text();
			value_name=conditionFromXML.getElementsByTag("value").first()!=null?
					conditionFromXML.getElementsByTag("value").first().getElementsByTag("name").first().text():"";
			value_text=conditionFromXML.getElementsByTag("value").first()!=null?
					conditionFromXML.getElementsByTag("value").first().getElementsByTag("text").first().text():"";
		}
		
		@Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((leftValue == null) ? 0 : leftValue.hashCode());
	        result = prime * result + ((rightValue == null) ? 0 : rightValue.hashCode());
	        result = prime * result + ((function == null) ? 0 : function.hashCode());
	        result = prime * result + ((value_name == null) ? 0 : value_name.hashCode());	
	        result = prime * result + ((value_text == null) ? 0 : value_text.hashCode());	
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

	        Condition mCondition=(Condition)obj;
	        return 
	        
	                 (leftValue == mCondition.getLeftValue() 
	                     || (leftValue != null && leftValue.equals(mCondition.getLeftValue())))
	                && (rightValue == mCondition.getRightValue()
	                     || (rightValue != null && rightValue.equals(mCondition.getRightValue())))
	                && (function == mCondition.getFunction()
	                     || (function != null && function.equals(mCondition.getFunction())))
	                && (value_name == mCondition.getValue_name()
	                     || (value_name != null && value_name.equals(mCondition.getValue_name())))
	               && (value_text == mCondition.getValue_text()
	                     || (value_text != null && value_text.equals(mCondition.getValue_text())))
	                     ;
	                     
	    }

		public String printCondition(){
			return Format.formatWordInsideParagraph(leftValue+" "+function+" "
					+(rightValue==null?value_name+"("+value_text+")":rightValue),Format.getIdCodeInText());
		}
	}
	
	
	
	public FilterRows(Element stepFromXML) {
		sendTrueTo=stepFromXML.getElementsByTag("send_true_to").first().text();
		sendFalseTo=stepFromXML.getElementsByTag("send_false_to").first().text();
		Elements conditionsFromXML=stepFromXML.getElementsByTag("compare");
		if (conditions==null){
			conditions=new HashSet<Condition>();
		}
		for(Element conditionFromXML: conditionsFromXML){
			Condition condition= new Condition(conditionFromXML);
			conditions.add(condition);

		};
		// TODO Auto-generated constructor stub
	}
	
	public String getSendTrueTo() {
		return sendTrueTo;
	}

	public void setSendTrueTo(String sendTrueTo) {
		this.sendTrueTo = sendTrueTo;
	}

	public String getSendFalseTo() {
		return sendFalseTo;
	}

	public void setSendFalseTo(String sendFalseTo) {
		this.sendFalseTo = sendFalseTo;
	}

	public Set<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(Set<Condition> conditions) {
		this.conditions = conditions;
	}

	public String printProperties() {
		String conditionsPrintout="";
		for(Condition condition:this.conditions){
			conditionsPrintout+=Format.formatAsListItem(condition.printCondition());
		}
		conditionsPrintout=Format.formatAsList(conditionsPrintout);
		String formatWord=Format.getIdNameInText();
		
		return this.getName()+": "+this.getType()+"; if true: "+Format.formatWordInsideParagraph(sendTrueTo,formatWord)
		+"; if false: "+Format.formatWordInsideParagraph(sendFalseTo,formatWord)
		+Format.formatAsHeader("fields: ",4)+conditionsPrintout;
	}

	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sendFalseTo == null) ? 0 : sendFalseTo.hashCode());
        result = prime * result + ((sendTrueTo == null) ? 0 : sendTrueTo.hashCode());
        result = prime * result + ((conditions == null) ? 0 : conditions.hashCode());
      
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

        FilterRows mFilterRows=(FilterRows)obj;
        return 
        
                 (sendTrueTo == mFilterRows.getSendTrueTo() 
                     || (sendTrueTo != null && sendTrueTo.equals(mFilterRows.getSendTrueTo())))
                && (sendFalseTo == mFilterRows.getSendTrueTo()
                     || (sendFalseTo != null && sendFalseTo.equals(mFilterRows.getSendTrueTo())))
             
                && (conditions == mFilterRows.getConditions()
                     || (conditions != null && conditions.equals(mFilterRows.getConditions())))
                     ;
                     
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

        FilterRows mFilterRows=(FilterRows)step;
       
		if(!sendTrueTo.equals(mFilterRows.getSendTrueTo())){
			result=result+"Different steps \"send true to\" "+Format.formatWordInsideParagraph(sendTrueTo,Format.getIdNameInText())
			+ " vs "+Format.formatWordInsideParagraph(mFilterRows.getSendTrueTo(),Format.getIdNameInText())+" in "+mFilterRows.getName();
		}
		if(!sendFalseTo.equals(mFilterRows.getSendFalseTo())){
			result=result+"Different steps \"send false to\" "+Format.formatWordInsideParagraph(sendFalseTo,Format.getIdNameInText())
			+ " vs "+Format.formatWordInsideParagraph(mFilterRows.getSendFalseTo(),Format.getIdNameInText())+" in "+mFilterRows.getName();
		}
		
		if(!conditions.equals(mFilterRows.getConditions())){
			String divId=this.getTransformationName()+"_"+this.getName()+"_conditions";
			result=result+Format.formatAsLinkToUnfold("Different conditions in "+mFilterRows.getName(),divId);
			Set<Condition> sameConditions = new HashSet<Condition>();
			for(Condition condition: mFilterRows.getConditions()){				
				if(this.getConditions().contains(condition)){					
					sameConditions.add(condition);
				}
				
			}
			String conditionsSet1="";
			String conditionsSet2="";
			if(sameConditions.size()!=mFilterRows.getConditions().size()){
				conditionsSet1=conditionsSet1+Format.formatAsHeader("Missing conditions: ",4);
				String tempResult="";
			for(Condition condition: mFilterRows.getConditions() ){
				if(!sameConditions.contains(condition)){
					tempResult=tempResult+Format.formatAsListItem(condition.printCondition())+"\n";
				}
			}
			tempResult=Format.formatAsList(tempResult);
			conditionsSet1=conditionsSet1+tempResult;
			}
			
			if(sameConditions.size()!=this.getConditions().size()){
				conditionsSet2=conditionsSet2+Format.formatAsHeader("New fields: ",4);
				String tempResult="";
				for(Condition condition: this.getConditions() ){
					if(!sameConditions.contains(condition)){
						tempResult=tempResult+Format.formatAsListItem(condition.printCondition())+"\n";
					}
				}
				tempResult=Format.formatAsList(tempResult);
				conditionsSet2=conditionsSet2+tempResult;
				}
			
			
			
			result=result+Format.formatAsDiv(conditionsSet1+conditionsSet2, divId);
			
		}
		
		else{
			//result=mDimensionLookup.getName()+" steps are identical.";
		}
		// TODO Auto-generated method stub
		return result;
	}

}

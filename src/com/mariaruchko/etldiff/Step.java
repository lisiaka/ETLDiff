package com.mariaruchko.etldiff;



public class Step {
	private String name;
	private String type;
	
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
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        
                 (name == step.getName() 
                     || (name != null && name.equals(step.getName())))
                && (type == step.getType()
                     || (type != null && type.equals(step.getType())));
                     
    }

}

package com.mariaruchko.etldiff;

import org.jsoup.nodes.Element;


public class TableOutput extends Step {
	private String connection;
	private String table;
	private String truncate;
	private Integer commit;

	
public String getConnection() {
		return connection;
	}

	public String getTable() {
		return table;
	}

	public String getTruncate() {
		return truncate;
	}

	public Integer getCommit() {
		return commit;
	}


public TableOutput(Element stepFromXML){
	connection=stepFromXML.getElementsByTag("connection").first().text();
	table=stepFromXML.getElementsByTag("table").first().text();
	truncate=stepFromXML.getElementsByTag("truncate").first().text();
	commit=Integer.valueOf(stepFromXML.getElementsByTag("commit").first().text());
	
}

@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((connection == null) ? 0 : connection.hashCode());
    result = prime * result + ((table == null) ? 0 : table.hashCode());
    result = prime * result + ((truncate == null) ? 0 : truncate.hashCode());
    result = prime * result + ((commit == null) ? 0 : commit.hashCode());
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

    TableOutput mTableOutput=(TableOutput)obj;
    return 
    
             (connection == mTableOutput.getConnection() 
                 || (connection != null && connection.equals(mTableOutput.getConnection())))
            && (table == mTableOutput.getTable()
                 || (table != null && table.equals(mTableOutput.getTable())))
            && (truncate == mTableOutput.getTruncate()
                 || (truncate != null && truncate.equals(mTableOutput.getTruncate())))
            && (commit == mTableOutput.getCommit()
                 || (commit != null && commit.equals(mTableOutput.getCommit())))
           
                 ;
                 
}


public String printProperties() {
	return this.getName()+": "+this.getType()+"; connection: "+connection+"; table: "+table+(truncate.equals("Y")?"; truncate":"")+"; commit: "+commit;
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

    TableOutput mTableOutput=(TableOutput)step;
   
	if(!connection.equals(mTableOutput.getConnection())){
		result=result+"Different connections "+connection+ " vs "+mTableOutput.getConnection()+" in "+mTableOutput.getName();
	}
	if(!table.equals(mTableOutput.getTable())){
		result=result+"Different tables "+table+ " vs "+mTableOutput.getTable()+" in "+mTableOutput.getName();
	}
	if(!truncate.equals(mTableOutput.getTruncate())){
		result=result+"Different truncate "+truncate+" vs "+mTableOutput.getTruncate()+" in "+mTableOutput.getName();
	}
	if(!commit.equals(mTableOutput.getCommit())){
		result=result+"Different commit "+commit+" vs "+mTableOutput.getCommit()+" in "+mTableOutput.getName();
	}
	else{
		//result=mTableOutput.getName()+" steps are identical.";
	}
	
	// TODO Auto-generated method stub
	return result;
}
}

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
}

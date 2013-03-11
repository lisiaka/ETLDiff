package com.mariaruchko.etldiff;
import org.jsoup.nodes.Element;




public class TableInputStep extends Step {
	public String getConnection() {
		return connection;
	}

	public String getSql() {
		return sql;
	}

	String connection;
	String sql;

	public TableInputStep(Element stepFromXML) {
		connection=stepFromXML.getElementsByTag("connection").first().text();
		sql=stepFromXML.getElementsByTag("sql").first().text();	
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((connection == null) ? 0 : connection.hashCode());
	    result = prime * result + ((sql == null) ? 0 : sql.hashCode());
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

	    TableInputStep mTableInputStep=(TableInputStep)obj;
	    return 
	    
	             (connection== mTableInputStep.getName()
	                 || (connection != null && connection.equals(mTableInputStep.getConnection())))
	            && (sql == mTableInputStep.getType()
	                 || (sql != null && sql.equals(mTableInputStep.getSql())));
	                 
	}
	
	public String printProperties() {
		return this.getName()+": "+this.getType()+"; connection: "+connection+"; sql: "+sql;
	}

}

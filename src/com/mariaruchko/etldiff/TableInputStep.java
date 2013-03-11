package com.mariaruchko.etldiff;
import org.jsoup.nodes.Element;


public class TableInputStep extends Step {
	String connection;
	String sql;

	public TableInputStep(Element stepFromXML) {
		connection=stepFromXML.getElementsByTag("connection").first().text();
		sql=stepFromXML.getElementsByTag("sql").first().text();	
	}

	public String printProperties() {
		return this.getName()+": "+this.getType()+"; connection: "+connection+"; sql: "+sql;
	}

}

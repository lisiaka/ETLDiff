package com.mariaruchko.etldiff;

import org.jsoup.nodes.Element;

public class TableOutput extends Step {
private String connection;
private String table;
private String truncate;
private Integer commit;

public TableOutput(Element stepFromXML){
	connection=stepFromXML.getElementsByTag("connection").first().text();
	table=stepFromXML.getElementsByTag("table").first().text();
	truncate=stepFromXML.getElementsByTag("truncate").first().text();
	commit=Integer.valueOf(stepFromXML.getElementsByTag("commit").first().text());
	
}

public String printProperties() {
	return this.getName()+": "+this.getType()+"; connection: "+connection+"; table: "+table+(truncate.equals("Y")?"; truncate":"")+"; commit: "+commit;
}
}

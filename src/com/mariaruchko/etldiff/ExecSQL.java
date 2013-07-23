package com.mariaruchko.etldiff;

import java.util.HashSet;
import java.util.Set;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class ExecSQL extends Step {
	private String connection;
	private String sql;
	private Set<String> arguments;
	
	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Set<String> getArguments() {
		return arguments;
	}

	public void setArguments(Set<String> arguments) {
		this.arguments = arguments;
	}

	
	
	
	public ExecSQL(Element stepFromXML) {
		connection=stepFromXML.getElementsByTag("connection").first().text();
		sql=stepFromXML.getElementsByTag("sql").first().text();	
		Elements argumentsFromXML=stepFromXML.getElementsByTag("arguments");
		if (arguments==null){
			arguments=new HashSet<String>();
		}
		for(Element argumentFromXML: argumentsFromXML){
			String argument= argumentFromXML.getElementsByTag("name").first()!=null?argumentFromXML.getElementsByTag("name").first().text():"";
			arguments.add(argument);

		};
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((connection == null) ? 0 : connection.hashCode());
	    result = prime * result + ((sql == null) ? 0 : sql.hashCode());
	    result = prime * result + ((arguments == null) ? 0 : arguments.hashCode());
	    return result;
	}

	public String printProperties() {
		String argumentsPrintout="";
		for(String argument:this.arguments){
			argumentsPrintout+=Format.formatAsListItem(argument);
		}
		argumentsPrintout=Format.formatAsList(argumentsPrintout);
		
		
		return this.getName()+": "+this.getType()+"; connection: "+connection+Format.formatAsHeader("sql: ",5)+Format.formatAsParagraph(sql, "sql")
		+Format.formatAsHeader("arguments: ",4)+argumentsPrintout;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == this) {
	        return true;
	    }
	    if (obj == null || obj.getClass() != this.getClass()) {
	        return false;
	    }

	    ExecSQL mExecSQL=(ExecSQL)obj;
	    return 
	    
	             (connection== mExecSQL.getName()
	                 || (connection != null && connection.equals(mExecSQL.getConnection())))
	            && (sql == mExecSQL.getType()
	                 || (sql != null && sql.equals(mExecSQL.getSql())))
	                 && (arguments == mExecSQL.getArguments()
	                 || (arguments != null && arguments.equals(mExecSQL.getArguments())));
	                 
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

	    ExecSQL mExecSQL=(ExecSQL)step;
	   
		if(!connection.equals(mExecSQL.getConnection())){
			result=result+"Different connections "+connection+ " vs "+mExecSQL.getConnection()+" in "+mExecSQL.getName();
		}
		
		
		if(!sql.equals(mExecSQL.getSql())){
			String divId=this.getTransformationName()+"_"+this.getName()+"_sql";
			String sqlFormat=Format.getParagraphSql();
			result=result+Format.formatAsLinkToUnfold("Different sql in "+this.getName(),divId);
			result=result+Format.formatAsDiv(Format.formatAsParagraph(sql,sqlFormat)+"vs"+Format.formatAsParagraph(mExecSQL.getSql(),sqlFormat), divId);
			
			
		}
		
		if(!arguments.equals(mExecSQL.getArguments())){
			result=result+"Different arguments "+arguments.toArray().toString()+ " vs "+mExecSQL.getArguments().toArray().toString()+" in "+mExecSQL.getName();
		}
		
		else{
			//result=tableInputStep.getName()+" steps are identical.";
		}
		
		// TODO Auto-generated method stub
		return result;
	}
}

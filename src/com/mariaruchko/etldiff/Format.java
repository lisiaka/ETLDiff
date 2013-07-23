package com.mariaruchko.etldiff;

public class Format {
	
	private final static String ID_CHANGED = "changed";
	private final static String ID_NOT_FOUND = "notfound";
	private final static String ID_CUSTOMIZED = "customized";
	private final static String PARAGRAPH_DETAILS = "details";
	private final static String PARAGRAPH_NOT_IMPLEMENTED = "notimplemented";
	private final static String PARAGRAPH_SQL = "sql";
	private final static String ID_CODE_IN_TEXT = "codeInText";
	private final static String ID_NAME_IN_TEXT = "nameInText";
	
public static String getIdNameInText() {
		return ID_NAME_IN_TEXT;
	}

public static String getIdCodeInText() {
		return ID_CODE_IN_TEXT;
	}

public static String getIdChanged() {
		return ID_CHANGED;
	}

	public static String getIdNotFound() {
		return ID_NOT_FOUND;
	}

	public static String getIdCustomized() {
		return ID_CUSTOMIZED;
	}

	public static String getParagraphDetails() {
		return PARAGRAPH_DETAILS;
	}

	public static String getParagraphNotImplemented() {
		return PARAGRAPH_NOT_IMPLEMENTED;
	}

	public static String getParagraphSql() {
		return PARAGRAPH_SQL;
	}

public static String formatAsParagraph(String stringToFormat, String style){	
	String mClass="";
	if (style!=""){
		mClass="class='"+style+"'";
	}
	
	return "<p "+mClass+">"+stringToFormat+"</p>";
	
}

public static String formatWordInsideParagraph(String stringToFormat, String style){	
	
	return "<span id='"+style+"'>"+stringToFormat+"</span>";
	
}
public static String formatAsHeader(String stringToFormat, Integer headerLevel){	
	
	return "<h"+headerLevel+">"+stringToFormat+"</h"+headerLevel+">";
	
}

public static String formatAsDiv(String stringToFormat, String id){	
	
	return "<div id='" +id+
			"'>"+stringToFormat+"</div>";
	
}

public static String formatAsLinkToUnfold(String stringToFormat, String id){	
	
	return "<a href=\"#\" onclick=\"toggle_visibility('" +id+"');\">"+stringToFormat+"</a>";
	
}

public static String formatAsListItem(String stringToFormat){	
	
	return "<li>"+stringToFormat+"</li>";
	
}
public static String formatAsList(String stringToFormat){	
	
	return "<ul>"+stringToFormat+"</ul>";
	
}
}

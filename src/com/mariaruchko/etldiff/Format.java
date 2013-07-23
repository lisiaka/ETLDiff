package com.mariaruchko.etldiff;

public class Format {
	
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

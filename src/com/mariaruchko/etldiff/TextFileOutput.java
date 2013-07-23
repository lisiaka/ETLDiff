package com.mariaruchko.etldiff;

import org.jsoup.nodes.Element;

public class TextFileOutput extends Step {
	
private String fileName;
private String extention;
private String fileFormat;
private String compression;

	public String getFileName() {
	return fileName;
}

public void setFileName(String fileName) {
	this.fileName = fileName;
}

public String getExtention() {
	return extention;
}

public void setExtention(String extention) {
	this.extention = extention;
}

public String getFileFormat() {
	return fileFormat;
}

public void setFileFormat(String fileFormat) {
	this.fileFormat = fileFormat;
}

public String getCompression() {
	return compression;
}

public void setCompression(String compression) {
	this.compression = compression;
}

	public TextFileOutput(Element stepFromXML) {
		// TODO Auto-generated constructor stub
		Element file=stepFromXML.getElementsByTag("file").first();
		fileName=file.getElementsByTag("name").first().text();
		extention=file.getElementsByTag("extention").first().text();
		fileFormat=stepFromXML.getElementsByTag("format").first().text();
		compression=stepFromXML.getElementsByTag("compression").first().text();
	}
	
	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
	    result = prime * result + ((extention == null) ? 0 : extention.hashCode());
	    result = prime * result + ((fileFormat == null) ? 0 : fileFormat.hashCode());
	    result = prime * result + ((compression == null) ? 0 : compression.hashCode());
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

	    TextFileOutput mTextFileOutput=(TextFileOutput)obj;
	    return 
	    
	             (fileName == mTextFileOutput.getFileName() 
	                 || (fileName != null && fileName.equals(mTextFileOutput.getFileName())))
	            && (extention == mTextFileOutput.getExtention()
	                 || (extention != null && extention.equals(mTextFileOutput.getExtention())))
	            && (fileFormat == mTextFileOutput.getFileFormat()
	                 || (fileFormat != null && fileFormat.equals(mTextFileOutput.getFileFormat())))
	            && (compression == mTextFileOutput.getCompression()
	                 || (compression != null && compression.equals(mTextFileOutput.getCompression())))
	           
	                 ;
	                 
	}
	
	public String printProperties() {
		String formatForWord=Format.getIdNameInText();
		return this.getName()+": "+this.getType()+"; file name: "+Format.formatWordInsideParagraph(fileName+"."+extention,formatForWord)
		+"; format: "+Format.formatWordInsideParagraph(fileFormat,formatForWord)+"; compression: "+Format.formatWordInsideParagraph(compression,formatForWord);
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

	    TextFileOutput mTextFileOutput=(TextFileOutput)step;
	   
		if(!fileName.equals(mTextFileOutput.getFileName())){
			result=result+"Different file names "+fileName+ " vs "+mTextFileOutput.getFileName()+" in "+mTextFileOutput.getName();
		}
		if(!extention.equals(mTextFileOutput.getExtention())){
			result=result+"Different extention "+extention+ " vs "+mTextFileOutput.getExtention()+" in "+mTextFileOutput.getName();
		}
		if(!fileFormat.equals(mTextFileOutput.getFileFormat())){
			result=result+"Different truncate "+fileFormat+" vs "+mTextFileOutput.getFileFormat()+" in "+mTextFileOutput.getName();
		}
		if(!compression.equals(mTextFileOutput.getCompression())){
			result=result+"Different commit "+compression+" vs "+mTextFileOutput.getCompression()+" in "+mTextFileOutput.getName();
		}
		else{
			//result=mTableOutput.getName()+" steps are identical.";
		}
		
		// TODO Auto-generated method stub
		return result;
	}
}

package com.mariaruchko.etldiff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class Hello {
	public static void main(String args[]) {
		InputStream inputStream1 = null;
		Document document1 = null;
		InputStream inputStream2 = null;
		Document document2 = null;
		PrintWriter outputStream = null; 
		BufferedReader inputStream = null;
		String repository1=null;
		String repository2=null;
		
		try{
			inputStream =
				new BufferedReader(new FileReader("/Users/maria/Documents/workspace/etl_compare/etl_compare.properties"));
			

			Parameters.putParameters(inputStream);
			
			try{

				repository1=Parameters.get("repository1").trim();
				repository2=Parameters.get("repository2").trim();
			}catch(Exception Ex){
				//System.out.print("!!!"+Ex.getMessage());
			}
		}catch(Exception Ex1){
			
		}

		try {
			inputStream1 = new FileInputStream(new File(repository1));

			document1 = Jsoup.parse(inputStream1, "UTF-8", "", Parser.xmlParser());
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	

		
		Set<String> transformationsFromDoc1Keys = null;
		Map<String, Transformation> transformationsFromDoc11 = null;
		Map<String,Transformation> transformationsFromDoc2 = null;
		Elements elementsFromDoc1 = document1.select("transformation");
		document1=null;

		try {
			inputStream2 =	new FileInputStream(new File(repository2));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {		

			document2 = Jsoup.parse(inputStream2, "UTF-8", "", Parser.xmlParser());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		Elements elementsFromDoc2 = document2.select("transformation");

		for (Element element : elementsFromDoc1) {
			if (transformationsFromDoc11 == null) {
				transformationsFromDoc11 = new HashMap<String,Transformation>();
			}

			Transformation transformation = new Transformation(element);
			transformationsFromDoc11.put(transformation.getName(), transformation);
		}
		transformationsFromDoc1Keys=transformationsFromDoc11.keySet();
		
		for (Element element : elementsFromDoc2) {
			if (transformationsFromDoc2 == null) {
				transformationsFromDoc2 = new HashMap<String,Transformation>();
			}

			Transformation transformation = new Transformation(element);
			transformationsFromDoc2.put(transformation.getName(), transformation);
		}

		try {
			outputStream =
				new PrintWriter(new FileWriter("/Users/maria/Documents/workspace/etl_compare/compare_result.html"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		outputStream.println("<head> <link rel='stylesheet' type='text/css' href='format.css'>");
		outputStream.println("<script type=\"text/javascript\">");
				
		outputStream.println("<!--");
		outputStream.println("   function toggle_visibility(id) {");
		outputStream.println("      var e = document.getElementById(id);");
		outputStream.println("       if(e.style.display == 'block')");
		outputStream.println("          e.style.display = 'none';");
		outputStream.println("       else");
		outputStream.println("          e.style.display = 'block';");
		outputStream.println("   }");
		outputStream.println("//-->");
		outputStream.println("</script>");
				
		outputStream.println(		"</head>");
		
		for (String transformationName : transformationsFromDoc1Keys) {
			Transformation transformation=transformationsFromDoc11.get(transformationName);
			String transFromDoc1Name=transformation.getName();

			if(transformation.equals(transformationsFromDoc2.get(transFromDoc1Name))){
				if(transformationsFromDoc2.get("c_"+transFromDoc1Name)!=null&&!transformationsFromDoc11.containsKey("c_"+transFromDoc1Name)){
					outputStream.println(Format.formatAsHeader(Format.formatAsLinkToUnfold(transformation.getDirectory() + "/" + transformation.getName()+Format.formatWordInsideParagraph(" - customized ",Format.getIdCustomized()),transformation.getName()),2));
					outputStream.println(Format.formatAsDiv(transformation.compare(transformationsFromDoc2.get("c_"+transFromDoc1Name)),transformation.getName()));
				}
				//System.out.println(transformation.getDirectory() + "/" + transformation.getName()+" - unchanged");
			}
			else if(transformationsFromDoc2.get(transFromDoc1Name)!=null&&transformation.hasThesameName(transformationsFromDoc2.get(transFromDoc1Name)))
				
			{
				outputStream.println(Format.formatAsHeader(Format.formatAsLinkToUnfold(transformation.getDirectory() + "/" + transformation.getName()+Format.formatWordInsideParagraph(" - changed",Format.getIdChanged()),transformation.getName()),2));
				outputStream.println(Format.formatAsDiv(transformation.compare(transformationsFromDoc2.get(transFromDoc1Name)),transformation.getName()));
			}
			else{
				outputStream.println(Format.formatAsHeader(transformation.getDirectory() + "/" + transformation.getName()+Format.formatWordInsideParagraph(" - not found",Format.getIdNotFound()),2));
			}
		};
		
		outputStream.close();

	}

}

package com.mariaruchko.etldiff;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		try {
			inputStream1 = new FileInputStream(new File("/Users/maria/Documents/workspace/etl_compare/Scandpower_ETL_old.xml"));

			document1 = Jsoup.parse(inputStream1, "UTF-8", "", Parser.xmlParser());
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		long heapSize = Runtime.getRuntime().totalMemory(); 
		long heapMaxSize = Runtime.getRuntime().maxMemory();

		List<Transformation> transformationsFromDoc1 = null;
		Map<String,Transformation> transformationsFromDoc2 = null;
		Elements elementsFromDoc1 = document1.select("transformation");
		document1=null;

		try {
			inputStream2 =	new FileInputStream(new File("/Users/maria/Documents/workspace/etl_compare/Scandpower_ETL.xml"));
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
			if (transformationsFromDoc1 == null) {
				transformationsFromDoc1 = new ArrayList<Transformation>();
			}

			Transformation transformation = new Transformation(element);
			transformationsFromDoc1.add(transformation);
		}

		for (Element element : elementsFromDoc2) {
			if (transformationsFromDoc2 == null) {
				transformationsFromDoc2 = new HashMap<String,Transformation>();
			}

			Transformation transformation = new Transformation(element);
			transformationsFromDoc2.put(transformation.getName(), transformation);
		}



		for (Transformation transformation : transformationsFromDoc1) {

			String transFromDoc1Name=transformation.getName();

			if(transformation.equals(transformationsFromDoc2.get(transFromDoc1Name))){
				if(transformationsFromDoc2.get("c_"+transFromDoc1Name)!=null){
					System.out.println(transformation.getDirectory() + "/" + transformation.getName()+" - customized");
				}
				//System.out.println(transformation.getDirectory() + "/" + transformation.getName()+" - unchanged");
			}
			else if(transformationsFromDoc2.get(transFromDoc1Name)!=null&&transformation.hasThesameName(transformationsFromDoc2.get(transFromDoc1Name)))
				
			{
				System.out.println(transformation.getDirectory() + "/" + transformation.getName()+" - changed");
			}
			else{
				System.out.println(transformation.getDirectory() + "/" + transformation.getName()+" - not found");
			}
		};

	}

}

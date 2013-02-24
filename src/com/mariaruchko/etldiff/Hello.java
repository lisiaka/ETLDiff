package com.mariaruchko.etldiff;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Hello {
	public static void main(String args[]) {
		List<Transformation> transformations;
		
		File input = new File("../etl_compare/Scandpower_ETL.xml");
		Document doc = null;
		try {
			doc = Jsoup.parse(input, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		transformations = new ArrayList<Transformation>();
		Elements transformationsFromXML = doc.getElementsByTag("transformation");
		for (Element transformation : transformationsFromXML) {
			Transformation trans = new Transformation(transformation);
			transformations.add(trans);
		}
		
		for (Transformation trans : transformations) {
			System.out.println(trans.getDirectory() + "/" + trans.getName());
			for(Step step : trans.getSteps()){
				System.out.println(step.getProperties());
			}
		}
		
	}
}

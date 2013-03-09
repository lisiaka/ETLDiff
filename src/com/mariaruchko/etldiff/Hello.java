package com.mariaruchko.etldiff;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class Hello {
	public static void main(String args[]) {
		InputStream inputStream = null;
		Document document = null;
		try {
			inputStream = new FileInputStream(new File("/Users/maria/Documents/workspace/etl_compare/Scandpower_ETL.xml"));
			document = Jsoup.parse(inputStream, "UTF-8", "", Parser.xmlParser());
		} catch (IOException e) {
		}
		
		List<Transformation> transformations = null;
		Elements elements = document.select("transformation");
		for (Element element : elements) {
			if (transformations == null) {
				transformations = new ArrayList<Transformation>();
			}

			Transformation transformation = new Transformation(element);
			transformations.add(transformation);
		}

		for (Transformation transformation : transformations) {
			System.out.println(transformation.getDirectory() + "/" + transformation.getName());
			for(Step step : transformation.getSteps()){
				System.out.println(step.getProperties());
			}
		}
	}
}

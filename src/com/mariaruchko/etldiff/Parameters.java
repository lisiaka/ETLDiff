package com.mariaruchko.etldiff;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Parameters {
	private static Map<String, String> parameterSet = new HashMap<String, String>();


	public static void putParameters(BufferedReader inputStream) {

		String l;

		try{	
			while ((l = inputStream.readLine()) != null) {

				put(l, "REPOSITORY_1", "repository1");		
				put(l, "REPOSITORY_2", "repository2");

			}
		}
		catch(Exception ex1){
			System.out.print(ex1.getMessage());
		}
	}

	private static void put(String inputString, String prompt, String parameterName) {

		if(inputString.startsWith(prompt)){
			try{
				if(!inputString.substring(inputString.indexOf("=")+1).trim().isEmpty()){

					parameterSet.put(parameterName,inputString.substring(inputString.indexOf("=")+1).trim());}
				else
				{
					parameterSet.put(parameterName," ");}
			}
			catch (Exception ex1){
				parameterSet.put(parameterName," ");
				System.out.print(ex1.getMessage());
			}

		}
	}

	public static String get(String parameterName) {
		return parameterSet.get(parameterName);

	}

	public static void printParameters() {
		Set<String> allParameters = parameterSet.keySet();
		System.out.println("Processing with parameters: ");
		for(String parameterName: allParameters){      
			System.out.println(parameterName+" = "+get(parameterName));
		}
	}
}
package test;

import java.io.File;

public class fileSearchCode {
	public static void main(String[] args){
	
	File filePath = new File("C:/Users/jason/Documents/School/University of Calgary/SENG 300 iteration 1/Iteration1/src/test");
	File[] allFiles = filePath.listFiles();
    for (int i = 0; i < allFiles.length; i++) {
	     if (allFiles[i].isFile()) {
	    		 String fileToCheck = allFiles[i].getName();
	    		 String isJava = fileToCheck.substring(fileToCheck.lastIndexOf("."));
	    		 if(isJava.equals(".java")){
	    			 //Could replace the print with code that puts the file into a list
	    			 System.out.println("File " + allFiles[i].getName());
	    		 } else {
	    			 System.out.println("false");
	    		 }
	     }
	    }
	}
}
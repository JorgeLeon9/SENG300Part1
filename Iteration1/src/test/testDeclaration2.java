package test;

import java.io.File;

public class testDeclaration2 {
	public static void main(String[] args){
	
	File filePath = new File("C:/Users/jason/Documents/School/University of Calgary/SENG 300 iteration 1/Iteration1/src/test");
	File[] allFiles = filePath.listFiles();{
    for (int i = 0; i < allFiles.length; i++) {
	     if (allFiles[i].isFile()) {
	    	 //for (int j = 0; i < allFiles.length; j++){
	    		 String fileToCheck = allFiles[i].getName();
	    		 String isJava = fileToCheck.substring(fileToCheck.lastIndexOf("."));
	    		 //to test if it is .java in the end
	    		 System.out.println(isJava);
	    		 //current bug: I print isjava, it is ".java" but the if statement is not true.
	    		 if(isJava == ".java"){
	    			 System.out.println("File " + allFiles[i].getName());
	    		 } else {
	    			 System.out.println("false");
	    		 }
	    	 //}
	     }
	    }
}}
}
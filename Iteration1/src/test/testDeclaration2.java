package test;

import java.io.File;

public class testDeclaration2 {
	public static void main(String[] args){
	
	File filePath = new File("C:/Users/jason/Documents/School/University of Calgary/SENG 300 iteration 1/Iteration1/src/test");
	File[] javaFiles = filePath.listFiles();{
    for (int i = 0; i < javaFiles.length; i++) {
	     if (javaFiles[i].isFile()) {
	    	 System.out.println("File " + javaFiles[i].getName());
	    	 } //dont need the directory part
	    }
}}
}
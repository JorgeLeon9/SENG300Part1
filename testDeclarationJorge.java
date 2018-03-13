package test ;


//package test;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class testDeclarationJorge {

	
	public int count;
	public  static String getFile(String filePath) throws FileNotFoundException, IOException{
		BufferedReader read = new BufferedReader(new FileReader(filePath));
		StringBuilder build = new StringBuilder();
		String line = read.readLine();
		
		while (line != null){
			build.append(line);
			build.append(System.lineSeparator());
			line = read.readLine();
			
		}
		
		return build.toString();
		
	}
	
	public static String typeFinder ( String val,String val2) throws FileNotFoundException, IOException{
		int count1=0;
		String type1=" ";
		
		String filePath = "test.java";
		char [] past=getFile(filePath).toCharArray();
		char []used;
		int count=0;
		for (int i=0;i< past.length;i++) {
			if (past[i]==' '){count++;}
			
		}
		used=new char[past.length-count];
		for (int i=0;i< past.length-count;i++) {
			if (past[i]==' '){continue;}
			used[count1]=past[i];
			
			count1++;
		}
		
		
		
		boolean flag=false;
		boolean localvar=false;
		boolean thecounter=false;
		for(int i=0; i < used.length  - val.length();i++) {
			int valch=0;
			
			if (used[i] == '(' ) {flag=true;}
			else if (used[i]== ')' ) {flag=false;}
			
			for (int j=0; j< val.length(); j++) {
				
				if ((used[i+j]==val.charAt(j) || used[i+j]==val2.charAt(j)) && flag==false) {
					valch++;
				}
			}
			if (valch==val.length() && localvar == false) {
				if (used[i-1] =='n') { type1="boolean"; }
				else if(used[i-1]=='e' && used[i-2]=='t') {type1="byte"; }
				else if (used[i-1]=='r') {type1="char"; }
				else if(used[i-1]=='t' && used[i-2]=='r') {type1="short";}
				else if (used[i-1]=='t'&& used[i-2]=='n') {type1="int"; }
				else if(used[i-1]=='g' && used[i-2]=='n'&& used[i-3]=='o') {type1="long";}
				else if (used[i-1]=='t' && used[i-2]=='a') {type1="float";}
				else if(used[i-1]=='l' && used[i-2]=='e') {type1="double";}
				else if (used[i-1]=='g' && used[i-2]=='n'&& used[i-3]=='i') {type1="String"; }
				else {continue;}
			}
			
		}
		return type1;
	}
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		String filePath = "test.java";
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		
		char[] fileContent = getFile(filePath).toCharArray();
		parser.setSource(fileContent);
		CompilationUnit unit = (CompilationUnit) parser.createAST(null);
		List<String> declarations = new ArrayList();
		unit.accept(new ASTVisitor(){
		
			
			public boolean visit(VariableDeclarationFragment node){
				
				SimpleName  name = node.getName();
				String type1=" ";
				int lineNumber = unit.getLineNumber(name.getStartPosition());
				try {
					
					type1=typeFinder(name.toString()+";",name.toString()+"=");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				declarations.add(name.toString());
				
				
				System.out.println(name+" type: " + type1);
				return false;
			}
		
		});
		Set<String> uniqueSet = new HashSet<String>(declarations);
		for (String type : uniqueSet) {
			System.out.println(type + " Declarations found: " + Collections.frequency(declarations, type));	
	
		}
	}
}

package test;
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
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class testDeclaration {

	
	public static String getFile(String filePath) throws FileNotFoundException, IOException{
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
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String filePath = "test.java";
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		char[] fileContent = getFile(filePath).toCharArray();
		parser.setSource(fileContent);
		CompilationUnit unit = (CompilationUnit) parser.createAST(null);
		List<String> declarations = new ArrayList();
		unit.accept(new ASTVisitor(){
		
			
			public boolean visit(VariableDeclarationFragment node){
				SimpleName name = node.getName();
				int lineNumber = unit.getLineNumber(name.getStartPosition());
				declarations.add(name.toString());
				
				System.out.println(declarations);
				//System.out.println("Line number: " + lineNumber);
				return false;
			}
		
		});
		Set<String> uniqueSet = new HashSet<String>(declarations);
		for (String type : uniqueSet) {
			System.out.println(type + " Declarations found: " + Collections.frequency(declarations, type));	
	
		}
	}
}

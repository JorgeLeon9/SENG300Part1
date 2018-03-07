package test;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
		unit.accept(new ASTVisitor(){
		
		
			public boolean visit(VariableDeclarationFragment node){
				SimpleName name = node.getName();
				int counter = 0;
				System.out.println(name + " Declarations Found:");
				return false;
			}
			
		});
	}

}

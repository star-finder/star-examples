package avl_tree;

import static defs.Defs.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.StateFormatMode;
import jbse.apps.run.RunParameters.StepShowMode;
import jbse.apps.settings.ParseException;
import jbse.apps.settings.SettingsReader;

public class Launcher {
	public static void main(String[] args)	{
		long begin = System.currentTimeMillis();
		
		RunParameters p = new RunParameters();
		fill(p);

		Run r = new Run(p);
		r.run();
		
		long end = System.currentTimeMillis();
		
		System.out.println(end - begin);
	}
	
	private static void fill(RunParameters p) {
		try {
			SettingsReader sr = new SettingsReader(examplesHome + "settings/avl_tree_accurate.jbse");
			sr.fillRunParameters(p);
		} catch (FileNotFoundException e) {
			System.err.println("Error: settings file not found.");
			System.exit(1);
		} catch (ParseException e) {
			System.err.println("Error: settings file syntactically ill-formed.");
			System.exit(2);
		} catch (IOException e) {
			System.err.println("Error while closing settings file.");
			System.exit(2);
		}
		
		p.addClasspath(classPath);
		p.addSourcePath(sourcePath);
		p.setExternalDecisionProcedurePath(z3Path);
		p.setDecisionProcedureType(DecisionProcedureType.Z3);
		p.setDoEqualityAnalysis(true); 
		p.setDoSignAnalysis(true);
//		p.setMethodSignature("avl_tree/AvlTree", "()I", "findMax");
//		p.setMethodSignature("avl_tree/AvlTree", "()I", "findMin");
//		p.setMethodSignature("avl_tree/AvlTree", "(I)I", "find");
//		p.setMethodSignature("avl_tree/AvlTree", "(I)V", "insertElem");
//		p.setMethodSignature("avl_tree/AvlTree", "()Z", "isEmpty");
//		p.setMethodSignature("avl_tree/AvlTree", "()V", "makeEmpty");
//		p.setMethodSignature("avl_tree/AvlTree", "(I)Z", "maxElement");
//		p.setMethodSignature("avl_tree/AvlTree", "(I)Z", "minElement");
		p.setMethodSignature("avl_tree/AvlTree", "()V", "printTree");
		p.setStepShowMode(StepShowMode.LEAVES);
		p.setStateFormatMode(StateFormatMode.JUNIT_TEST);
		p.setShowContradictory(false);
		p.setOutputFileName(examplesHome + "out/avl.txt");
		p.setDepthScope(20);
//		p.setGuided("avl/Main", "()V", "foo");
		//p.setStepShowMode(StepShowMode.ALL);
		//p.setStateFormatMode(StateFormatMode.TRACE);
	}
}

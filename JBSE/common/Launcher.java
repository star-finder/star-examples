package common;

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
			SettingsReader sr = new SettingsReader(examplesHome + "settings/linked_list.jbse");
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
//		p.setMethodSignature("common/LinkedList", "(Ljava/lang/Object;)V", "addFirst");
//		p.setMethodSignature("common/LinkedList", "(ILjava/lang/Object;)V", "add");
//		p.setMethodSignature("common/LinkedList", "(Ljava/lang/Object;)V", "addLast");
//		p.setMethodSignature("common/LinkedList", "(Ljava/lang/Object;)Z", "add");
//		p.setMethodSignature("common/LinkedList", "()V", "clear");
//		p.setMethodSignature("common/LinkedList", "()Ljava/lang/Object;", "clone");
//		p.setMethodSignature("common/LinkedList", "(Ljava/lang/Object;)Z", "contains");
//		p.setMethodSignature("common/LinkedList", "()Ljava/lang/Object;", "getFirst");
//		p.setMethodSignature("common/LinkedList", "()Ljava/lang/Object;", "getLast");
//		p.setMethodSignature("common/LinkedList", "(I)Ljava/lang/Object;", "get");
//		p.setMethodSignature("common/LinkedList", "(Ljava/lang/Object;)I", "indexOf");
//		p.setMethodSignature("common/LinkedList", "(Ljava/lang/Object;)I", "lastIndexOf");
//		p.setMethodSignature("common/LinkedList", "()Ljava/lang/Object;", "removeFirst");
//		p.setMethodSignature("common/LinkedList", "(I)Ljava/lang/Object;", "remove");
//		p.setMethodSignature("common/LinkedList", "()Ljava/lang/Object;", "removeLast");
//		p.setMethodSignature("common/LinkedList", "(Ljava/lang/Object;)Z", "remove");
//		p.setMethodSignature("common/LinkedList", "(ILjava/lang/Object;)Ljava/lang/Object;", "set");
//		p.setMethodSignature("common/LinkedList", "()I", "size");
//		p.setMethodSignature("common/LinkedList", "()[Ljava/lang/Object;", "toArray");
		p.setMethodSignature("common/LinkedList", "(Lcommon/LinkedList$Entry;)Z", "inList");
		p.setStepShowMode(StepShowMode.LEAVES);
		p.setStateFormatMode(StateFormatMode.JUNIT_TEST);
		p.setShowContradictory(false);
		p.setOutputFileName(examplesHome + "out/list.txt");
		p.setDepthScope(20);
//		p.setGuided("avl/Main", "()V", "foo");
		//p.setStepShowMode(StepShowMode.ALL);
		//p.setStateFormatMode(StateFormatMode.TRACE);
	}
}

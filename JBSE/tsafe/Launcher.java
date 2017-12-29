package tsafe;

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
			SettingsReader sr = new SettingsReader(examplesHome + "settings/tsafe_accurate.jbse");
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
//		p.setMethodSignature("treemap/TreeMap", "(I)Z", "containsKey");
//		p.setMethodSignature("treemap/TreeMap", "(Ljava/lang/Object;)Z", "containsValue");
//		p.setMethodSignature("treemap/TreeMap", "()I", "firstKey");
//		p.setMethodSignature("treemap/TreeMap", "()I", "lastKey");
//		p.setMethodSignature("treemap/TreeMap", "(I)Ljava/lang/Object;", "get");
//		p.setMethodSignature("treemap/TreeMap", "(ILjava/lang/Object;)Ljava/lang/Object;", "put");
		p.setMethodSignature("tsafe/Driver_TS_R", "(Ltsafe/TrajectorySynthesizer;Ltsafe/RouteTrack;Ltsafe/Route;)V", "TS_R_3");
		p.setStepShowMode(StepShowMode.LEAVES);
		p.setStateFormatMode(StateFormatMode.JUNIT_TEST);
		p.setShowContradictory(false);
		p.setOutputFileName(examplesHome + "out/treemap.txt");
		p.setDepthScope(20);
//		p.setGuided("treemap/Main", "()V", "foo");
		//p.setStepShowMode(StepShowMode.ALL);
		//p.setStateFormatMode(StateFormatMode.TRACE);
	}
}

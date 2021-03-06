package gov.nasa.jpf.star.examples.tll;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Before;
import org.junit.Test;

import gov.nasa.jpf.star.data.DataNode;
import gov.nasa.jpf.star.data.DataNodeLexer;
import gov.nasa.jpf.star.data.DataNodeMap;
import gov.nasa.jpf.star.data.DataNodeParser;
import gov.nasa.jpf.star.precondition.Precondition;
import gov.nasa.jpf.star.precondition.PreconditionLexer;
import gov.nasa.jpf.star.precondition.PreconditionMap;
import gov.nasa.jpf.star.precondition.PreconditionParser;
import gov.nasa.jpf.star.predicate.InductivePred;
import gov.nasa.jpf.star.predicate.InductivePredLexer;
import gov.nasa.jpf.star.predicate.InductivePredMap;
import gov.nasa.jpf.star.predicate.InductivePredParser;
import gov.nasa.jpf.util.test.TestJPF;

@SuppressWarnings("deprecation")
public class Tll_countTest extends TestJPF {
	
	private void initDataNode() {
		String data = "data Node {int val; Node parent; Node left; Node right; Node next}";
		
		ANTLRInputStream in = new ANTLRInputStream(data);
		DataNodeLexer lexer = new DataNodeLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        DataNodeParser parser = new DataNodeParser(tokens);
		
        DataNode[] dns = parser.datas().dns;
        DataNodeMap.put(dns);
	}
	
	private void initPredicate() {
//		String pred1 = "pred tll(x) == x::Node<dv,dp,dl,r,dn> & r=null || x::Node<dv,dp,l,r,dn> * tll1(l,x,z,ll) * tll1(r,x,lr,z) & r!=null";
		String pred = "pred tll(curr,p,lr,ll) == curr::Node<dv,p,dl,r,n> & curr=ll & r=null & n=lr || curr::Node<dv,p,l,r,dn> * tll(l,curr,z,ll) * tll(r,curr,lr,z) & r!=null";
		
//		String pred = pred1 + ";" + pred2;
		
		ANTLRInputStream in = new ANTLRInputStream(pred);
		InductivePredLexer lexer = new InductivePredLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        InductivePredParser parser = new InductivePredParser(tokens);
        
        InductivePred[] ips = parser.preds().ips;
        InductivePredMap.put(ips);
	}
	
	private void initPrecondition() {
		String pre = "pre positiveLeafCount == tll(this_root,p,lr,ll)";
		
		ANTLRInputStream in = new ANTLRInputStream(pre);
		PreconditionLexer lexer = new PreconditionLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PreconditionParser parser = new PreconditionParser(tokens);
        
        Precondition[] ps = parser.pres().ps;
        PreconditionMap.put(ps);
	}
	
	@Before
	public void init() {
		initDataNode();
		initPredicate();
		initPrecondition();
	}
	
	@Test
	public void testMain() {
		long begin = System.currentTimeMillis();
		
		if (verifyNoPropertyViolation(
				"+listener=.star.StarListener",
				"+star.max_depth=3",
//				"+star.min_int=-100",
//				"+star.max_int=100",
				"+star.test_path=/Users/HongLongPham/Workspace/JPF_HOME/jpf-star/src/examples/gov/nasa/jpf/star/examples/tll",
				"+star.test_package=gov.nasa.jpf.star.examples.tll",
				"+star.test_imports=gov.nasa.jpf.star.examples.Utilities",
				"+classpath=build/examples", 
				"+sourcepath=src/examples",
				"+symbolic.method=gov.nasa.jpf.star.examples.tll.Tll.positiveLeafCount()",
				"+symbolic.fields=instance",
				"+symbolic.lazy=true")) {
			Tll tll = new Tll();
			tll.positiveLeafCount();
		}
		
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}

}

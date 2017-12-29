package ganttproject;

import java.util.Date;

import com.google.common.collect.Range;

public class ExplicitDependencyImpl implements DependencyEdge {
    TaskDependency myDep;
    Range<Date> myStartRange;
    Range<Date> myEndRange;
    Node mySrcNode;
    Node myDstNode;
    boolean isWeak = false;

    public ExplicitDependencyImpl(TaskDependency dep, Node srcNode, Node dstNode) {
      myDep = dep;
      mySrcNode = srcNode;
      myDstNode = dstNode;
    }

    public ExplicitDependencyImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public Range<Date> getStartRange() {
      return myStartRange;
    }

    @Override
    public Range<Date> getEndRange() {
      return myEndRange;
    }

    @Override
    public boolean isWeak() {
      return isWeak;
    }

    @Override
    public Node getDst() {
      return myDstNode;
    }

    @Override
    public Node getSrc() {
      return mySrcNode;
    }

    @Override
    public int hashCode() {
      return myDep.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof ExplicitDependencyImpl == false) {
        return false;
      }
      ExplicitDependencyImpl that = (ExplicitDependencyImpl) obj;
      return this.myDep.equals(that.myDep);
    }

    @Override
    public String toString() {
      return myDep.toString();
    }
  }

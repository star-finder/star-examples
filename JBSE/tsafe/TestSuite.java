package tsafe;

import static java.lang.System.identityHashCode;
import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.HashSet;
import sun.misc.Unsafe;

import org.junit.Test;

public class TestSuite {
    private static class AccessibleObject {
        private final Object target;
        AccessibleObject(Object o) {
            target = o;
        }
        void set(String fieldName, Object value) {
            try {
                final Field p = target.getClass().getDeclaredField(fieldName);
                p.setAccessible(true);
                p.set(target, value);
            } catch (IllegalArgumentException | IllegalAccessException
                | NoSuchFieldException | SecurityException e) {
                throw new RuntimeException(e);
            }
        }
        AccessibleObject get(String fieldName) {
            try {
                final Field p = target.getClass().getDeclaredField(fieldName);
                p.setAccessible(true);
                return new AccessibleObject(p.get(target));
            } catch (IllegalArgumentException | IllegalAccessException
                | NoSuchFieldException | SecurityException e) {
                throw new RuntimeException(e);
            }
        }
        Object getValue() {
            return target;
        }
    }

    private static final Unsafe UNSAFE; //ugly!

    static {
        final Field uns;
        try {
            uns = Unsafe.class.getDeclaredField("theUnsafe");
            uns.setAccessible(true);
            UNSAFE = (Unsafe) uns.get(null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static Object newInstance(String type) {
        try {
            final Class<?> clazz = Class.forName(type);
            return clazz.cast(UNSAFE.allocateInstance(clazz));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    private static Object newArray(String memberType, int length) {
        try {
            final Class<?> clazz = Class.forName(memberType);
            return Array.newInstance(clazz, length);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public class ObjectField {
        private final Object obj;
        private final Field fld;
        public ObjectField(Object obj, String fldName) {
            this.obj = obj;
            try {
                this.fld = obj.getClass().getDeclaredField(fldName);
            } catch (NoSuchFieldException | SecurityException e) {
                throw new RuntimeException(e);
            }
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((fld == null) ? 0 : fld.hashCode());
            result = prime * result + ((obj == null) ? 0 : identityHashCode(obj));
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final ObjectField other = (ObjectField) obj;
            if (this.fld == null) {
                if (other.fld != null) {
                    return false;
                }
            } else if (!fld.equals(other.fld)) {
                return false;
            }
            if (this.obj == null) {
                if (other.obj != null) {
                    return false;
                }
            } else if (this.obj != other.obj) {
                return false;
            }
            return true;
        }
    }

    public HashSet<ObjectField> nullObjectFields;

//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1 {ROOT}:route.fixes not expanded. It may be a hint of too strong user-defined constraints, possibly correct when enforcing redundancy by representation invariant.
    //Unable to generate test case 0 for state //.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1[191] (no numeric solution from the solver)
//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1 trace is safe.
    //Unable to generate test case 1 for state //.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2[51] (no numeric solution from the solver)
//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2 trace is safe.
//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2//.1 {ROOT}:route.fixes not expanded. It may be a hint of too strong user-defined constraints, possibly correct when enforcing redundancy by representation invariant.
    //Unable to generate test case 2 for state //.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2//.1[191] (no numeric solution from the solver)
//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2//.1 trace is safe.
    //Unable to generate test case 3 for state //.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2.2[51] (no numeric solution from the solver)
//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2.2 trace is safe.
    //Unable to generate test case 4 for state //.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2[51] (no numeric solution from the solver)
//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2 trace is safe.
//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2//.1//.1//.1 {ROOT}:route.fixes not expanded. It may be a hint of too strong user-defined constraints, possibly correct when enforcing redundancy by representation invariant.
    //Unable to generate test case 5 for state //.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2//.1//.1//.1[191] (no numeric solution from the solver)
//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2//.1//.1//.1 trace is safe.
    //Unable to generate test case 6 for state //.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2//.1//.1.2[51] (no numeric solution from the solver)
//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2//.1//.1.2 trace is safe.
//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2//.1.2//.1 {ROOT}:route.fixes not expanded. It may be a hint of too strong user-defined constraints, possibly correct when enforcing redundancy by representation invariant.
    //Unable to generate test case 7 for state //.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2//.1.2//.1[191] (no numeric solution from the solver)
//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2//.1.2//.1 trace is safe.
    //Unable to generate test case 8 for state //.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2//.1.2.2[51] (no numeric solution from the solver)
//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2//.1.2.2 trace is safe.
    //Unable to generate test case 9 for state //.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2.2[51] (no numeric solution from the solver)
//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2.2 trace is safe.
    //Unable to generate test case 10 for state //.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2[48] (no numeric solution from the solver)
//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2 trace is safe.
    //Unable to generate test case 11 for state //.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2[48] (no numeric solution from the solver)
//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2 trace is safe.
    @Test//(expected=java.lang.NullPointerException.class)
    public void test12() {
        //test case for state //.1//.1//.1//.1//.1//.1//.1//.1//.1.2[2]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = (tsafe.RouteTrack) newInstance("tsafe.RouteTrack"); // {R2} == Object[2] (fresh)
        tsafe.Route __ROOT_route = (tsafe.Route) newInstance("tsafe.Route"); // {R3} == Object[3] (fresh)
        new AccessibleObject(__ROOT_trajSynth).set("params", newInstance("tsafe.EngineParameters")); // {R5} == Object[4] (fresh)
        new AccessibleObject(__ROOT_trajSynth).get("params").set("tsTimeHorizon", 1);  // {V19} > 0
        new AccessibleObject(__ROOT_track).set("speed", 1.0);  // {V5} > 0.0
        new AccessibleObject(__ROOT_track).set("prevFix", newInstance("tsafe.Fix")); // {R6} == Object[5] (fresh)
        new AccessibleObject(__ROOT_track).set("nextFix", newInstance("tsafe.Fix")); // {R7} == Object[6] (fresh)
        ; // pre_init(tsafe/Point2D)
        new AccessibleObject(__ROOT_trajSynth).set("calc", null);this.nullObjectFields.add(new ObjectField(__ROOT_trajSynth, "calc")); // {R4} == null
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1//.1//.1//.1//.1//.1//.1//.1.2 trace is safe.
//.1//.1//.1//.1//.1//.1//.1//.1.2//.1//.1//.1//.1//.1 {ROOT}:route.fixes not expanded. It may be a hint of too strong user-defined constraints, possibly correct when enforcing redundancy by representation invariant.
    //Unable to generate test case 13 for state //.1//.1//.1//.1//.1//.1//.1//.1.2//.1//.1//.1//.1//.1[191] (no numeric solution from the solver)
//.1//.1//.1//.1//.1//.1//.1//.1.2//.1//.1//.1//.1//.1 trace is safe.
    //Unable to generate test case 14 for state //.1//.1//.1//.1//.1//.1//.1//.1.2//.1//.1//.1//.1.2[51] (no numeric solution from the solver)
//.1//.1//.1//.1//.1//.1//.1//.1.2//.1//.1//.1//.1.2 trace is safe.
    //Unable to generate test case 15 for state //.1//.1//.1//.1//.1//.1//.1//.1.2//.1//.1//.1.2[55] (no numeric solution from the solver)
//.1//.1//.1//.1//.1//.1//.1//.1.2//.1//.1//.1.2 trace is safe.
    //Unable to generate test case 16 for state //.1//.1//.1//.1//.1//.1//.1//.1.2//.1//.1.2[51] (no numeric solution from the solver)
//.1//.1//.1//.1//.1//.1//.1//.1.2//.1//.1.2 trace is safe.
    //Unable to generate test case 17 for state //.1//.1//.1//.1//.1//.1//.1//.1.2//.1.2[55] (no numeric solution from the solver)
//.1//.1//.1//.1//.1//.1//.1//.1.2//.1.2 trace is safe.
    @Test//(expected=java.lang.NullPointerException.class)
    public void test18() {
        //test case for state //.1//.1//.1//.1//.1//.1//.1//.1.2.2[2]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = (tsafe.RouteTrack) newInstance("tsafe.RouteTrack"); // {R2} == Object[2] (fresh)
        tsafe.Route __ROOT_route = (tsafe.Route) newInstance("tsafe.Route"); // {R3} == Object[3] (fresh)
        new AccessibleObject(__ROOT_trajSynth).set("params", newInstance("tsafe.EngineParameters")); // {R5} == Object[4] (fresh)
        new AccessibleObject(__ROOT_trajSynth).get("params").set("tsTimeHorizon", 1);  // {V19} > 0
        new AccessibleObject(__ROOT_track).set("speed", 1.0);  // {V5} > 0.0
        new AccessibleObject(__ROOT_track).set("prevFix", newInstance("tsafe.Fix")); // {R6} == Object[5] (fresh)
        new AccessibleObject(__ROOT_track).set("nextFix", new AccessibleObject(__ROOT_track).get("prevFix").getValue()); // {R7} == Object[5] (aliases {ROOT}:track.prevFix)
        ; // pre_init(tsafe/Point2D)
        new AccessibleObject(__ROOT_trajSynth).set("calc", null);this.nullObjectFields.add(new ObjectField(__ROOT_trajSynth, "calc")); // {R4} == null
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1//.1//.1//.1//.1//.1//.1.2.2 trace is safe.
    @Test//(expected=java.lang.NullPointerException.class)
    public void test19() {
        //test case for state //.1//.1//.1//.1//.1//.1//.1//.1.3//.1[71]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = (tsafe.RouteTrack) newInstance("tsafe.RouteTrack"); // {R2} == Object[2] (fresh)
        tsafe.Route __ROOT_route = (tsafe.Route) newInstance("tsafe.Route"); // {R3} == Object[3] (fresh)
        new AccessibleObject(__ROOT_trajSynth).set("params", newInstance("tsafe.EngineParameters")); // {R5} == Object[4] (fresh)
        new AccessibleObject(__ROOT_trajSynth).get("params").set("tsTimeHorizon", 1);  // {V19} > 0
        new AccessibleObject(__ROOT_track).set("speed", 1.0);  // {V5} > 0.0
        new AccessibleObject(__ROOT_track).set("prevFix", newInstance("tsafe.Fix")); // {R6} == Object[5] (fresh)
        new AccessibleObject(__ROOT_track).set("nextFix", null);this.nullObjectFields.add(new ObjectField(__ROOT_track, "nextFix")); // {R7} == null
        ; // pre_init(tsafe/Point2D)
        new AccessibleObject(__ROOT_trajSynth).set("calc", newInstance("tsafe.SimpleCalculator")); // {R4} == Object[7] (fresh)
        ; // pre_init(tsafe/PointXY)
        ; // pre_init(java/lang/Math)
        ; // pre_init(java/lang/StrictMath)
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1//.1//.1//.1//.1//.1//.1.3//.1 trace is safe.
    @Test//(expected=java.lang.NullPointerException.class)
    public void test20() {
        //test case for state //.1//.1//.1//.1//.1//.1//.1//.1.3.2[2]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = (tsafe.RouteTrack) newInstance("tsafe.RouteTrack"); // {R2} == Object[2] (fresh)
        tsafe.Route __ROOT_route = (tsafe.Route) newInstance("tsafe.Route"); // {R3} == Object[3] (fresh)
        new AccessibleObject(__ROOT_trajSynth).set("params", newInstance("tsafe.EngineParameters")); // {R5} == Object[4] (fresh)
        new AccessibleObject(__ROOT_trajSynth).get("params").set("tsTimeHorizon", 1);  // {V19} > 0
        new AccessibleObject(__ROOT_track).set("speed", 1.0);  // {V5} > 0.0
        new AccessibleObject(__ROOT_track).set("prevFix", newInstance("tsafe.Fix")); // {R6} == Object[5] (fresh)
        new AccessibleObject(__ROOT_track).set("nextFix", null);this.nullObjectFields.add(new ObjectField(__ROOT_track, "nextFix")); // {R7} == null
        ; // pre_init(tsafe/Point2D)
        new AccessibleObject(__ROOT_trajSynth).set("calc", null);this.nullObjectFields.add(new ObjectField(__ROOT_trajSynth, "calc")); // {R4} == null
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1//.1//.1//.1//.1//.1//.1.3.2 trace is safe.
    @Test//(expected=java.lang.NullPointerException.class)
    public void test21() {
        //test case for state //.1//.1//.1//.1//.1//.1//.1.2//.1//.1[5]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = (tsafe.RouteTrack) newInstance("tsafe.RouteTrack"); // {R2} == Object[2] (fresh)
        tsafe.Route __ROOT_route = (tsafe.Route) newInstance("tsafe.Route"); // {R3} == Object[3] (fresh)
        new AccessibleObject(__ROOT_trajSynth).set("params", newInstance("tsafe.EngineParameters")); // {R5} == Object[4] (fresh)
        new AccessibleObject(__ROOT_trajSynth).get("params").set("tsTimeHorizon", 1);  // {V19} > 0
        new AccessibleObject(__ROOT_track).set("speed", 1.0);  // {V5} > 0.0
        new AccessibleObject(__ROOT_track).set("prevFix", null);this.nullObjectFields.add(new ObjectField(__ROOT_track, "prevFix")); // {R6} == null
        new AccessibleObject(__ROOT_track).set("nextFix", newInstance("tsafe.Fix")); // {R7} == Object[5] (fresh)
        ; // pre_init(tsafe/Point2D)
        new AccessibleObject(__ROOT_trajSynth).set("calc", newInstance("tsafe.SimpleCalculator")); // {R4} == Object[7] (fresh)
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1//.1//.1//.1//.1//.1.2//.1//.1 trace is safe.
    @Test//(expected=java.lang.NullPointerException.class)
    public void test22() {
        //test case for state //.1//.1//.1//.1//.1//.1//.1.2//.1.2[2]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = (tsafe.RouteTrack) newInstance("tsafe.RouteTrack"); // {R2} == Object[2] (fresh)
        tsafe.Route __ROOT_route = (tsafe.Route) newInstance("tsafe.Route"); // {R3} == Object[3] (fresh)
        new AccessibleObject(__ROOT_trajSynth).set("params", newInstance("tsafe.EngineParameters")); // {R5} == Object[4] (fresh)
        new AccessibleObject(__ROOT_trajSynth).get("params").set("tsTimeHorizon", 1);  // {V19} > 0
        new AccessibleObject(__ROOT_track).set("speed", 1.0);  // {V5} > 0.0
        new AccessibleObject(__ROOT_track).set("prevFix", null);this.nullObjectFields.add(new ObjectField(__ROOT_track, "prevFix")); // {R6} == null
        new AccessibleObject(__ROOT_track).set("nextFix", newInstance("tsafe.Fix")); // {R7} == Object[5] (fresh)
        ; // pre_init(tsafe/Point2D)
        new AccessibleObject(__ROOT_trajSynth).set("calc", null);this.nullObjectFields.add(new ObjectField(__ROOT_trajSynth, "calc")); // {R4} == null
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1//.1//.1//.1//.1//.1.2//.1.2 trace is safe.
    @Test//(expected=java.lang.NullPointerException.class)
    public void test23() {
        //test case for state //.1//.1//.1//.1//.1//.1//.1.2.2//.1[5]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = (tsafe.RouteTrack) newInstance("tsafe.RouteTrack"); // {R2} == Object[2] (fresh)
        tsafe.Route __ROOT_route = (tsafe.Route) newInstance("tsafe.Route"); // {R3} == Object[3] (fresh)
        new AccessibleObject(__ROOT_trajSynth).set("params", newInstance("tsafe.EngineParameters")); // {R5} == Object[4] (fresh)
        new AccessibleObject(__ROOT_trajSynth).get("params").set("tsTimeHorizon", 1);  // {V19} > 0
        new AccessibleObject(__ROOT_track).set("speed", 1.0);  // {V5} > 0.0
        new AccessibleObject(__ROOT_track).set("prevFix", null);this.nullObjectFields.add(new ObjectField(__ROOT_track, "prevFix")); // {R6} == null
        new AccessibleObject(__ROOT_track).set("nextFix", null);this.nullObjectFields.add(new ObjectField(__ROOT_track, "nextFix")); // {R7} == null
        ; // pre_init(tsafe/Point2D)
        new AccessibleObject(__ROOT_trajSynth).set("calc", newInstance("tsafe.SimpleCalculator")); // {R4} == Object[6] (fresh)
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1//.1//.1//.1//.1//.1.2.2//.1 trace is safe.
    @Test//(expected=java.lang.NullPointerException.class)
    public void test24() {
        //test case for state //.1//.1//.1//.1//.1//.1//.1.2.2.2[2]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = (tsafe.RouteTrack) newInstance("tsafe.RouteTrack"); // {R2} == Object[2] (fresh)
        tsafe.Route __ROOT_route = (tsafe.Route) newInstance("tsafe.Route"); // {R3} == Object[3] (fresh)
        new AccessibleObject(__ROOT_trajSynth).set("params", newInstance("tsafe.EngineParameters")); // {R5} == Object[4] (fresh)
        new AccessibleObject(__ROOT_trajSynth).get("params").set("tsTimeHorizon", 1);  // {V19} > 0
        new AccessibleObject(__ROOT_track).set("speed", 1.0);  // {V5} > 0.0
        new AccessibleObject(__ROOT_track).set("prevFix", null);this.nullObjectFields.add(new ObjectField(__ROOT_track, "prevFix")); // {R6} == null
        new AccessibleObject(__ROOT_track).set("nextFix", null);this.nullObjectFields.add(new ObjectField(__ROOT_track, "nextFix")); // {R7} == null
        ; // pre_init(tsafe/Point2D)
        new AccessibleObject(__ROOT_trajSynth).set("calc", null);this.nullObjectFields.add(new ObjectField(__ROOT_trajSynth, "calc")); // {R4} == null
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1//.1//.1//.1//.1//.1.2.2.2 trace is safe.
    @Test//(expected=java.lang.RuntimeException.class)
    public void test25() {
        //test case for state //.1//.1//.1//.1//.1//.1.2[47]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = (tsafe.RouteTrack) newInstance("tsafe.RouteTrack"); // {R2} == Object[2] (fresh)
        tsafe.Route __ROOT_route = (tsafe.Route) newInstance("tsafe.Route"); // {R3} == Object[3] (fresh)
        new AccessibleObject(__ROOT_trajSynth).set("params", newInstance("tsafe.EngineParameters")); // {R5} == Object[4] (fresh)
        new AccessibleObject(__ROOT_trajSynth).get("params").set("tsTimeHorizon", 1);  // {V19} > 0
        new AccessibleObject(__ROOT_track).set("speed", 0.0);  // {V5} <= 0.0
        ; // pre_init(java/lang/RuntimeException)
        ; // pre_init(java/lang/Exception)
        ; // pre_init(java/lang/Throwable)
        ; // !pre_init(java/lang/String)
        ; // !pre_init(java/lang/String$CaseInsensitiveComparator)
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1//.1//.1//.1//.1.2 trace is safe.
    @Test//(expected=java.lang.RuntimeException.class)
    public void test26() {
        //test case for state //.1//.1//.1//.1//.1.2[47]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = (tsafe.RouteTrack) newInstance("tsafe.RouteTrack"); // {R2} == Object[2] (fresh)
        tsafe.Route __ROOT_route = (tsafe.Route) newInstance("tsafe.Route"); // {R3} == Object[3] (fresh)
        new AccessibleObject(__ROOT_trajSynth).set("params", newInstance("tsafe.EngineParameters")); // {R5} == Object[4] (fresh)
        new AccessibleObject(__ROOT_trajSynth).get("params").set("tsTimeHorizon", 0);  // {V19} <= 0
        ; // pre_init(java/lang/RuntimeException)
        ; // pre_init(java/lang/Exception)
        ; // pre_init(java/lang/Throwable)
        ; // !pre_init(java/lang/String)
        ; // !pre_init(java/lang/String$CaseInsensitiveComparator)
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1//.1//.1//.1.2 trace is safe.
    @Test//(expected=java.lang.NullPointerException.class)
    public void test27() {
        //test case for state //.1//.1//.1//.1.2[1]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = (tsafe.RouteTrack) newInstance("tsafe.RouteTrack"); // {R2} == Object[2] (fresh)
        tsafe.Route __ROOT_route = (tsafe.Route) newInstance("tsafe.Route"); // {R3} == Object[3] (fresh)
        new AccessibleObject(__ROOT_trajSynth).set("params", null);this.nullObjectFields.add(new ObjectField(__ROOT_trajSynth, "params")); // {R5} == null
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1//.1//.1.2 trace is safe.
//.1//.1//.1.2//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1 trace is safe.
    //Unable to generate test case 28 for state //.1//.1//.1.2//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1[188] (no numeric solution from the solver)
    //Unable to generate test case 29 for state //.1//.1//.1.2//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2[51] (no numeric solution from the solver)
//.1//.1//.1.2//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2 trace is safe.
    //Unable to generate test case 30 for state //.1//.1//.1.2//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2//.1[188] (no numeric solution from the solver)
//.1//.1//.1.2//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2//.1 trace is safe.
    //Unable to generate test case 31 for state //.1//.1//.1.2//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2.2[51] (no numeric solution from the solver)
//.1//.1//.1.2//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1.2.2 trace is safe.
    //Unable to generate test case 32 for state //.1//.1//.1.2//.1//.1//.1//.1//.1//.1//.1//.1//.1.2[51] (no numeric solution from the solver)
//.1//.1//.1.2//.1//.1//.1//.1//.1//.1//.1//.1//.1.2 trace is safe.
    //Unable to generate test case 33 for state //.1//.1//.1.2//.1//.1//.1//.1//.1//.1//.1//.1.2//.1//.1//.1[188] (no numeric solution from the solver)
//.1//.1//.1.2//.1//.1//.1//.1//.1//.1//.1//.1.2//.1//.1//.1 trace is safe.
    //Unable to generate test case 34 for state //.1//.1//.1.2//.1//.1//.1//.1//.1//.1//.1//.1.2//.1//.1.2[51] (no numeric solution from the solver)
//.1//.1//.1.2//.1//.1//.1//.1//.1//.1//.1//.1.2//.1//.1.2 trace is safe.
    //Unable to generate test case 35 for state //.1//.1//.1.2//.1//.1//.1//.1//.1//.1//.1//.1.2//.1.2//.1[188] (no numeric solution from the solver)
//.1//.1//.1.2//.1//.1//.1//.1//.1//.1//.1//.1.2//.1.2//.1 trace is safe.
    //Unable to generate test case 36 for state //.1//.1//.1.2//.1//.1//.1//.1//.1//.1//.1//.1.2//.1.2.2[51] (no numeric solution from the solver)
//.1//.1//.1.2//.1//.1//.1//.1//.1//.1//.1//.1.2//.1.2.2 trace is safe.
    //Unable to generate test case 37 for state //.1//.1//.1.2//.1//.1//.1//.1//.1//.1//.1//.1.2.2[51] (no numeric solution from the solver)
//.1//.1//.1.2//.1//.1//.1//.1//.1//.1//.1//.1.2.2 trace is safe.
    //Unable to generate test case 38 for state //.1//.1//.1.2//.1//.1//.1//.1//.1//.1//.1.2[48] (no numeric solution from the solver)
//.1//.1//.1.2//.1//.1//.1//.1//.1//.1//.1.2 trace is safe.
    //Unable to generate test case 39 for state //.1//.1//.1.2//.1//.1//.1//.1//.1//.1.2[48] (no numeric solution from the solver)
//.1//.1//.1.2//.1//.1//.1//.1//.1//.1.2 trace is safe.
    @Test//(expected=java.lang.NullPointerException.class)
    public void test40() {
        //test case for state //.1//.1//.1.2//.1//.1//.1//.1//.1.2[2]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = (tsafe.RouteTrack) newInstance("tsafe.RouteTrack"); // {R2} == Object[2] (fresh)
        tsafe.Route __ROOT_route = null; // {R3} == null
        new AccessibleObject(__ROOT_trajSynth).set("params", newInstance("tsafe.EngineParameters")); // {R5} == Object[3] (fresh)
        new AccessibleObject(__ROOT_trajSynth).get("params").set("tsTimeHorizon", 1);  // {V18} > 0
        new AccessibleObject(__ROOT_track).set("speed", 1.0);  // {V5} > 0.0
        new AccessibleObject(__ROOT_track).set("prevFix", newInstance("tsafe.Fix")); // {R6} == Object[4] (fresh)
        new AccessibleObject(__ROOT_track).set("nextFix", newInstance("tsafe.Fix")); // {R7} == Object[5] (fresh)
        ; // pre_init(tsafe/Point2D)
        new AccessibleObject(__ROOT_trajSynth).set("calc", null);this.nullObjectFields.add(new ObjectField(__ROOT_trajSynth, "calc")); // {R4} == null
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1//.1.2//.1//.1//.1//.1//.1.2 trace is safe.
    //Unable to generate test case 41 for state //.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1//.1//.1//.1[188] (no numeric solution from the solver)
//.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1//.1//.1//.1 trace is safe.
    //Unable to generate test case 42 for state //.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1//.1//.1.2[51] (no numeric solution from the solver)
//.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1//.1//.1.2 trace is safe.
    //Unable to generate test case 43 for state //.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1//.1.2[55] (no numeric solution from the solver)
//.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1//.1.2 trace is safe.
    //Unable to generate test case 44 for state //.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1.2[51] (no numeric solution from the solver)
//.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1.2 trace is safe.
    //Unable to generate test case 45 for state //.1//.1//.1.2//.1//.1//.1//.1.2//.1.2[55] (no numeric solution from the solver)
//.1//.1//.1.2//.1//.1//.1//.1.2//.1.2 trace is safe.
    @Test//(expected=java.lang.NullPointerException.class)
    public void test46() {
        //test case for state //.1//.1//.1.2//.1//.1//.1//.1.2.2[2]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = (tsafe.RouteTrack) newInstance("tsafe.RouteTrack"); // {R2} == Object[2] (fresh)
        tsafe.Route __ROOT_route = null; // {R3} == null
        new AccessibleObject(__ROOT_trajSynth).set("params", newInstance("tsafe.EngineParameters")); // {R5} == Object[3] (fresh)
        new AccessibleObject(__ROOT_trajSynth).get("params").set("tsTimeHorizon", 1);  // {V18} > 0
        new AccessibleObject(__ROOT_track).set("speed", 1.0);  // {V5} > 0.0
        new AccessibleObject(__ROOT_track).set("prevFix", newInstance("tsafe.Fix")); // {R6} == Object[4] (fresh)
        new AccessibleObject(__ROOT_track).set("nextFix", new AccessibleObject(__ROOT_track).get("prevFix").getValue()); // {R7} == Object[4] (aliases {ROOT}:track.prevFix)
        ; // pre_init(tsafe/Point2D)
        new AccessibleObject(__ROOT_trajSynth).set("calc", null);this.nullObjectFields.add(new ObjectField(__ROOT_trajSynth, "calc")); // {R4} == null
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1//.1.2//.1//.1//.1//.1.2.2 trace is safe.
    @Test//(expected=java.lang.NullPointerException.class)
    public void test47() {
        //test case for state //.1//.1//.1.2//.1//.1//.1//.1.3//.1[71]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = (tsafe.RouteTrack) newInstance("tsafe.RouteTrack"); // {R2} == Object[2] (fresh)
        tsafe.Route __ROOT_route = null; // {R3} == null
        new AccessibleObject(__ROOT_trajSynth).set("params", newInstance("tsafe.EngineParameters")); // {R5} == Object[3] (fresh)
        new AccessibleObject(__ROOT_trajSynth).get("params").set("tsTimeHorizon", 1);  // {V18} > 0
        new AccessibleObject(__ROOT_track).set("speed", 1.0);  // {V5} > 0.0
        new AccessibleObject(__ROOT_track).set("prevFix", newInstance("tsafe.Fix")); // {R6} == Object[4] (fresh)
        new AccessibleObject(__ROOT_track).set("nextFix", null);this.nullObjectFields.add(new ObjectField(__ROOT_track, "nextFix")); // {R7} == null
        ; // pre_init(tsafe/Point2D)
        new AccessibleObject(__ROOT_trajSynth).set("calc", newInstance("tsafe.SimpleCalculator")); // {R4} == Object[6] (fresh)
        ; // pre_init(tsafe/PointXY)
        ; // pre_init(java/lang/Math)
        ; // pre_init(java/lang/StrictMath)
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1//.1.2//.1//.1//.1//.1.3//.1 trace is safe.
    @Test//(expected=java.lang.NullPointerException.class)
    public void test48() {
        //test case for state //.1//.1//.1.2//.1//.1//.1//.1.3.2[2]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = (tsafe.RouteTrack) newInstance("tsafe.RouteTrack"); // {R2} == Object[2] (fresh)
        tsafe.Route __ROOT_route = null; // {R3} == null
        new AccessibleObject(__ROOT_trajSynth).set("params", newInstance("tsafe.EngineParameters")); // {R5} == Object[3] (fresh)
        new AccessibleObject(__ROOT_trajSynth).get("params").set("tsTimeHorizon", 1);  // {V18} > 0
        new AccessibleObject(__ROOT_track).set("speed", 1.0);  // {V5} > 0.0
        new AccessibleObject(__ROOT_track).set("prevFix", newInstance("tsafe.Fix")); // {R6} == Object[4] (fresh)
        new AccessibleObject(__ROOT_track).set("nextFix", null);this.nullObjectFields.add(new ObjectField(__ROOT_track, "nextFix")); // {R7} == null
        ; // pre_init(tsafe/Point2D)
        new AccessibleObject(__ROOT_trajSynth).set("calc", null);this.nullObjectFields.add(new ObjectField(__ROOT_trajSynth, "calc")); // {R4} == null
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1//.1.2//.1//.1//.1//.1.3.2 trace is safe.
    @Test//(expected=java.lang.NullPointerException.class)
    public void test49() {
        //test case for state //.1//.1//.1.2//.1//.1//.1.2//.1//.1[5]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = (tsafe.RouteTrack) newInstance("tsafe.RouteTrack"); // {R2} == Object[2] (fresh)
        tsafe.Route __ROOT_route = null; // {R3} == null
        new AccessibleObject(__ROOT_trajSynth).set("params", newInstance("tsafe.EngineParameters")); // {R5} == Object[3] (fresh)
        new AccessibleObject(__ROOT_trajSynth).get("params").set("tsTimeHorizon", 1);  // {V18} > 0
        new AccessibleObject(__ROOT_track).set("speed", 1.0);  // {V5} > 0.0
        new AccessibleObject(__ROOT_track).set("prevFix", null);this.nullObjectFields.add(new ObjectField(__ROOT_track, "prevFix")); // {R6} == null
        new AccessibleObject(__ROOT_track).set("nextFix", newInstance("tsafe.Fix")); // {R7} == Object[4] (fresh)
        ; // pre_init(tsafe/Point2D)
        new AccessibleObject(__ROOT_trajSynth).set("calc", newInstance("tsafe.SimpleCalculator")); // {R4} == Object[6] (fresh)
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1//.1.2//.1//.1//.1.2//.1//.1 trace is safe.
    @Test//(expected=java.lang.NullPointerException.class)
    public void test50() {
        //test case for state //.1//.1//.1.2//.1//.1//.1.2//.1.2[2]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = (tsafe.RouteTrack) newInstance("tsafe.RouteTrack"); // {R2} == Object[2] (fresh)
        tsafe.Route __ROOT_route = null; // {R3} == null
        new AccessibleObject(__ROOT_trajSynth).set("params", newInstance("tsafe.EngineParameters")); // {R5} == Object[3] (fresh)
        new AccessibleObject(__ROOT_trajSynth).get("params").set("tsTimeHorizon", 1);  // {V18} > 0
        new AccessibleObject(__ROOT_track).set("speed", 1.0);  // {V5} > 0.0
        new AccessibleObject(__ROOT_track).set("prevFix", null);this.nullObjectFields.add(new ObjectField(__ROOT_track, "prevFix")); // {R6} == null
        new AccessibleObject(__ROOT_track).set("nextFix", newInstance("tsafe.Fix")); // {R7} == Object[4] (fresh)
        ; // pre_init(tsafe/Point2D)
        new AccessibleObject(__ROOT_trajSynth).set("calc", null);this.nullObjectFields.add(new ObjectField(__ROOT_trajSynth, "calc")); // {R4} == null
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1//.1.2//.1//.1//.1.2//.1.2 trace is safe.
    @Test//(expected=java.lang.NullPointerException.class)
    public void test51() {
        //test case for state //.1//.1//.1.2//.1//.1//.1.2.2//.1[5]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = (tsafe.RouteTrack) newInstance("tsafe.RouteTrack"); // {R2} == Object[2] (fresh)
        tsafe.Route __ROOT_route = null; // {R3} == null
        new AccessibleObject(__ROOT_trajSynth).set("params", newInstance("tsafe.EngineParameters")); // {R5} == Object[3] (fresh)
        new AccessibleObject(__ROOT_trajSynth).get("params").set("tsTimeHorizon", 1);  // {V18} > 0
        new AccessibleObject(__ROOT_track).set("speed", 1.0);  // {V5} > 0.0
        new AccessibleObject(__ROOT_track).set("prevFix", null);this.nullObjectFields.add(new ObjectField(__ROOT_track, "prevFix")); // {R6} == null
        new AccessibleObject(__ROOT_track).set("nextFix", null);this.nullObjectFields.add(new ObjectField(__ROOT_track, "nextFix")); // {R7} == null
        ; // pre_init(tsafe/Point2D)
        new AccessibleObject(__ROOT_trajSynth).set("calc", newInstance("tsafe.SimpleCalculator")); // {R4} == Object[5] (fresh)
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1//.1.2//.1//.1//.1.2.2//.1 trace is safe.
    @Test//(expected=java.lang.NullPointerException.class)
    public void test52() {
        //test case for state //.1//.1//.1.2//.1//.1//.1.2.2.2[2]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = (tsafe.RouteTrack) newInstance("tsafe.RouteTrack"); // {R2} == Object[2] (fresh)
        tsafe.Route __ROOT_route = null; // {R3} == null
        new AccessibleObject(__ROOT_trajSynth).set("params", newInstance("tsafe.EngineParameters")); // {R5} == Object[3] (fresh)
        new AccessibleObject(__ROOT_trajSynth).get("params").set("tsTimeHorizon", 1);  // {V18} > 0
        new AccessibleObject(__ROOT_track).set("speed", 1.0);  // {V5} > 0.0
        new AccessibleObject(__ROOT_track).set("prevFix", null);this.nullObjectFields.add(new ObjectField(__ROOT_track, "prevFix")); // {R6} == null
        new AccessibleObject(__ROOT_track).set("nextFix", null);this.nullObjectFields.add(new ObjectField(__ROOT_track, "nextFix")); // {R7} == null
        ; // pre_init(tsafe/Point2D)
        new AccessibleObject(__ROOT_trajSynth).set("calc", null);this.nullObjectFields.add(new ObjectField(__ROOT_trajSynth, "calc")); // {R4} == null
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1//.1.2//.1//.1//.1.2.2.2 trace is safe.
    @Test//(expected=java.lang.RuntimeException.class)
    public void test53() {
        //test case for state //.1//.1//.1.2//.1//.1.2[47]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = (tsafe.RouteTrack) newInstance("tsafe.RouteTrack"); // {R2} == Object[2] (fresh)
        tsafe.Route __ROOT_route = null; // {R3} == null
        new AccessibleObject(__ROOT_trajSynth).set("params", newInstance("tsafe.EngineParameters")); // {R5} == Object[3] (fresh)
        new AccessibleObject(__ROOT_trajSynth).get("params").set("tsTimeHorizon", 1);  // {V18} > 0
        new AccessibleObject(__ROOT_track).set("speed", 0.0);  // {V5} <= 0.0
        ; // pre_init(java/lang/RuntimeException)
        ; // pre_init(java/lang/Exception)
        ; // pre_init(java/lang/Throwable)
        ; // !pre_init(java/lang/String)
        ; // !pre_init(java/lang/String$CaseInsensitiveComparator)
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1//.1.2//.1//.1.2 trace is safe.
    @Test//(expected=java.lang.RuntimeException.class)
    public void test54() {
        //test case for state //.1//.1//.1.2//.1.2[47]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = (tsafe.RouteTrack) newInstance("tsafe.RouteTrack"); // {R2} == Object[2] (fresh)
        tsafe.Route __ROOT_route = null; // {R3} == null
        new AccessibleObject(__ROOT_trajSynth).set("params", newInstance("tsafe.EngineParameters")); // {R5} == Object[3] (fresh)
        new AccessibleObject(__ROOT_trajSynth).get("params").set("tsTimeHorizon", 0);  // {V18} <= 0
        ; // pre_init(java/lang/RuntimeException)
        ; // pre_init(java/lang/Exception)
        ; // pre_init(java/lang/Throwable)
        ; // !pre_init(java/lang/String)
        ; // !pre_init(java/lang/String$CaseInsensitiveComparator)
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1//.1.2//.1.2 trace is safe.
    @Test//(expected=java.lang.NullPointerException.class)
    public void test55() {
        //test case for state //.1//.1//.1.2.2[1]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = (tsafe.RouteTrack) newInstance("tsafe.RouteTrack"); // {R2} == Object[2] (fresh)
        tsafe.Route __ROOT_route = null; // {R3} == null
        new AccessibleObject(__ROOT_trajSynth).set("params", null);this.nullObjectFields.add(new ObjectField(__ROOT_trajSynth, "params")); // {R5} == null
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1//.1.2.2 trace is safe.
    @Test//(expected=java.lang.NullPointerException.class)
    public void test56() {
        //test case for state //.1//.1.2//.1//.1//.1[9]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = null; // {R2} == null
        tsafe.Route __ROOT_route = (tsafe.Route) newInstance("tsafe.Route"); // {R3} == Object[2] (fresh)
        new AccessibleObject(__ROOT_trajSynth).set("params", newInstance("tsafe.EngineParameters")); // {R5} == Object[3] (fresh)
        new AccessibleObject(__ROOT_trajSynth).get("params").set("tsTimeHorizon", 1);  // {V12} > 0
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1.2//.1//.1//.1 trace is safe.
//.1//.1.2//.1//.1.2 trace is safe.
    @Test//(expected=java.lang.RuntimeException.class)
    public void test57() {
        //test case for state //.1//.1.2//.1//.1.2[47]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = null; // {R2} == null
        tsafe.Route __ROOT_route = (tsafe.Route) newInstance("tsafe.Route"); // {R3} == Object[2] (fresh)
        new AccessibleObject(__ROOT_trajSynth).set("params", newInstance("tsafe.EngineParameters")); // {R5} == Object[3] (fresh)
        new AccessibleObject(__ROOT_trajSynth).get("params").set("tsTimeHorizon", 0);  // {V12} <= 0
        ; // pre_init(java/lang/RuntimeException)
        ; // pre_init(java/lang/Exception)
        ; // pre_init(java/lang/Throwable)
        ; // !pre_init(java/lang/String)
        ; // !pre_init(java/lang/String$CaseInsensitiveComparator)
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @Test//(expected=java.lang.NullPointerException.class)
    public void test58() {
        //test case for state //.1//.1.2//.1.2[1]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = null; // {R2} == null
        tsafe.Route __ROOT_route = (tsafe.Route) newInstance("tsafe.Route"); // {R3} == Object[2] (fresh)
        new AccessibleObject(__ROOT_trajSynth).set("params", null);this.nullObjectFields.add(new ObjectField(__ROOT_trajSynth, "params")); // {R5} == null
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1.2//.1.2 trace is safe.
    @Test//(expected=java.lang.NullPointerException.class)
    public void test59() {
        //test case for state //.1//.1.2.2//.1//.1[9]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = null; // {R2} == null
        tsafe.Route __ROOT_route = null; // {R3} == null
        new AccessibleObject(__ROOT_trajSynth).set("params", newInstance("tsafe.EngineParameters")); // {R5} == Object[2] (fresh)
        new AccessibleObject(__ROOT_trajSynth).get("params").set("tsTimeHorizon", 1);  // {V11} > 0
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1.2.2//.1//.1 trace is safe.
    @Test//(expected=java.lang.RuntimeException.class)
    public void test60() {
        //test case for state //.1//.1.2.2//.1.2[47]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = null; // {R2} == null
        tsafe.Route __ROOT_route = null; // {R3} == null
        new AccessibleObject(__ROOT_trajSynth).set("params", newInstance("tsafe.EngineParameters")); // {R5} == Object[2] (fresh)
        new AccessibleObject(__ROOT_trajSynth).get("params").set("tsTimeHorizon", 0);  // {V11} <= 0
        ; // pre_init(java/lang/RuntimeException)
        ; // pre_init(java/lang/Exception)
        ; // pre_init(java/lang/Throwable)
        ; // !pre_init(java/lang/String)
        ; // !pre_init(java/lang/String$CaseInsensitiveComparator)
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1.2.2//.1.2 trace is safe.
    @Test//(expected=java.lang.NullPointerException.class)
    public void test61() {
        //test case for state //.1//.1.2.2.2[1]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = (tsafe.TrajectorySynthesizer) newInstance("tsafe.TrajectorySynthesizer"); // {R1} == Object[1] (fresh)
        tsafe.RouteTrack __ROOT_track = null; // {R2} == null
        tsafe.Route __ROOT_route = null; // {R3} == null
        new AccessibleObject(__ROOT_trajSynth).set("params", null);this.nullObjectFields.add(new ObjectField(__ROOT_trajSynth, "params")); // {R5} == null
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1//.1.2.2.2 trace is safe.
    @Test//(expected=java.lang.NullPointerException.class)
    public void test62() {
        //test case for state //.1.2//.1//.1[9]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = null; // {R1} == null
        tsafe.RouteTrack __ROOT_track = (tsafe.RouteTrack) newInstance("tsafe.RouteTrack"); // {R2} == Object[1] (fresh)
        tsafe.Route __ROOT_route = (tsafe.Route) newInstance("tsafe.Route"); // {R3} == Object[2] (fresh)
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1.2//.1//.1 trace is safe.
    @Test//(expected=java.lang.NullPointerException.class)
    public void test63() {
        //test case for state //.1.2//.1.2[9]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = null; // {R1} == null
        tsafe.RouteTrack __ROOT_track = (tsafe.RouteTrack) newInstance("tsafe.RouteTrack"); // {R2} == Object[1] (fresh)
        tsafe.Route __ROOT_route = null; // {R3} == null
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1.2//.1.2 trace is safe.
    @Test//(expected=java.lang.NullPointerException.class)
    public void test64() {
        //test case for state //.1.2.2//.1[9]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = null; // {R1} == null
        tsafe.RouteTrack __ROOT_track = null; // {R2} == null
        tsafe.Route __ROOT_route = (tsafe.Route) newInstance("tsafe.Route"); // {R3} == Object[1] (fresh)
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1.2.2//.1 trace is safe.
    @Test//(expected=java.lang.NullPointerException.class)
    public void test65() {
        //test case for state //.1.2.2.2[9]
        this.nullObjectFields = new HashSet<>();
        tsafe.Driver_TS_R __ROOT_this = (tsafe.Driver_TS_R) newInstance("tsafe.Driver_TS_R"); // {R0} == Object[0] (fresh)
        ; // !pre_init(tsafe/Driver_TS_R)
        ; // !pre_init(java/lang/Object)
        tsafe.TrajectorySynthesizer __ROOT_trajSynth = null; // {R1} == null
        tsafe.RouteTrack __ROOT_track = null; // {R2} == null
        tsafe.Route __ROOT_route = null; // {R3} == null
        try {
			assertTrue(__ROOT_this.repOK(__ROOT_trajSynth, __ROOT_track, __ROOT_route));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//.1.2.2.2 trace is safe.
}

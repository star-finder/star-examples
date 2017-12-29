package avl_tree;

import static java.lang.System.identityHashCode;
import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.HashSet;
import sun.misc.Unsafe;

import org.junit.Test;

public class AvlTree_minElementTest {
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

//.1//.1 {ROOT}:this.root.parent not expanded. It may be a hint of too strong user-defined constraints, possibly correct when enforcing redundancy by representation invariant.
    @Test
    public void test0() {
        //test case for state //.1//.1//.1//.1[3]
        this.nullObjectFields = new HashSet<>();
        avl_tree.AvlTree __ROOT_this = (avl_tree.AvlTree) newInstance("avl_tree.AvlTree"); // {R0} == Object[0] (fresh)
        ; // pre_init(avl_tree/AvlTree)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("root", newInstance("avl_tree.AvlNode")); // {R1} == Object[1] (fresh)
        ; // pre_init(avl_tree/AvlNode)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R2} == null
        new AccessibleObject(__ROOT_this).get("root").set("height", 0);  // {V4} >= 0
        ; // pre_init(jbse/meta/Analysis)
        ; // pre_init(avl_tree/Range)
        int __ROOT_minE = 1;new AccessibleObject(__ROOT_this).get("root").set("element", 0);   // {V1} > {V3}
        if (__ROOT_this.repOK()) {System.out.println("true"); __ROOT_this.minElement(__ROOT_minE);}
        //assertTrue(__returnedValue == false);
    }
//.1//.1//.1//.1 trace is safe.
//.1//.1//.1.2//.1 {ROOT}:this.root.left.parent not expanded. It may be a hint of too strong user-defined constraints, possibly correct when enforcing redundancy by representation invariant.
    @Test
    public void test1() {
        //test case for state //.1//.1//.1.2//.1//.1//.1//.1//.1[6]
        this.nullObjectFields = new HashSet<>();
        avl_tree.AvlTree __ROOT_this = (avl_tree.AvlTree) newInstance("avl_tree.AvlTree"); // {R0} == Object[0] (fresh)
        ; // pre_init(avl_tree/AvlTree)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("root", newInstance("avl_tree.AvlNode")); // {R1} == Object[1] (fresh)
        ; // pre_init(avl_tree/AvlNode)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R2} == null
        new AccessibleObject(__ROOT_this).get("root").set("height", 1);  // {V4} >= 0
        ; // pre_init(jbse/meta/Analysis)
        ; // pre_init(avl_tree/Range)
        int __ROOT_minE = -1;new AccessibleObject(__ROOT_this).get("root").set("element", 0);   // {V1} <= {V3}
        new AccessibleObject(__ROOT_this).get("root").set("left", newInstance("avl_tree.AvlNode")); // {R3} == Object[3] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("parent", new AccessibleObject(__ROOT_this).get("root").getValue()); // {R10} == Object[1] (aliases {ROOT}:this.root)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("height", 0);  // {V12} >= 0
        new AccessibleObject(__ROOT_this).get("root").get("left").set("element", -2);  // {V11} < {V3}
        ; // !pre_init(java/lang/String)
        ; // !pre_init(java/lang/String$CaseInsensitiveComparator)
         // {V4} >= 1
         // {V1} > {V11}
        if (__ROOT_this.repOK()) {System.out.println("true"); __ROOT_this.minElement(__ROOT_minE);}
        //assertTrue(__returnedValue == false);
    }
//.1//.1//.1.2//.1//.1//.1//.1//.1 trace is safe.
//.1//.1//.1.2//.1//.1//.1//.1.2//.1 {ROOT}:this.root.left.left.parent not expanded. It may be a hint of too strong user-defined constraints, possibly correct when enforcing redundancy by representation invariant.
    @Test
    public void test2() {
        //test case for state //.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1//.1//.1//.1[9]
        this.nullObjectFields = new HashSet<>();
        avl_tree.AvlTree __ROOT_this = (avl_tree.AvlTree) newInstance("avl_tree.AvlTree"); // {R0} == Object[0] (fresh)
        ; // pre_init(avl_tree/AvlTree)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("root", newInstance("avl_tree.AvlNode")); // {R1} == Object[1] (fresh)
        ; // pre_init(avl_tree/AvlNode)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R2} == null
        new AccessibleObject(__ROOT_this).get("root").set("height", 1);  // {V4} >= 0
        ; // pre_init(jbse/meta/Analysis)
        ; // pre_init(avl_tree/Range)
        int __ROOT_minE = -3;new AccessibleObject(__ROOT_this).get("root").set("element", 0);   // {V1} <= {V3}
        new AccessibleObject(__ROOT_this).get("root").set("left", newInstance("avl_tree.AvlNode")); // {R3} == Object[3] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("parent", new AccessibleObject(__ROOT_this).get("root").getValue()); // {R10} == Object[1] (aliases {ROOT}:this.root)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("height", 1);  // {V12} >= 0
        new AccessibleObject(__ROOT_this).get("root").get("left").set("element", -2);  // {V11} < {V3}
        ; // !pre_init(java/lang/String)
        ; // !pre_init(java/lang/String$CaseInsensitiveComparator)
         // {V4} >= 1
         // {V1} <= {V11}
        new AccessibleObject(__ROOT_this).get("root").get("left").set("left", newInstance("avl_tree.AvlNode")); // {R11} == Object[9] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").set("parent", new AccessibleObject(__ROOT_this).get("root").get("left").getValue()); // {R17} == Object[3] (aliases {ROOT}:this.root.left)
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").set("height", 0);  // {V17} >= 0
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").set("element", -4);  // {V16} < {V11}
         // {V12} >= 1
         // {V1} > {V16}
        if (__ROOT_this.repOK()) {System.out.println("true"); __ROOT_this.minElement(__ROOT_minE);}
        //assertTrue(__returnedValue == false);
    }
//.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1//.1//.1//.1 trace is safe.
//.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1//.1//.1.2//.1 {ROOT}:this.root.left.left.left.parent not expanded. It may be a hint of too strong user-defined constraints, possibly correct when enforcing redundancy by representation invariant.
    @Test
    public void test3() {
        //test case for state //.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1//.1[0]
        this.nullObjectFields = new HashSet<>();
        avl_tree.AvlTree __ROOT_this = (avl_tree.AvlTree) newInstance("avl_tree.AvlTree"); // {R0} == Object[0] (fresh)
        ; // pre_init(avl_tree/AvlTree)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("root", newInstance("avl_tree.AvlNode")); // {R1} == Object[1] (fresh)
        ; // pre_init(avl_tree/AvlNode)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R2} == null
        new AccessibleObject(__ROOT_this).get("root").set("height", 1);  // {V4} >= 0
        ; // pre_init(jbse/meta/Analysis)
        ; // pre_init(avl_tree/Range)
        int __ROOT_minE = -3;new AccessibleObject(__ROOT_this).get("root").set("element", 0);   // {V1} <= {V3}
        new AccessibleObject(__ROOT_this).get("root").set("left", newInstance("avl_tree.AvlNode")); // {R3} == Object[3] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("parent", new AccessibleObject(__ROOT_this).get("root").getValue()); // {R10} == Object[1] (aliases {ROOT}:this.root)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("height", 1);  // {V12} >= 0
        new AccessibleObject(__ROOT_this).get("root").get("left").set("element", -2);  // {V11} < {V3}
        ; // !pre_init(java/lang/String)
        ; // !pre_init(java/lang/String$CaseInsensitiveComparator)
         // {V4} >= 1
         // {V1} <= {V11}
        new AccessibleObject(__ROOT_this).get("root").get("left").set("left", newInstance("avl_tree.AvlNode")); // {R11} == Object[9] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").set("parent", new AccessibleObject(__ROOT_this).get("root").get("left").getValue()); // {R17} == Object[3] (aliases {ROOT}:this.root.left)
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").set("height", 0);  // {V17} >= 0
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").set("element", -3);  // {V16} < {V11}
         // {V12} >= 1
         // {V1} <= {V16}
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").set("left", newInstance("avl_tree.AvlNode")); // {R18} == Object[11] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").get("left").set("parent", new AccessibleObject(__ROOT_this).get("root").get("left").get("left").getValue()); // {R24} == Object[9] (aliases {ROOT}:this.root.left.left)
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").get("left").set("height", 0);  // {V22} >= 0
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").get("left").set("element", -4);  // {V21} < {V16}
        if (__ROOT_this.repOK()) {System.out.println("true"); __ROOT_this.minElement(__ROOT_minE);}
    }
//.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1//.1 trace exhausted depth scope.
    @Test
    public void test4() {
        //test case for state //.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1.2[1]
        this.nullObjectFields = new HashSet<>();
        avl_tree.AvlTree __ROOT_this = (avl_tree.AvlTree) newInstance("avl_tree.AvlTree"); // {R0} == Object[0] (fresh)
        ; // pre_init(avl_tree/AvlTree)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("root", newInstance("avl_tree.AvlNode")); // {R1} == Object[1] (fresh)
        ; // pre_init(avl_tree/AvlNode)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R2} == null
        new AccessibleObject(__ROOT_this).get("root").set("height", 1);  // {V4} >= 0
        ; // pre_init(jbse/meta/Analysis)
        ; // pre_init(avl_tree/Range)
        int __ROOT_minE = -3;new AccessibleObject(__ROOT_this).get("root").set("element", 0);   // {V1} <= {V3}
        new AccessibleObject(__ROOT_this).get("root").set("left", newInstance("avl_tree.AvlNode")); // {R3} == Object[3] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("parent", new AccessibleObject(__ROOT_this).get("root").getValue()); // {R10} == Object[1] (aliases {ROOT}:this.root)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("height", 1);  // {V12} >= 0
        new AccessibleObject(__ROOT_this).get("root").get("left").set("element", -2);  // {V11} < {V3}
        ; // !pre_init(java/lang/String)
        ; // !pre_init(java/lang/String$CaseInsensitiveComparator)
         // {V4} >= 1
         // {V1} <= {V11}
        new AccessibleObject(__ROOT_this).get("root").get("left").set("left", newInstance("avl_tree.AvlNode")); // {R11} == Object[9] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").set("parent", new AccessibleObject(__ROOT_this).get("root").get("left").getValue()); // {R17} == Object[3] (aliases {ROOT}:this.root.left)
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").set("height", 0);  // {V17} >= 0
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").set("element", -3);  // {V16} < {V11}
         // {V12} >= 1
         // {V1} <= {V16}
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").set("left", newInstance("avl_tree.AvlNode")); // {R18} == Object[11] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").get("left").set("parent", new AccessibleObject(__ROOT_this).get("root").get("left").get("left").getValue()); // {R24} == Object[9] (aliases {ROOT}:this.root.left.left)
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").get("left").set("height", 0);  // {V22} >= 0
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").get("left").set("element", 0);  // {V21} >= {V16}
        if (__ROOT_this.repOK()) {System.out.println("true"); __ROOT_this.minElement(__ROOT_minE);}
    }
//.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1.2 trace exhausted depth scope.
//.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1//.1//.1.2//.1.2 trace violates an assumption.
//.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1//.1//.1.2.2//.1//.1 {ROOT}:this.root.left.left.right.parent not expanded. It may be a hint of too strong user-defined constraints, possibly correct when enforcing redundancy by representation invariant.
    @Test
    public void test5() {
        //test case for state //.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1//.1//.1.2.2//.1//.1[5]
        this.nullObjectFields = new HashSet<>();
        avl_tree.AvlTree __ROOT_this = (avl_tree.AvlTree) newInstance("avl_tree.AvlTree"); // {R0} == Object[0] (fresh)
        ; // pre_init(avl_tree/AvlTree)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("root", newInstance("avl_tree.AvlNode")); // {R1} == Object[1] (fresh)
        ; // pre_init(avl_tree/AvlNode)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R2} == null
        new AccessibleObject(__ROOT_this).get("root").set("height", 1);  // {V4} >= 0
        ; // pre_init(jbse/meta/Analysis)
        ; // pre_init(avl_tree/Range)
        int __ROOT_minE = -3;new AccessibleObject(__ROOT_this).get("root").set("element", 0);   // {V1} <= {V3}
        new AccessibleObject(__ROOT_this).get("root").set("left", newInstance("avl_tree.AvlNode")); // {R3} == Object[3] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("parent", new AccessibleObject(__ROOT_this).get("root").getValue()); // {R10} == Object[1] (aliases {ROOT}:this.root)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("height", 1);  // {V12} >= 0
        new AccessibleObject(__ROOT_this).get("root").get("left").set("element", -2);  // {V11} < {V3}
        ; // !pre_init(java/lang/String)
        ; // !pre_init(java/lang/String$CaseInsensitiveComparator)
         // {V4} >= 1
         // {V1} <= {V11}
        new AccessibleObject(__ROOT_this).get("root").get("left").set("left", newInstance("avl_tree.AvlNode")); // {R11} == Object[9] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").set("parent", new AccessibleObject(__ROOT_this).get("root").get("left").getValue()); // {R17} == Object[3] (aliases {ROOT}:this.root.left)
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").set("height", 1);  // {V17} >= 0
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").set("element", -3);  // {V16} < {V11}
         // {V12} >= 1
         // {V1} <= {V16}
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").set("left", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").get("left").get("left").getValue(), "left")); // {R18} == null
         // {V17} <= 1
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").set("right", newInstance("avl_tree.AvlNode")); // {R19} == Object[11] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").get("right").set("parent", new AccessibleObject(__ROOT_this).get("root").get("left").get("left").getValue()); // {R24} == Object[9] (aliases {ROOT}:this.root.left.left)
        if (__ROOT_this.repOK()) {System.out.println("true"); __ROOT_this.minElement(__ROOT_minE);}
    }
//.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1//.1//.1.2.2//.1//.1 trace exhausted depth scope.
    @Test
    public void test6() {
        //test case for state //.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1//.1//.1.2.2//.1.2//.1[0]
        this.nullObjectFields = new HashSet<>();
        avl_tree.AvlTree __ROOT_this = (avl_tree.AvlTree) newInstance("avl_tree.AvlTree"); // {R0} == Object[0] (fresh)
        ; // pre_init(avl_tree/AvlTree)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("root", newInstance("avl_tree.AvlNode")); // {R1} == Object[1] (fresh)
        ; // pre_init(avl_tree/AvlNode)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R2} == null
        new AccessibleObject(__ROOT_this).get("root").set("height", 1);  // {V4} >= 0
        ; // pre_init(jbse/meta/Analysis)
        ; // pre_init(avl_tree/Range)
        int __ROOT_minE = -3;new AccessibleObject(__ROOT_this).get("root").set("element", 0);   // {V1} <= {V3}
        new AccessibleObject(__ROOT_this).get("root").set("left", newInstance("avl_tree.AvlNode")); // {R3} == Object[3] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("parent", new AccessibleObject(__ROOT_this).get("root").getValue()); // {R10} == Object[1] (aliases {ROOT}:this.root)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("height", 1);  // {V12} >= 0
        new AccessibleObject(__ROOT_this).get("root").get("left").set("element", -2);  // {V11} < {V3}
        ; // !pre_init(java/lang/String)
        ; // !pre_init(java/lang/String$CaseInsensitiveComparator)
         // {V4} >= 1
         // {V1} <= {V11}
        new AccessibleObject(__ROOT_this).get("root").get("left").set("left", newInstance("avl_tree.AvlNode")); // {R11} == Object[9] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").set("parent", new AccessibleObject(__ROOT_this).get("root").get("left").getValue()); // {R17} == Object[3] (aliases {ROOT}:this.root.left)
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").set("height", 0);  // {V17} >= 0
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").set("element", -3);  // {V16} < {V11}
         // {V12} >= 1
         // {V1} <= {V16}
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").set("left", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").get("left").get("left").getValue(), "left")); // {R18} == null
         // {V17} <= 1
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").set("right", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").get("left").get("left").getValue(), "right")); // {R19} == null
         // {V17} == 0
        if (__ROOT_this.repOK()) {System.out.println("true"); __ROOT_this.minElement(__ROOT_minE);}
    }
//.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1//.1//.1.2.2//.1.2//.1 trace exhausted depth scope.
    @Test
    public void test7() {
        //test case for state //.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1//.1//.1.2.2//.1.2.2[1]
        this.nullObjectFields = new HashSet<>();
        avl_tree.AvlTree __ROOT_this = (avl_tree.AvlTree) newInstance("avl_tree.AvlTree"); // {R0} == Object[0] (fresh)
        ; // pre_init(avl_tree/AvlTree)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("root", newInstance("avl_tree.AvlNode")); // {R1} == Object[1] (fresh)
        ; // pre_init(avl_tree/AvlNode)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R2} == null
        new AccessibleObject(__ROOT_this).get("root").set("height", 1);  // {V4} >= 0
        ; // pre_init(jbse/meta/Analysis)
        ; // pre_init(avl_tree/Range)
        int __ROOT_minE = -3;new AccessibleObject(__ROOT_this).get("root").set("element", 0);   // {V1} <= {V3}
        new AccessibleObject(__ROOT_this).get("root").set("left", newInstance("avl_tree.AvlNode")); // {R3} == Object[3] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("parent", new AccessibleObject(__ROOT_this).get("root").getValue()); // {R10} == Object[1] (aliases {ROOT}:this.root)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("height", 1);  // {V12} >= 0
        new AccessibleObject(__ROOT_this).get("root").get("left").set("element", -2);  // {V11} < {V3}
        ; // !pre_init(java/lang/String)
        ; // !pre_init(java/lang/String$CaseInsensitiveComparator)
         // {V4} >= 1
         // {V1} <= {V11}
        new AccessibleObject(__ROOT_this).get("root").get("left").set("left", newInstance("avl_tree.AvlNode")); // {R11} == Object[9] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").set("parent", new AccessibleObject(__ROOT_this).get("root").get("left").getValue()); // {R17} == Object[3] (aliases {ROOT}:this.root.left)
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").set("height", 1);  // {V17} >= 0
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").set("element", -3);  // {V16} < {V11}
         // {V12} >= 1
         // {V1} <= {V16}
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").set("left", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").get("left").get("left").getValue(), "left")); // {R18} == null
         // {V17} <= 1
        new AccessibleObject(__ROOT_this).get("root").get("left").get("left").set("right", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").get("left").get("left").getValue(), "right")); // {R19} == null
         // {V17} != 0
        if (__ROOT_this.repOK()) {System.out.println("true"); __ROOT_this.minElement(__ROOT_minE);}
    }
//.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1//.1//.1.2.2//.1.2.2 trace exhausted depth scope.
//.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1//.1//.1.2.2.2 trace violates an assumption.
//.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1//.1.2 trace violates an assumption.
//.1//.1//.1.2//.1//.1//.1//.1.2//.1//.1.2 trace violates an assumption.
//.1//.1//.1.2//.1//.1//.1//.1.2//.1.2 trace violates an assumption.
//.1//.1//.1.2//.1//.1//.1//.1.2.2//.1//.1 {ROOT}:this.root.left.right.parent not expanded. It may be a hint of too strong user-defined constraints, possibly correct when enforcing redundancy by representation invariant.
    @Test
    public void test8() {
        //test case for state //.1//.1//.1.2//.1//.1//.1//.1.2.2//.1//.1//.1//.1//.1//.1//.1[16]
        this.nullObjectFields = new HashSet<>();
        avl_tree.AvlTree __ROOT_this = (avl_tree.AvlTree) newInstance("avl_tree.AvlTree"); // {R0} == Object[0] (fresh)
        ; // pre_init(avl_tree/AvlTree)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("root", newInstance("avl_tree.AvlNode")); // {R1} == Object[1] (fresh)
        ; // pre_init(avl_tree/AvlNode)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R2} == null
        new AccessibleObject(__ROOT_this).get("root").set("height", 1);  // {V4} >= 0
        ; // pre_init(jbse/meta/Analysis)
        ; // pre_init(avl_tree/Range)
        int __ROOT_minE = -3;new AccessibleObject(__ROOT_this).get("root").set("element", 0);   // {V1} <= {V3}
        new AccessibleObject(__ROOT_this).get("root").set("left", newInstance("avl_tree.AvlNode")); // {R3} == Object[3] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("parent", new AccessibleObject(__ROOT_this).get("root").getValue()); // {R10} == Object[1] (aliases {ROOT}:this.root)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("height", 1);  // {V12} >= 0
        new AccessibleObject(__ROOT_this).get("root").get("left").set("element", -2);  // {V11} < {V3}
        ; // !pre_init(java/lang/String)
        ; // !pre_init(java/lang/String$CaseInsensitiveComparator)
         // {V4} >= 1
         // {V1} <= {V11}
        new AccessibleObject(__ROOT_this).get("root").get("left").set("left", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").get("left").getValue(), "left")); // {R11} == null
         // {V12} <= 1
        new AccessibleObject(__ROOT_this).get("root").get("left").set("right", newInstance("avl_tree.AvlNode")); // {R12} == Object[9] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("left").get("right").set("parent", new AccessibleObject(__ROOT_this).get("root").get("left").getValue()); // {R17} == Object[3] (aliases {ROOT}:this.root.left)
        new AccessibleObject(__ROOT_this).get("root").get("left").get("right").set("height", 0);  // {V17} >= 0
        new AccessibleObject(__ROOT_this).get("root").get("left").get("right").set("element", -1);  // {V16} < {V3}
         // {V16} > {V11}
         // {V12} == 1
         // {V17} == 0
        if (__ROOT_this.repOK()) {System.out.println("true"); __ROOT_this.minElement(__ROOT_minE);}
    }
//.1//.1//.1.2//.1//.1//.1//.1.2.2//.1//.1//.1//.1//.1//.1//.1 trace exhausted depth scope.
//.1//.1//.1.2//.1//.1//.1//.1.2.2//.1//.1//.1//.1//.1//.1.2 trace violates an assumption.
//.1//.1//.1.2//.1//.1//.1//.1.2.2//.1//.1//.1//.1//.1.2 trace violates an assumption.
//.1//.1//.1.2//.1//.1//.1//.1.2.2//.1//.1//.1//.1.2 trace violates an assumption.
//.1//.1//.1.2//.1//.1//.1//.1.2.2//.1//.1//.1.2 trace violates an assumption.
//.1//.1//.1.2//.1//.1//.1//.1.2.2//.1//.1.2 trace violates an assumption.
//.1//.1//.1.2//.1//.1//.1//.1.2.2//.1.2//.1//.1 {ROOT}:this.root.right.parent not expanded. It may be a hint of too strong user-defined constraints, possibly correct when enforcing redundancy by representation invariant.
    @Test
    public void test9() {
        //test case for state //.1//.1//.1.2//.1//.1//.1//.1.2.2//.1.2//.1//.1//.1//.1//.1[13]
        this.nullObjectFields = new HashSet<>();
        avl_tree.AvlTree __ROOT_this = (avl_tree.AvlTree) newInstance("avl_tree.AvlTree"); // {R0} == Object[0] (fresh)
        ; // pre_init(avl_tree/AvlTree)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("root", newInstance("avl_tree.AvlNode")); // {R1} == Object[1] (fresh)
        ; // pre_init(avl_tree/AvlNode)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R2} == null
        new AccessibleObject(__ROOT_this).get("root").set("height", 2);  // {V4} >= 0
        ; // pre_init(jbse/meta/Analysis)
        ; // pre_init(avl_tree/Range)
        int __ROOT_minE = -3;new AccessibleObject(__ROOT_this).get("root").set("element", 0);   // {V1} <= {V3}
        new AccessibleObject(__ROOT_this).get("root").set("left", newInstance("avl_tree.AvlNode")); // {R3} == Object[3] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("parent", new AccessibleObject(__ROOT_this).get("root").getValue()); // {R10} == Object[1] (aliases {ROOT}:this.root)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("height", 0);  // {V12} >= 0
        new AccessibleObject(__ROOT_this).get("root").get("left").set("element", -2);  // {V11} < {V3}
        ; // !pre_init(java/lang/String)
        ; // !pre_init(java/lang/String$CaseInsensitiveComparator)
         // {V4} >= 1
         // {V1} <= {V11}
        new AccessibleObject(__ROOT_this).get("root").get("left").set("left", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").get("left").getValue(), "left")); // {R11} == null
         // {V12} <= 1
        new AccessibleObject(__ROOT_this).get("root").get("left").set("right", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").get("left").getValue(), "right")); // {R12} == null
         // {V12} == 0
        new AccessibleObject(__ROOT_this).get("root").set("right", newInstance("avl_tree.AvlNode")); // {R4} == Object[11] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("right").set("parent", new AccessibleObject(__ROOT_this).get("root").getValue()); // {R17} == Object[1] (aliases {ROOT}:this.root)
        new AccessibleObject(__ROOT_this).get("root").get("right").set("height", 1);  // {V17} >= 0
        new AccessibleObject(__ROOT_this).get("root").get("right").set("element", 1);  // {V16} > {V3}
         // {V4} >= {V17} + 1
        if (__ROOT_this.repOK()) {System.out.println("true"); __ROOT_this.minElement(__ROOT_minE);}
    }
//.1//.1//.1.2//.1//.1//.1//.1.2.2//.1.2//.1//.1//.1//.1//.1 trace exhausted depth scope.
//.1//.1//.1.2//.1//.1//.1//.1.2.2//.1.2//.1//.1//.1//.1.2 trace violates an assumption.
//.1//.1//.1.2//.1//.1//.1//.1.2.2//.1.2//.1//.1//.1.2 trace violates an assumption.
//.1//.1//.1.2//.1//.1//.1//.1.2.2//.1.2//.1//.1.2 trace violates an assumption.
    @Test
    public void test10() {
        //test case for state //.1//.1//.1.2//.1//.1//.1//.1.2.2//.1.2//.1.2//.1[26]
        this.nullObjectFields = new HashSet<>();
        avl_tree.AvlTree __ROOT_this = (avl_tree.AvlTree) newInstance("avl_tree.AvlTree"); // {R0} == Object[0] (fresh)
        ; // pre_init(avl_tree/AvlTree)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("root", newInstance("avl_tree.AvlNode")); // {R1} == Object[1] (fresh)
        ; // pre_init(avl_tree/AvlNode)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R2} == null
        new AccessibleObject(__ROOT_this).get("root").set("height", 1);  // {V4} >= 0
        ; // pre_init(jbse/meta/Analysis)
        ; // pre_init(avl_tree/Range)
        int __ROOT_minE = -3;new AccessibleObject(__ROOT_this).get("root").set("element", 0);   // {V1} <= {V3}
        new AccessibleObject(__ROOT_this).get("root").set("left", newInstance("avl_tree.AvlNode")); // {R3} == Object[3] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("parent", new AccessibleObject(__ROOT_this).get("root").getValue()); // {R10} == Object[1] (aliases {ROOT}:this.root)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("height", 0);  // {V12} >= 0
        new AccessibleObject(__ROOT_this).get("root").get("left").set("element", -2);  // {V11} < {V3}
        ; // !pre_init(java/lang/String)
        ; // !pre_init(java/lang/String$CaseInsensitiveComparator)
         // {V4} >= 1
         // {V1} <= {V11}
        new AccessibleObject(__ROOT_this).get("root").get("left").set("left", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").get("left").getValue(), "left")); // {R11} == null
         // {V12} <= 1
        new AccessibleObject(__ROOT_this).get("root").get("left").set("right", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").get("left").getValue(), "right")); // {R12} == null
         // {V12} == 0
        new AccessibleObject(__ROOT_this).get("root").set("right", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "right")); // {R4} == null
         // {V4} == 1
        if (__ROOT_this.repOK()) {System.out.println("true"); __ROOT_this.minElement(__ROOT_minE);}
        //assertTrue(__returnedValue == true);
    }
//.1//.1//.1.2//.1//.1//.1//.1.2.2//.1.2//.1.2//.1 trace is safe.
//.1//.1//.1.2//.1//.1//.1//.1.2.2//.1.2//.1.2.2 trace violates an assumption.
//.1//.1//.1.2//.1//.1//.1//.1.2.2//.1.2.2 trace violates an assumption.
//.1//.1//.1.2//.1//.1//.1//.1.2.2.2 trace violates an assumption.
//.1//.1//.1.2//.1//.1//.1.2 trace violates an assumption.
//.1//.1//.1.2//.1//.1.2 trace violates an assumption.
//.1//.1//.1.2//.1.2 trace violates an assumption.
//.1//.1//.1.2.2//.1//.1 {ROOT}:this.root.right.parent not expanded. It may be a hint of too strong user-defined constraints, possibly correct when enforcing redundancy by representation invariant.
//.1//.1//.1.2.2//.1//.1//.1//.1//.1//.1//.1 {ROOT}:this.root.right.left.parent not expanded. It may be a hint of too strong user-defined constraints, possibly correct when enforcing redundancy by representation invariant.
//.1//.1//.1.2.2//.1//.1//.1//.1//.1//.1//.1//.1//.1//.1 trace violates an assumption.
//.1//.1//.1.2.2//.1//.1//.1//.1//.1//.1//.1//.1//.1.2 trace violates an assumption.
//.1//.1//.1.2.2//.1//.1//.1//.1//.1//.1//.1//.1.2 trace violates an assumption.
//.1//.1//.1.2.2//.1//.1//.1//.1//.1//.1//.1.2 trace violates an assumption.
//.1//.1//.1.2.2//.1//.1//.1//.1//.1//.1.2//.1 {ROOT}:this.root.right.right.parent not expanded. It may be a hint of too strong user-defined constraints, possibly correct when enforcing redundancy by representation invariant.
    @Test
    public void test11() {
        //test case for state //.1//.1//.1.2.2//.1//.1//.1//.1//.1//.1.2//.1//.1//.1[26]
        this.nullObjectFields = new HashSet<>();
        avl_tree.AvlTree __ROOT_this = (avl_tree.AvlTree) newInstance("avl_tree.AvlTree"); // {R0} == Object[0] (fresh)
        ; // pre_init(avl_tree/AvlTree)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("root", newInstance("avl_tree.AvlNode")); // {R1} == Object[1] (fresh)
        ; // pre_init(avl_tree/AvlNode)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R2} == null
        new AccessibleObject(__ROOT_this).get("root").set("height", 1);  // {V4} >= 0
        ; // pre_init(jbse/meta/Analysis)
        ; // pre_init(avl_tree/Range)
        int __ROOT_minE = -3;new AccessibleObject(__ROOT_this).get("root").set("element", 0);   // {V1} <= {V3}
        new AccessibleObject(__ROOT_this).get("root").set("left", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "left")); // {R3} == null
        ; // !pre_init(java/lang/String)
        ; // !pre_init(java/lang/String$CaseInsensitiveComparator)
         // {V4} <= 1
        new AccessibleObject(__ROOT_this).get("root").set("right", newInstance("avl_tree.AvlNode")); // {R4} == Object[7] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("right").set("parent", new AccessibleObject(__ROOT_this).get("root").getValue()); // {R10} == Object[1] (aliases {ROOT}:this.root)
        new AccessibleObject(__ROOT_this).get("root").get("right").set("height", 0);  // {V12} >= 0
        new AccessibleObject(__ROOT_this).get("root").get("right").set("element", 2);  // {V11} > {V3}
         // {V4} == 1
         // {V12} == 0
        new AccessibleObject(__ROOT_this).get("root").get("right").set("left", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").get("right").getValue(), "left")); // {R11} == null
        new AccessibleObject(__ROOT_this).get("root").get("right").set("right", newInstance("avl_tree.AvlNode")); // {R12} == Object[11] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("right").get("right").set("parent", new AccessibleObject(__ROOT_this).get("root").get("right").getValue()); // {R17} == Object[7] (aliases {ROOT}:this.root.right)
        new AccessibleObject(__ROOT_this).get("root").get("right").get("right").set("height", 0);  // {V17} >= 0
        new AccessibleObject(__ROOT_this).get("root").get("right").get("right").set("element", 3);  // {V16} > {V11}
        if (__ROOT_this.repOK()) {System.out.println("true"); __ROOT_this.minElement(__ROOT_minE);}
    }
//.1//.1//.1.2.2//.1//.1//.1//.1//.1//.1.2//.1//.1//.1 trace exhausted depth scope.
//.1//.1//.1.2.2//.1//.1//.1//.1//.1//.1.2//.1//.1.2 trace violates an assumption.
//.1//.1//.1.2.2//.1//.1//.1//.1//.1//.1.2//.1.2 trace violates an assumption.
    @Test
    public void test12() {
        //test case for state //.1//.1//.1.2.2//.1//.1//.1//.1//.1//.1.2.2[36]
        this.nullObjectFields = new HashSet<>();
        avl_tree.AvlTree __ROOT_this = (avl_tree.AvlTree) newInstance("avl_tree.AvlTree"); // {R0} == Object[0] (fresh)
        ; // pre_init(avl_tree/AvlTree)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("root", newInstance("avl_tree.AvlNode")); // {R1} == Object[1] (fresh)
        ; // pre_init(avl_tree/AvlNode)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R2} == null
        new AccessibleObject(__ROOT_this).get("root").set("height", 1);  // {V4} >= 0
        ; // pre_init(jbse/meta/Analysis)
        ; // pre_init(avl_tree/Range)
        int __ROOT_minE = -3;new AccessibleObject(__ROOT_this).get("root").set("element", 0);   // {V1} <= {V3}
        new AccessibleObject(__ROOT_this).get("root").set("left", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "left")); // {R3} == null
        ; // !pre_init(java/lang/String)
        ; // !pre_init(java/lang/String$CaseInsensitiveComparator)
         // {V4} <= 1
        new AccessibleObject(__ROOT_this).get("root").set("right", newInstance("avl_tree.AvlNode")); // {R4} == Object[7] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("right").set("parent", new AccessibleObject(__ROOT_this).get("root").getValue()); // {R10} == Object[1] (aliases {ROOT}:this.root)
        new AccessibleObject(__ROOT_this).get("root").get("right").set("height", 0);  // {V12} >= 0
        new AccessibleObject(__ROOT_this).get("root").get("right").set("element", 2);  // {V11} > {V3}
         // {V4} == 1
         // {V12} == 0
        new AccessibleObject(__ROOT_this).get("root").get("right").set("left", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").get("right").getValue(), "left")); // {R11} == null
        new AccessibleObject(__ROOT_this).get("root").get("right").set("right", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").get("right").getValue(), "right")); // {R12} == null
        if (__ROOT_this.repOK()) {System.out.println("true"); __ROOT_this.minElement(__ROOT_minE);}
        //assertTrue(__returnedValue == true);
    }
//.1//.1//.1.2.2//.1//.1//.1//.1//.1//.1.2.2 trace is safe.
//.1//.1//.1.2.2//.1//.1//.1//.1//.1.2 trace violates an assumption.
//.1//.1//.1.2.2//.1//.1//.1//.1.2 trace violates an assumption.
//.1//.1//.1.2.2//.1//.1//.1.2 trace violates an assumption.
//.1//.1//.1.2.2//.1//.1.2 trace violates an assumption.
    @Test
    public void test13() {
        //test case for state //.1//.1//.1.2.2//.1.2//.1[18]
        this.nullObjectFields = new HashSet<>();
        avl_tree.AvlTree __ROOT_this = (avl_tree.AvlTree) newInstance("avl_tree.AvlTree"); // {R0} == Object[0] (fresh)
        ; // pre_init(avl_tree/AvlTree)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("root", newInstance("avl_tree.AvlNode")); // {R1} == Object[1] (fresh)
        ; // pre_init(avl_tree/AvlNode)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R2} == null
        new AccessibleObject(__ROOT_this).get("root").set("height", 0);  // {V4} >= 0
        ; // pre_init(jbse/meta/Analysis)
        ; // pre_init(avl_tree/Range)
        int __ROOT_minE = -3;new AccessibleObject(__ROOT_this).get("root").set("element", 0);   // {V1} <= {V3}
        new AccessibleObject(__ROOT_this).get("root").set("left", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "left")); // {R3} == null
        ; // !pre_init(java/lang/String)
        ; // !pre_init(java/lang/String$CaseInsensitiveComparator)
         // {V4} <= 1
        new AccessibleObject(__ROOT_this).get("root").set("right", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "right")); // {R4} == null
         // {V4} == 0
        if (__ROOT_this.repOK()) {System.out.println("true"); __ROOT_this.minElement(__ROOT_minE);}
        //assertTrue(__returnedValue == true);
    }
//.1//.1//.1.2.2//.1.2//.1 trace is safe.
//.1//.1//.1.2.2//.1.2.2 trace violates an assumption.
//.1//.1//.1.2.2.2 trace violates an assumption.
//.1//.1.2 trace violates an assumption.
    @Test
    public void test14() {
        //test case for state //.1.2[6]
        this.nullObjectFields = new HashSet<>();
        avl_tree.AvlTree __ROOT_this = (avl_tree.AvlTree) newInstance("avl_tree.AvlTree"); // {R0} == Object[0] (fresh)
        ; // pre_init(avl_tree/AvlTree)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("root", null);this.nullObjectFields.add(new ObjectField(__ROOT_this, "root")); // {R1} == null
        if (__ROOT_this.repOK()) {System.out.println("true"); __ROOT_this.minElement(0);}
        //assertTrue(__returnedValue == true);
    }
//.1.2 trace is safe.
}

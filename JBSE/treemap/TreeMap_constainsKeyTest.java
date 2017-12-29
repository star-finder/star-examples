package treemap;

import static java.lang.System.identityHashCode;
import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.HashSet;
import sun.misc.Unsafe;

import org.junit.Test;

public class TreeMap_constainsKeyTest {
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

    @Test
    public void test0() {
        //test case for state .1.1.1.1.1.2.1.1[5]
        this.nullObjectFields = new HashSet<>();
        treemap.TreeMap __ROOT_this = (treemap.TreeMap) newInstance("treemap.TreeMap"); // {R0} == Object[0] (fresh)
        ; // pre_init(treemap/TreeMap)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 1);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        new AccessibleObject(__ROOT_this).set("_blackHeight", 1);  // {V4} >= 0
        new AccessibleObject(__ROOT_this).set("root", newInstance("treemap.TreeMap$Entry")); // {R1} == Object[1] (fresh)
        ; // pre_init(treemap/TreeMap$Entry$HEXTriggers)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R6} == null
        new AccessibleObject(__ROOT_this).get("root").set("_owner", __ROOT_this); // {R10} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 1
        new AccessibleObject(__ROOT_this).get("root").set("color", (2 != 0));  // WIDEN-I({V13}) != 0
         // {V4} >= 1
        int __ROOT_key = 3;new AccessibleObject(__ROOT_this).get("root").set("key", 3);   // {V7} == {V12}
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.containsKey(__ROOT_key);
        }
//        boolean __returnedValue = __ROOT_this.containsKey(__ROOT_key);
//        assertTrue(__returnedValue == true);
    }
    @Test
    public void test1() {
        //test case for state .1.1.1.1.1.2.1.2.1.1.1.1.1.1[5]
        this.nullObjectFields = new HashSet<>();
        treemap.TreeMap __ROOT_this = (treemap.TreeMap) newInstance("treemap.TreeMap"); // {R0} == Object[0] (fresh)
        ; // pre_init(treemap/TreeMap)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 2);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        new AccessibleObject(__ROOT_this).set("_blackHeight", 1);  // {V4} >= 0
        new AccessibleObject(__ROOT_this).set("root", newInstance("treemap.TreeMap$Entry")); // {R1} == Object[1] (fresh)
        ; // pre_init(treemap/TreeMap$Entry$HEXTriggers)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R6} == null
        new AccessibleObject(__ROOT_this).get("root").set("_owner", __ROOT_this); // {R10} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 1
        new AccessibleObject(__ROOT_this).get("root").set("color", (3 != 0));  // WIDEN-I({V13}) != 0
         // {V4} >= 1
        int __ROOT_key = -1;new AccessibleObject(__ROOT_this).get("root").set("key", 0);   // {V7} != {V12}
         // {V7} < {V12}
        new AccessibleObject(__ROOT_this).get("root").set("left", newInstance("treemap.TreeMap$Entry")); // {R4} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("parent", new AccessibleObject(__ROOT_this).get("root").getValue()); // {R14} == Object[1] (aliases {ROOT}:this.root)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("_owner", __ROOT_this); // {R18} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 2
        new AccessibleObject(__ROOT_this).get("root").get("left").set("color", (0 != 0));  // WIDEN-I({V24}) == 0
        new AccessibleObject(__ROOT_this).get("root").get("left").set("key", -1);  // {V23} < {V12}
         // {V7} == {V23}
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.containsKey(__ROOT_key);
        }
//        boolean __returnedValue = __ROOT_this.containsKey(__ROOT_key);
//        assertTrue(__returnedValue == true);
    }
    @Test
    public void test2() {
        //test case for state .1.1.1.1.1.2.1.2.1.1.1.1.1.2.1[0]
        this.nullObjectFields = new HashSet<>();
        treemap.TreeMap __ROOT_this = (treemap.TreeMap) newInstance("treemap.TreeMap"); // {R0} == Object[0] (fresh)
        ; // pre_init(treemap/TreeMap)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 2);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        new AccessibleObject(__ROOT_this).set("_blackHeight", 1);  // {V4} >= 0
        new AccessibleObject(__ROOT_this).set("root", newInstance("treemap.TreeMap$Entry")); // {R1} == Object[1] (fresh)
        ; // pre_init(treemap/TreeMap$Entry$HEXTriggers)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R6} == null
        new AccessibleObject(__ROOT_this).get("root").set("_owner", __ROOT_this); // {R10} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 1
        new AccessibleObject(__ROOT_this).get("root").set("color", (3 != 0));  // WIDEN-I({V13}) != 0
         // {V4} >= 1
        int __ROOT_key = -2;new AccessibleObject(__ROOT_this).get("root").set("key", 0);   // {V7} != {V12}
         // {V7} < {V12}
        new AccessibleObject(__ROOT_this).get("root").set("left", newInstance("treemap.TreeMap$Entry")); // {R4} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("parent", new AccessibleObject(__ROOT_this).get("root").getValue()); // {R14} == Object[1] (aliases {ROOT}:this.root)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("_owner", __ROOT_this); // {R18} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 2
        new AccessibleObject(__ROOT_this).get("root").get("left").set("color", (0 != 0));  // WIDEN-I({V24}) == 0
        new AccessibleObject(__ROOT_this).get("root").get("left").set("key", -1);  // {V23} < {V12}
         // {V7} != {V23}
         // {V7} < {V23}
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.containsKey(__ROOT_key);
        }
//        __ROOT_this.containsKey(__ROOT_key);
    }
    @Test
    public void test3() {
        //test case for state .1.1.1.1.1.2.1.2.1.1.1.1.1.2.2[1]
        this.nullObjectFields = new HashSet<>();
        treemap.TreeMap __ROOT_this = (treemap.TreeMap) newInstance("treemap.TreeMap"); // {R0} == Object[0] (fresh)
        ; // pre_init(treemap/TreeMap)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 2);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        new AccessibleObject(__ROOT_this).set("_blackHeight", 1);  // {V4} >= 0
        new AccessibleObject(__ROOT_this).set("root", newInstance("treemap.TreeMap$Entry")); // {R1} == Object[1] (fresh)
        ; // pre_init(treemap/TreeMap$Entry$HEXTriggers)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R6} == null
        new AccessibleObject(__ROOT_this).get("root").set("_owner", __ROOT_this); // {R10} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 1
        new AccessibleObject(__ROOT_this).get("root").set("color", (3 != 0));  // WIDEN-I({V13}) != 0
         // {V4} >= 1
        int __ROOT_key = -1;new AccessibleObject(__ROOT_this).get("root").set("key", 0);   // {V7} != {V12}
         // {V7} < {V12}
        new AccessibleObject(__ROOT_this).get("root").set("left", newInstance("treemap.TreeMap$Entry")); // {R4} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("parent", new AccessibleObject(__ROOT_this).get("root").getValue()); // {R14} == Object[1] (aliases {ROOT}:this.root)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("_owner", __ROOT_this); // {R18} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 2
        new AccessibleObject(__ROOT_this).get("root").get("left").set("color", (0 != 0));  // WIDEN-I({V24}) == 0
        new AccessibleObject(__ROOT_this).get("root").get("left").set("key", -2);  // {V23} < {V12}
         // {V7} != {V23}
         // {V7} >= {V23}
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.containsKey(__ROOT_key);
        }
//        __ROOT_this.containsKey(__ROOT_key);
    }
    @Test
    public void test4() {
        //test case for state .1.1.1.1.1.2.1.2.1.1.1.2.1.1.1[5]
        this.nullObjectFields = new HashSet<>();
        treemap.TreeMap __ROOT_this = (treemap.TreeMap) newInstance("treemap.TreeMap"); // {R0} == Object[0] (fresh)
        ; // pre_init(treemap/TreeMap)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 2);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        new AccessibleObject(__ROOT_this).set("_blackHeight", 2);  // {V4} >= 0
        new AccessibleObject(__ROOT_this).set("root", newInstance("treemap.TreeMap$Entry")); // {R1} == Object[1] (fresh)
        ; // pre_init(treemap/TreeMap$Entry$HEXTriggers)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R6} == null
        new AccessibleObject(__ROOT_this).get("root").set("_owner", __ROOT_this); // {R10} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 1
        new AccessibleObject(__ROOT_this).get("root").set("color", (3 != 0));  // WIDEN-I({V13}) != 0
         // {V4} >= 1
        int __ROOT_key = -1;new AccessibleObject(__ROOT_this).get("root").set("key", 0);   // {V7} != {V12}
         // {V7} < {V12}
        new AccessibleObject(__ROOT_this).get("root").set("left", newInstance("treemap.TreeMap$Entry")); // {R4} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("parent", new AccessibleObject(__ROOT_this).get("root").getValue()); // {R14} == Object[1] (aliases {ROOT}:this.root)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("_owner", __ROOT_this); // {R18} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 2
        new AccessibleObject(__ROOT_this).get("root").get("left").set("color", (5 != 0));  // WIDEN-I({V24}) != 0
         // {V4} >= 2
        new AccessibleObject(__ROOT_this).get("root").get("left").set("key", -1);  // {V23} < {V12}
         // {V7} == {V23}
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.containsKey(__ROOT_key);
        }
//        boolean __returnedValue = __ROOT_this.containsKey(__ROOT_key);
//        assertTrue(__returnedValue == true);
    }
    @Test
    public void test5() {
        //test case for state .1.1.1.1.1.2.1.2.1.1.1.2.1.1.2.1[0]
        this.nullObjectFields = new HashSet<>();
        treemap.TreeMap __ROOT_this = (treemap.TreeMap) newInstance("treemap.TreeMap"); // {R0} == Object[0] (fresh)
        ; // pre_init(treemap/TreeMap)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 2);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        new AccessibleObject(__ROOT_this).set("_blackHeight", 2);  // {V4} >= 0
        new AccessibleObject(__ROOT_this).set("root", newInstance("treemap.TreeMap$Entry")); // {R1} == Object[1] (fresh)
        ; // pre_init(treemap/TreeMap$Entry$HEXTriggers)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R6} == null
        new AccessibleObject(__ROOT_this).get("root").set("_owner", __ROOT_this); // {R10} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 1
        new AccessibleObject(__ROOT_this).get("root").set("color", (3 != 0));  // WIDEN-I({V13}) != 0
         // {V4} >= 1
        int __ROOT_key = -2;new AccessibleObject(__ROOT_this).get("root").set("key", 0);   // {V7} != {V12}
         // {V7} < {V12}
        new AccessibleObject(__ROOT_this).get("root").set("left", newInstance("treemap.TreeMap$Entry")); // {R4} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("parent", new AccessibleObject(__ROOT_this).get("root").getValue()); // {R14} == Object[1] (aliases {ROOT}:this.root)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("_owner", __ROOT_this); // {R18} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 2
        new AccessibleObject(__ROOT_this).get("root").get("left").set("color", (5 != 0));  // WIDEN-I({V24}) != 0
         // {V4} >= 2
        new AccessibleObject(__ROOT_this).get("root").get("left").set("key", -1);  // {V23} < {V12}
         // {V7} != {V23}
         // {V7} < {V23}
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.containsKey(__ROOT_key);
        }
//        __ROOT_this.containsKey(__ROOT_key);
    }
    @Test
    public void test6() {
        //test case for state .1.1.1.1.1.2.1.2.1.1.1.2.1.1.2.2[1]
        this.nullObjectFields = new HashSet<>();
        treemap.TreeMap __ROOT_this = (treemap.TreeMap) newInstance("treemap.TreeMap"); // {R0} == Object[0] (fresh)
        ; // pre_init(treemap/TreeMap)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 2);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        new AccessibleObject(__ROOT_this).set("_blackHeight", 2);  // {V4} >= 0
        new AccessibleObject(__ROOT_this).set("root", newInstance("treemap.TreeMap$Entry")); // {R1} == Object[1] (fresh)
        ; // pre_init(treemap/TreeMap$Entry$HEXTriggers)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R6} == null
        new AccessibleObject(__ROOT_this).get("root").set("_owner", __ROOT_this); // {R10} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 1
        new AccessibleObject(__ROOT_this).get("root").set("color", (3 != 0));  // WIDEN-I({V13}) != 0
         // {V4} >= 1
        int __ROOT_key = -1;new AccessibleObject(__ROOT_this).get("root").set("key", 0);   // {V7} != {V12}
         // {V7} < {V12}
        new AccessibleObject(__ROOT_this).get("root").set("left", newInstance("treemap.TreeMap$Entry")); // {R4} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("parent", new AccessibleObject(__ROOT_this).get("root").getValue()); // {R14} == Object[1] (aliases {ROOT}:this.root)
        new AccessibleObject(__ROOT_this).get("root").get("left").set("_owner", __ROOT_this); // {R18} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 2
        new AccessibleObject(__ROOT_this).get("root").get("left").set("color", (5 != 0));  // WIDEN-I({V24}) != 0
         // {V4} >= 2
        new AccessibleObject(__ROOT_this).get("root").get("left").set("key", -2);  // {V23} < {V12}
         // {V7} != {V23}
         // {V7} >= {V23}
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.containsKey(__ROOT_key);
        }
//        __ROOT_this.containsKey(__ROOT_key);
    }
    @Test
    public void test7() {
        //test case for state .1.1.1.1.1.2.1.2.1.2.1[16]
        this.nullObjectFields = new HashSet<>();
        treemap.TreeMap __ROOT_this = (treemap.TreeMap) newInstance("treemap.TreeMap"); // {R0} == Object[0] (fresh)
        ; // pre_init(treemap/TreeMap)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 2);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        new AccessibleObject(__ROOT_this).set("_blackHeight", 1);  // {V4} >= 0
        new AccessibleObject(__ROOT_this).set("root", newInstance("treemap.TreeMap$Entry")); // {R1} == Object[1] (fresh)
        ; // pre_init(treemap/TreeMap$Entry$HEXTriggers)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R6} == null
        new AccessibleObject(__ROOT_this).get("root").set("_owner", __ROOT_this); // {R10} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 1
        new AccessibleObject(__ROOT_this).get("root").set("color", (3 != 0));  // WIDEN-I({V13}) != 0
         // {V4} >= 1
        int __ROOT_key = -1;new AccessibleObject(__ROOT_this).get("root").set("key", 0);   // {V7} != {V12}
         // {V7} < {V12}
        new AccessibleObject(__ROOT_this).get("root").set("left", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "left")); // {R4} == null
         // {V4} == 1
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.containsKey(__ROOT_key);
        }
//        boolean __returnedValue = __ROOT_this.containsKey(__ROOT_key);
//        assertTrue(__returnedValue == false);
    }
    @Test
    public void test8() {
        //test case for state .1.1.1.1.1.2.1.2.2.1.1.1.1.1[5]
        this.nullObjectFields = new HashSet<>();
        treemap.TreeMap __ROOT_this = (treemap.TreeMap) newInstance("treemap.TreeMap"); // {R0} == Object[0] (fresh)
        ; // pre_init(treemap/TreeMap)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 2);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        new AccessibleObject(__ROOT_this).set("_blackHeight", 1);  // {V4} >= 0
        new AccessibleObject(__ROOT_this).set("root", newInstance("treemap.TreeMap$Entry")); // {R1} == Object[1] (fresh)
        ; // pre_init(treemap/TreeMap$Entry$HEXTriggers)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R6} == null
        new AccessibleObject(__ROOT_this).get("root").set("_owner", __ROOT_this); // {R10} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 1
        new AccessibleObject(__ROOT_this).get("root").set("color", (3 != 0));  // WIDEN-I({V13}) != 0
         // {V4} >= 1
        int __ROOT_key = 0;new AccessibleObject(__ROOT_this).get("root").set("key", -1);   // {V7} != {V12}
         // {V7} >= {V12}
        new AccessibleObject(__ROOT_this).get("root").set("right", newInstance("treemap.TreeMap$Entry")); // {R5} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("right").set("parent", new AccessibleObject(__ROOT_this).get("root").getValue()); // {R14} == Object[1] (aliases {ROOT}:this.root)
        new AccessibleObject(__ROOT_this).get("root").get("right").set("_owner", __ROOT_this); // {R18} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 2
        new AccessibleObject(__ROOT_this).get("root").get("right").set("color", (0 != 0));  // WIDEN-I({V24}) == 0
        new AccessibleObject(__ROOT_this).get("root").get("right").set("key", 0);  // {V23} > {V12}
         // {V7} == {V23}
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.containsKey(__ROOT_key);
        }
//        boolean __returnedValue = __ROOT_this.containsKey(__ROOT_key);
//        assertTrue(__returnedValue == true);
    }
    @Test
    public void test9() {
        //test case for state .1.1.1.1.1.2.1.2.2.1.1.1.1.2.1[0]
        this.nullObjectFields = new HashSet<>();
        treemap.TreeMap __ROOT_this = (treemap.TreeMap) newInstance("treemap.TreeMap"); // {R0} == Object[0] (fresh)
        ; // pre_init(treemap/TreeMap)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 2);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        new AccessibleObject(__ROOT_this).set("_blackHeight", 1);  // {V4} >= 0
        new AccessibleObject(__ROOT_this).set("root", newInstance("treemap.TreeMap$Entry")); // {R1} == Object[1] (fresh)
        ; // pre_init(treemap/TreeMap$Entry$HEXTriggers)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R6} == null
        new AccessibleObject(__ROOT_this).get("root").set("_owner", __ROOT_this); // {R10} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 1
        new AccessibleObject(__ROOT_this).get("root").set("color", (3 != 0));  // WIDEN-I({V13}) != 0
         // {V4} >= 1
        int __ROOT_key = 0;new AccessibleObject(__ROOT_this).get("root").set("key", -1);   // {V7} != {V12}
         // {V7} >= {V12}
        new AccessibleObject(__ROOT_this).get("root").set("right", newInstance("treemap.TreeMap$Entry")); // {R5} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("right").set("parent", new AccessibleObject(__ROOT_this).get("root").getValue()); // {R14} == Object[1] (aliases {ROOT}:this.root)
        new AccessibleObject(__ROOT_this).get("root").get("right").set("_owner", __ROOT_this); // {R18} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 2
        new AccessibleObject(__ROOT_this).get("root").get("right").set("color", (0 != 0));  // WIDEN-I({V24}) == 0
        new AccessibleObject(__ROOT_this).get("root").get("right").set("key", 1);  // {V23} > {V12}
         // {V7} != {V23}
         // {V7} < {V23}
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.containsKey(__ROOT_key);
        }
//        __ROOT_this.containsKey(__ROOT_key);
    }
    @Test
    public void test10() {
        //test case for state .1.1.1.1.1.2.1.2.2.1.1.1.1.2.2[1]
        this.nullObjectFields = new HashSet<>();
        treemap.TreeMap __ROOT_this = (treemap.TreeMap) newInstance("treemap.TreeMap"); // {R0} == Object[0] (fresh)
        ; // pre_init(treemap/TreeMap)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 2);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        new AccessibleObject(__ROOT_this).set("_blackHeight", 1);  // {V4} >= 0
        new AccessibleObject(__ROOT_this).set("root", newInstance("treemap.TreeMap$Entry")); // {R1} == Object[1] (fresh)
        ; // pre_init(treemap/TreeMap$Entry$HEXTriggers)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R6} == null
        new AccessibleObject(__ROOT_this).get("root").set("_owner", __ROOT_this); // {R10} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 1
        new AccessibleObject(__ROOT_this).get("root").set("color", (3 != 0));  // WIDEN-I({V13}) != 0
         // {V4} >= 1
        int __ROOT_key = 0;new AccessibleObject(__ROOT_this).get("root").set("key", -2);   // {V7} != {V12}
         // {V7} >= {V12}
        new AccessibleObject(__ROOT_this).get("root").set("right", newInstance("treemap.TreeMap$Entry")); // {R5} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("right").set("parent", new AccessibleObject(__ROOT_this).get("root").getValue()); // {R14} == Object[1] (aliases {ROOT}:this.root)
        new AccessibleObject(__ROOT_this).get("root").get("right").set("_owner", __ROOT_this); // {R18} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 2
        new AccessibleObject(__ROOT_this).get("root").get("right").set("color", (0 != 0));  // WIDEN-I({V24}) == 0
        new AccessibleObject(__ROOT_this).get("root").get("right").set("key", -1);  // {V23} > {V12}
         // {V7} != {V23}
         // {V7} >= {V23}
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.containsKey(__ROOT_key);
        }
//        __ROOT_this.containsKey(__ROOT_key);
    }
    @Test
    public void test11() {
        //test case for state .1.1.1.1.1.2.1.2.2.1.1.2.1.1.1[5]
        this.nullObjectFields = new HashSet<>();
        treemap.TreeMap __ROOT_this = (treemap.TreeMap) newInstance("treemap.TreeMap"); // {R0} == Object[0] (fresh)
        ; // pre_init(treemap/TreeMap)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 2);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        new AccessibleObject(__ROOT_this).set("_blackHeight", 2);  // {V4} >= 0
        new AccessibleObject(__ROOT_this).set("root", newInstance("treemap.TreeMap$Entry")); // {R1} == Object[1] (fresh)
        ; // pre_init(treemap/TreeMap$Entry$HEXTriggers)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R6} == null
        new AccessibleObject(__ROOT_this).get("root").set("_owner", __ROOT_this); // {R10} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 1
        new AccessibleObject(__ROOT_this).get("root").set("color", (1 != 0));  // WIDEN-I({V13}) != 0
         // {V4} >= 1
        int __ROOT_key = 0;new AccessibleObject(__ROOT_this).get("root").set("key", -2);   // {V7} != {V12}
         // {V7} >= {V12}
        new AccessibleObject(__ROOT_this).get("root").set("right", newInstance("treemap.TreeMap$Entry")); // {R5} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("right").set("parent", new AccessibleObject(__ROOT_this).get("root").getValue()); // {R14} == Object[1] (aliases {ROOT}:this.root)
        new AccessibleObject(__ROOT_this).get("root").get("right").set("_owner", __ROOT_this); // {R18} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 2
        new AccessibleObject(__ROOT_this).get("root").get("right").set("color", (4 != 0));  // WIDEN-I({V24}) != 0
         // {V4} >= 2
        new AccessibleObject(__ROOT_this).get("root").get("right").set("key", 0);  // {V23} > {V12}
         // {V7} == {V23}
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.containsKey(__ROOT_key);
        }
//        boolean __returnedValue = __ROOT_this.containsKey(__ROOT_key);
//        assertTrue(__returnedValue == true);
    }
    @Test
    public void test12() {
        //test case for state .1.1.1.1.1.2.1.2.2.1.1.2.1.1.2.1[0]
        this.nullObjectFields = new HashSet<>();
        treemap.TreeMap __ROOT_this = (treemap.TreeMap) newInstance("treemap.TreeMap"); // {R0} == Object[0] (fresh)
        ; // pre_init(treemap/TreeMap)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 2);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        new AccessibleObject(__ROOT_this).set("_blackHeight", 2);  // {V4} >= 0
        new AccessibleObject(__ROOT_this).set("root", newInstance("treemap.TreeMap$Entry")); // {R1} == Object[1] (fresh)
        ; // pre_init(treemap/TreeMap$Entry$HEXTriggers)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R6} == null
        new AccessibleObject(__ROOT_this).get("root").set("_owner", __ROOT_this); // {R10} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 1
        new AccessibleObject(__ROOT_this).get("root").set("color", (3 != 0));  // WIDEN-I({V13}) != 0
         // {V4} >= 1
        int __ROOT_key = 0;new AccessibleObject(__ROOT_this).get("root").set("key", -2);   // {V7} != {V12}
         // {V7} >= {V12}
        new AccessibleObject(__ROOT_this).get("root").set("right", newInstance("treemap.TreeMap$Entry")); // {R5} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("right").set("parent", new AccessibleObject(__ROOT_this).get("root").getValue()); // {R14} == Object[1] (aliases {ROOT}:this.root)
        new AccessibleObject(__ROOT_this).get("root").get("right").set("_owner", __ROOT_this); // {R18} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 2
        new AccessibleObject(__ROOT_this).get("root").get("right").set("color", (5 != 0));  // WIDEN-I({V24}) != 0
         // {V4} >= 2
        new AccessibleObject(__ROOT_this).get("root").get("right").set("key", 1);  // {V23} > {V12}
         // {V7} != {V23}
         // {V7} < {V23}
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.containsKey(__ROOT_key);
        }
//        __ROOT_this.containsKey(__ROOT_key);
    }
    @Test
    public void test13() {
        //test case for state .1.1.1.1.1.2.1.2.2.1.1.2.1.1.2.2[1]
        this.nullObjectFields = new HashSet<>();
        treemap.TreeMap __ROOT_this = (treemap.TreeMap) newInstance("treemap.TreeMap"); // {R0} == Object[0] (fresh)
        ; // pre_init(treemap/TreeMap)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 2);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        new AccessibleObject(__ROOT_this).set("_blackHeight", 2);  // {V4} >= 0
        new AccessibleObject(__ROOT_this).set("root", newInstance("treemap.TreeMap$Entry")); // {R1} == Object[1] (fresh)
        ; // pre_init(treemap/TreeMap$Entry$HEXTriggers)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R6} == null
        new AccessibleObject(__ROOT_this).get("root").set("_owner", __ROOT_this); // {R10} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 1
        new AccessibleObject(__ROOT_this).get("root").set("color", (3 != 0));  // WIDEN-I({V13}) != 0
         // {V4} >= 1
        int __ROOT_key = 0;new AccessibleObject(__ROOT_this).get("root").set("key", -2);   // {V7} != {V12}
         // {V7} >= {V12}
        new AccessibleObject(__ROOT_this).get("root").set("right", newInstance("treemap.TreeMap$Entry")); // {R5} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("root").get("right").set("parent", new AccessibleObject(__ROOT_this).get("root").getValue()); // {R14} == Object[1] (aliases {ROOT}:this.root)
        new AccessibleObject(__ROOT_this).get("root").get("right").set("_owner", __ROOT_this); // {R18} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 2
        new AccessibleObject(__ROOT_this).get("root").get("right").set("color", (5 != 0));  // WIDEN-I({V24}) != 0
         // {V4} >= 2
        new AccessibleObject(__ROOT_this).get("root").get("right").set("key", -1);  // {V23} > {V12}
         // {V7} != {V23}
         // {V7} >= {V23}
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.containsKey(__ROOT_key);
        }
//        __ROOT_this.containsKey(__ROOT_key);
    }
    @Test
    public void test14() {
        //test case for state .1.1.1.1.1.2.1.2.2.2.1[15]
        this.nullObjectFields = new HashSet<>();
        treemap.TreeMap __ROOT_this = (treemap.TreeMap) newInstance("treemap.TreeMap"); // {R0} == Object[0] (fresh)
        ; // pre_init(treemap/TreeMap)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 2);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        new AccessibleObject(__ROOT_this).set("_blackHeight", 1);  // {V4} >= 0
        new AccessibleObject(__ROOT_this).set("root", newInstance("treemap.TreeMap$Entry")); // {R1} == Object[1] (fresh)
        ; // pre_init(treemap/TreeMap$Entry$HEXTriggers)
        new AccessibleObject(__ROOT_this).get("root").set("parent", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "parent")); // {R6} == null
        new AccessibleObject(__ROOT_this).get("root").set("_owner", __ROOT_this); // {R10} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 1
        new AccessibleObject(__ROOT_this).get("root").set("color", (3 != 0));  // WIDEN-I({V13}) != 0
         // {V4} >= 1
        int __ROOT_key = 0;new AccessibleObject(__ROOT_this).get("root").set("key", -2);   // {V7} != {V12}
         // {V7} >= {V12}
        new AccessibleObject(__ROOT_this).get("root").set("right", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("root").getValue(), "right")); // {R5} == null
         // {V4} == 1
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.containsKey(__ROOT_key);
        }
//        boolean __returnedValue = __ROOT_this.containsKey(__ROOT_key);
//        assertTrue(__returnedValue == false);
    }
    @Test
    public void test15() {
        //test case for state .1.1.1.2.1[18]
        this.nullObjectFields = new HashSet<>();
        treemap.TreeMap __ROOT_this = (treemap.TreeMap) newInstance("treemap.TreeMap"); // {R0} == Object[0] (fresh)
        ; // pre_init(treemap/TreeMap)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 0);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        new AccessibleObject(__ROOT_this).set("_blackHeight", 1);  // {V4} >= 0
        new AccessibleObject(__ROOT_this).set("root", null);this.nullObjectFields.add(new ObjectField(__ROOT_this, "root")); // {R1} == null
         // {V0} == 0
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.containsKey(0);
        }
//        boolean __returnedValue = __ROOT_this.containsKey(0);
//        assertTrue(__returnedValue == false);
    }
}

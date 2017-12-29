package common;

import static java.lang.System.identityHashCode;
import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.HashSet;
import sun.misc.Unsafe;

import org.junit.Test;

public class Dll_removeLastTest {
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
        //test case for state .1.1.1.1.1.1.1[39]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 2);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        new AccessibleObject(__ROOT_this).set("header", newInstance("common.LinkedList$Entry")); // {R1} == Object[1] (fresh)
        new AccessibleObject(__ROOT_this).get("header").set("previous", newInstance("common.LinkedList$Entry")); // {R5} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("_owner", __ROOT_this); // {R10} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 1
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("element", __ROOT_this); // {R7} == Object[0] (aliases {ROOT}:this)
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("previous", newInstance("common.LinkedList$Entry")); // {R9} == Object[3] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("previous").get("previous").set("_owner", __ROOT_this); // {R14} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 2
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("next", new AccessibleObject(__ROOT_this).get("header").getValue()); // {R8} == Object[1] (aliases {ROOT}:this.header)
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.removeLast();
        }
//        common.LinkedList __returnedValue = __ROOT_this.removeLast();
//        assertTrue(__returnedValue == __ROOT_this);
    }
    @Test
    public void test1() {
        //test case for state .1.1.1.1.1.2.1[39]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 1);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        new AccessibleObject(__ROOT_this).set("header", newInstance("common.LinkedList$Entry")); // {R1} == Object[1] (fresh)
        new AccessibleObject(__ROOT_this).get("header").set("previous", newInstance("common.LinkedList$Entry")); // {R5} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("_owner", __ROOT_this); // {R10} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 1
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("element", __ROOT_this); // {R7} == Object[0] (aliases {ROOT}:this)
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("previous", new AccessibleObject(__ROOT_this).get("header").getValue()); // {R9} == Object[1] (aliases {ROOT}:this.header)
         // {V0} == 1
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("next", new AccessibleObject(__ROOT_this).get("header").getValue()); // {R8} == Object[1] (aliases {ROOT}:this.header)
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.removeLast();
        }
//        common.LinkedList __returnedValue = __ROOT_this.removeLast();
//        assertTrue(__returnedValue == __ROOT_this);
    }
    @Test
    public void test2() {
        //test case for state .1.1.1.1.2.1.1[39]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 2);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        new AccessibleObject(__ROOT_this).set("header", newInstance("common.LinkedList$Entry")); // {R1} == Object[1] (fresh)
        new AccessibleObject(__ROOT_this).get("header").set("previous", newInstance("common.LinkedList$Entry")); // {R5} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("_owner", __ROOT_this); // {R10} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 1
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("element", new AccessibleObject(__ROOT_this).get("header").getValue()); // {R7} == Object[1] (aliases {ROOT}:this.header)
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("previous", newInstance("common.LinkedList$Entry")); // {R9} == Object[3] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("previous").get("previous").set("_owner", __ROOT_this); // {R14} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 2
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("next", new AccessibleObject(__ROOT_this).get("header").getValue()); // {R8} == Object[1] (aliases {ROOT}:this.header)
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.removeLast();
        }
//        common.LinkedList.Entry __returnedValue = __ROOT_this.removeLast();
//        assertTrue(__returnedValue == new AccessibleObject(__ROOT_this).get("header").getValue());
    }
    @Test
    public void test3() {
        //test case for state .1.1.1.1.2.2.1[39]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 1);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        new AccessibleObject(__ROOT_this).set("header", newInstance("common.LinkedList$Entry")); // {R1} == Object[1] (fresh)
        new AccessibleObject(__ROOT_this).get("header").set("previous", newInstance("common.LinkedList$Entry")); // {R5} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("_owner", __ROOT_this); // {R10} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 1
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("element", new AccessibleObject(__ROOT_this).get("header").getValue()); // {R7} == Object[1] (aliases {ROOT}:this.header)
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("previous", new AccessibleObject(__ROOT_this).get("header").getValue()); // {R9} == Object[1] (aliases {ROOT}:this.header)
         // {V0} == 1
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("next", new AccessibleObject(__ROOT_this).get("header").getValue()); // {R8} == Object[1] (aliases {ROOT}:this.header)
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.removeLast();
        }
//        common.LinkedList.Entry __returnedValue = __ROOT_this.removeLast();
//        assertTrue(__returnedValue == new AccessibleObject(__ROOT_this).get("header").getValue());
    }
    @Test
    public void test4() {
        //test case for state .1.1.1.1.3.1.1[39]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 2);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        new AccessibleObject(__ROOT_this).set("header", newInstance("common.LinkedList$Entry")); // {R1} == Object[1] (fresh)
        new AccessibleObject(__ROOT_this).get("header").set("previous", newInstance("common.LinkedList$Entry")); // {R5} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("_owner", __ROOT_this); // {R10} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 1
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("element", new AccessibleObject(__ROOT_this).get("header").get("previous").getValue()); // {R7} == Object[2] (aliases {ROOT}:this.header.previous)
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("previous", newInstance("common.LinkedList$Entry")); // {R9} == Object[3] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("previous").get("previous").set("_owner", __ROOT_this); // {R14} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 2
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("next", new AccessibleObject(__ROOT_this).get("header").getValue()); // {R8} == Object[1] (aliases {ROOT}:this.header)
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.removeLast();
        }
//        common.LinkedList.Entry __returnedValue = __ROOT_this.removeLast();
//        assertTrue(__returnedValue == new AccessibleObject(__ROOT_this).get("header").get("previous").getValue());
    }
    @Test
    public void test5() {
        //test case for state .1.1.1.1.3.2.1[39]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 1);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        new AccessibleObject(__ROOT_this).set("header", newInstance("common.LinkedList$Entry")); // {R1} == Object[1] (fresh)
        new AccessibleObject(__ROOT_this).get("header").set("previous", newInstance("common.LinkedList$Entry")); // {R5} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("_owner", __ROOT_this); // {R10} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 1
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("element", new AccessibleObject(__ROOT_this).get("header").get("previous").getValue()); // {R7} == Object[2] (aliases {ROOT}:this.header.previous)
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("previous", new AccessibleObject(__ROOT_this).get("header").getValue()); // {R9} == Object[1] (aliases {ROOT}:this.header)
         // {V0} == 1
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("next", new AccessibleObject(__ROOT_this).get("header").getValue()); // {R8} == Object[1] (aliases {ROOT}:this.header)
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.removeLast();
        }
//        common.LinkedList.Entry __returnedValue = __ROOT_this.removeLast();
//        assertTrue(__returnedValue == new AccessibleObject(__ROOT_this).get("header").get("previous").getValue());
    }
    @Test
    public void test6() {
        //test case for state .1.1.1.1.4.1.1[39]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 2);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        new AccessibleObject(__ROOT_this).set("header", newInstance("common.LinkedList$Entry")); // {R1} == Object[1] (fresh)
        new AccessibleObject(__ROOT_this).get("header").set("previous", newInstance("common.LinkedList$Entry")); // {R5} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("_owner", __ROOT_this); // {R10} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 1
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("element", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("header").get("previous").getValue(), "element")); // {R7} == null
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("previous", newInstance("common.LinkedList$Entry")); // {R9} == Object[3] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("previous").get("previous").set("_owner", __ROOT_this); // {R14} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 2
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("next", new AccessibleObject(__ROOT_this).get("header").getValue()); // {R8} == Object[1] (aliases {ROOT}:this.header)
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.removeLast();
        }
//        java.lang.Object __returnedValue = __ROOT_this.removeLast();
//        assertTrue(__returnedValue == null);
    }
    @Test
    public void test7() {
        //test case for state .1.1.1.1.4.2.1[39]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 1);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        new AccessibleObject(__ROOT_this).set("header", newInstance("common.LinkedList$Entry")); // {R1} == Object[1] (fresh)
        new AccessibleObject(__ROOT_this).get("header").set("previous", newInstance("common.LinkedList$Entry")); // {R5} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("_owner", __ROOT_this); // {R10} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 1
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("element", null);this.nullObjectFields.add(new ObjectField(new AccessibleObject(__ROOT_this).get("header").get("previous").getValue(), "element")); // {R7} == null
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("previous", new AccessibleObject(__ROOT_this).get("header").getValue()); // {R9} == Object[1] (aliases {ROOT}:this.header)
         // {V0} == 1
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("next", new AccessibleObject(__ROOT_this).get("header").getValue()); // {R8} == Object[1] (aliases {ROOT}:this.header)
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.removeLast();
        }
//        java.lang.Object __returnedValue = __ROOT_this.removeLast();
//        assertTrue(__returnedValue == null);
    }
    @Test(expected=java.util.NoSuchElementException.class)
    public void test8() {
        //test case for state .1.1.2.1[52]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 0);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        new AccessibleObject(__ROOT_this).set("header", newInstance("common.LinkedList$Entry")); // {R1} == Object[1] (fresh)
        new AccessibleObject(__ROOT_this).get("header").set("previous", new AccessibleObject(__ROOT_this).get("header").getValue()); // {R5} == Object[1] (aliases {ROOT}:this.header)
        new AccessibleObject(__ROOT_this).get("header").set("_owner", __ROOT_this); // {R6} == Object[0] (aliases {ROOT}:this)
         // {V0} == 0
        ; // pre_init(java/util/NoSuchElementException)
        ; // pre_init(java/lang/RuntimeException)
        ; // pre_init(java/lang/Exception)
        ; // pre_init(java/lang/Throwable)
        ; // !pre_init(java/lang/String)
        ; // !pre_init(java/lang/String$CaseInsensitiveComparator)
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.removeLast();
        }
//        __ROOT_this.removeLast();
    }
}

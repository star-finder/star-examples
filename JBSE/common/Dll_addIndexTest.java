package common;

import static java.lang.System.identityHashCode;
import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.HashSet;
import sun.misc.Unsafe;

import org.junit.Test;

public class Dll_addIndexTest {
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
        //test case for state .1.1.1.1.1.1[50]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 1);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        common.LinkedList __ROOT_element = __ROOT_this; // {R2} == Object[0] (aliases {ROOT}:this)
        int __ROOT_index = 1;  // {V6} == {V0}
        new AccessibleObject(__ROOT_this).set("header", newInstance("common.LinkedList$Entry")); // {R1} == Object[1] (fresh)
        ; // pre_init(common/LinkedList$Entry)
        new AccessibleObject(__ROOT_this).get("header").set("previous", newInstance("common.LinkedList$Entry")); // {R6} == Object[3] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("_owner", __ROOT_this); // {R11} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 1
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.add(__ROOT_index, __ROOT_element);
        }
//        __ROOT_this.add(__ROOT_index, __ROOT_element);
    }
    @Test
    public void test1() {
        //test case for state .1.1.1.1.2.1[50]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 0);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        common.LinkedList __ROOT_element = __ROOT_this; // {R2} == Object[0] (aliases {ROOT}:this)
        int __ROOT_index = 0;  // {V6} == {V0}
        new AccessibleObject(__ROOT_this).set("header", newInstance("common.LinkedList$Entry")); // {R1} == Object[1] (fresh)
        ; // pre_init(common/LinkedList$Entry)
        new AccessibleObject(__ROOT_this).get("header").set("previous", new AccessibleObject(__ROOT_this).get("header").getValue()); // {R6} == Object[1] (aliases {ROOT}:this.header)
        new AccessibleObject(__ROOT_this).get("header").set("_owner", __ROOT_this); // {R7} == Object[0] (aliases {ROOT}:this)
         // {V0} == 0
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.add(__ROOT_index, __ROOT_element);
        }
//        __ROOT_this.add(__ROOT_index, __ROOT_element);
    }
    @Test(expected=java.lang.IndexOutOfBoundsException.class)
    public void test2() {
        //test case for state .1.1.1.2.1.1[40]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 0);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        common.LinkedList __ROOT_element = __ROOT_this; // {R2} == Object[0] (aliases {ROOT}:this)
        int __ROOT_index = 1;  // {V6} != {V0}
         // {V6} >= 0
         // {V6} >= {V0}
        ; // pre_init(java/lang/IndexOutOfBoundsException)
        ; // pre_init(java/lang/RuntimeException)
        ; // pre_init(java/lang/Exception)
        ; // pre_init(java/lang/Throwable)
        ; // !pre_init(java/lang/String)
        ; // !pre_init(java/lang/String$CaseInsensitiveComparator)
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.add(__ROOT_index, __ROOT_element);
        }
//        __ROOT_this.add(__ROOT_index, __ROOT_element);
    }
    @Test
    public void test3() {
        //test case for state .1.1.1.2.1.2.1.1[52]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 1);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        common.LinkedList __ROOT_element = __ROOT_this; // {R2} == Object[0] (aliases {ROOT}:this)
        int __ROOT_index = 0;  // {V6} != {V0}
         // {V6} >= 0
         // {V6} < {V0}
        new AccessibleObject(__ROOT_this).set("header", newInstance("common.LinkedList$Entry")); // {R1} == Object[1] (fresh)
        new AccessibleObject(__ROOT_this).get("header").set("next", newInstance("common.LinkedList$Entry")); // {R5} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("next").set("_owner", __ROOT_this); // {R11} == Object[0] (aliases {ROOT}:this)
         // 1 > {V6}
        ; // pre_init(common/LinkedList$Entry)
        new AccessibleObject(__ROOT_this).get("header").get("next").set("previous", new AccessibleObject(__ROOT_this).get("header").getValue()); // {R10} == Object[1] (aliases {ROOT}:this.header)
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.add(__ROOT_index, __ROOT_element);
        }
//        __ROOT_this.add(__ROOT_index, __ROOT_element);
    }
    @Test
    public void test4() {
        //test case for state .1.1.1.2.1.2.1.2.1.1[52]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 2);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        common.LinkedList __ROOT_element = __ROOT_this; // {R2} == Object[0] (aliases {ROOT}:this)
        int __ROOT_index = 1;  // {V6} != {V0}
         // {V6} >= 0
         // {V6} < {V0}
        new AccessibleObject(__ROOT_this).set("header", newInstance("common.LinkedList$Entry")); // {R1} == Object[1] (fresh)
        new AccessibleObject(__ROOT_this).get("header").set("next", newInstance("common.LinkedList$Entry")); // {R5} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("next").set("_owner", __ROOT_this); // {R11} == Object[0] (aliases {ROOT}:this)
         // 1 <= {V6}
        new AccessibleObject(__ROOT_this).get("header").get("next").set("next", newInstance("common.LinkedList$Entry")); // {R9} == Object[3] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").set("_owner", __ROOT_this); // {R15} == Object[0] (aliases {ROOT}:this)
         // 2 > {V6}
        ; // pre_init(common/LinkedList$Entry)
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").set("previous", new AccessibleObject(__ROOT_this).get("header").get("next").getValue()); // {R14} == Object[2] (aliases {ROOT}:this.header.next)
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.add(__ROOT_index, __ROOT_element);
        }
//        __ROOT_this.add(__ROOT_index, __ROOT_element);
    }
    @Test
    public void test5() {
        //test case for state .1.1.1.2.1.2.1.2.1.2.1.1[9]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 3);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        common.LinkedList __ROOT_element = __ROOT_this; // {R2} == Object[0] (aliases {ROOT}:this)
        int __ROOT_index = 2;  // {V6} != {V0}
         // {V6} >= 0
         // {V6} < {V0}
        new AccessibleObject(__ROOT_this).set("header", newInstance("common.LinkedList$Entry")); // {R1} == Object[1] (fresh)
        new AccessibleObject(__ROOT_this).get("header").set("next", newInstance("common.LinkedList$Entry")); // {R5} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("next").set("_owner", __ROOT_this); // {R11} == Object[0] (aliases {ROOT}:this)
         // 1 <= {V6}
        new AccessibleObject(__ROOT_this).get("header").get("next").set("next", newInstance("common.LinkedList$Entry")); // {R9} == Object[3] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").set("_owner", __ROOT_this); // {R15} == Object[0] (aliases {ROOT}:this)
         // 2 <= {V6}
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").set("next", newInstance("common.LinkedList$Entry")); // {R13} == Object[4] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").get("next").set("_owner", __ROOT_this); // {R19} == Object[0] (aliases {ROOT}:this)
         // 3 > {V6}
        ; // pre_init(common/LinkedList$Entry)
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").get("next").set("previous", new AccessibleObject(__ROOT_this).get("header").get("next").get("next").getValue()); // {R18} == Object[3] (aliases {ROOT}:this.header.next.next)
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.add(__ROOT_index, __ROOT_element);
        }
//        __ROOT_this.add(__ROOT_index, __ROOT_element);
    }
    @Test
    public void test6() {
        //test case for state .1.1.1.2.1.2.1.2.1.2.1.2.1[0]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 4);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        common.LinkedList __ROOT_element = __ROOT_this; // {R2} == Object[0] (aliases {ROOT}:this)
        int __ROOT_index = 3;  // {V6} != {V0}
         // {V6} >= 0
         // {V6} < {V0}
        new AccessibleObject(__ROOT_this).set("header", newInstance("common.LinkedList$Entry")); // {R1} == Object[1] (fresh)
        new AccessibleObject(__ROOT_this).get("header").set("next", newInstance("common.LinkedList$Entry")); // {R5} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("next").set("_owner", __ROOT_this); // {R11} == Object[0] (aliases {ROOT}:this)
         // 1 <= {V6}
        new AccessibleObject(__ROOT_this).get("header").get("next").set("next", newInstance("common.LinkedList$Entry")); // {R9} == Object[3] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").set("_owner", __ROOT_this); // {R15} == Object[0] (aliases {ROOT}:this)
         // 2 <= {V6}
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").set("next", newInstance("common.LinkedList$Entry")); // {R13} == Object[4] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").get("next").set("_owner", __ROOT_this); // {R19} == Object[0] (aliases {ROOT}:this)
         // 3 <= {V6}
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").get("next").set("next", newInstance("common.LinkedList$Entry")); // {R17} == Object[5] (fresh)
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.add(__ROOT_index, __ROOT_element);
        }
//        __ROOT_this.add(__ROOT_index, __ROOT_element);
    }
    @Test
    public void test7() {
        //test case for state .1.1.1.2.1.2.1.2.1.2.1.2.2[1]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 4);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        common.LinkedList __ROOT_element = __ROOT_this; // {R2} == Object[0] (aliases {ROOT}:this)
        int __ROOT_index = 3;  // {V6} != {V0}
         // {V6} >= 0
         // {V6} < {V0}
        new AccessibleObject(__ROOT_this).set("header", newInstance("common.LinkedList$Entry")); // {R1} == Object[1] (fresh)
        new AccessibleObject(__ROOT_this).get("header").set("next", newInstance("common.LinkedList$Entry")); // {R5} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("next").set("_owner", __ROOT_this); // {R11} == Object[0] (aliases {ROOT}:this)
         // 1 <= {V6}
        new AccessibleObject(__ROOT_this).get("header").get("next").set("next", newInstance("common.LinkedList$Entry")); // {R9} == Object[3] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").set("_owner", __ROOT_this); // {R15} == Object[0] (aliases {ROOT}:this)
         // 2 <= {V6}
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").set("next", newInstance("common.LinkedList$Entry")); // {R13} == Object[4] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").get("next").set("_owner", __ROOT_this); // {R19} == Object[0] (aliases {ROOT}:this)
         // 3 <= {V6}
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").get("next").set("next", new AccessibleObject(__ROOT_this).get("header").getValue()); // {R17} == Object[1] (aliases {ROOT}:this.header)
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.add(__ROOT_index, __ROOT_element);
        }
//        __ROOT_this.add(__ROOT_index, __ROOT_element);
    }
    @Test(expected=java.lang.IndexOutOfBoundsException.class)
    public void test8() {
        //test case for state .1.1.1.2.2[40]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 4);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        common.LinkedList __ROOT_element = __ROOT_this; // {R2} == Object[0] (aliases {ROOT}:this)
        int __ROOT_index = -1;  // {V6} != {V0}
         // {V6} < 0
        ; // pre_init(java/lang/IndexOutOfBoundsException)
        ; // pre_init(java/lang/RuntimeException)
        ; // pre_init(java/lang/Exception)
        ; // pre_init(java/lang/Throwable)
        ; // !pre_init(java/lang/String)
        ; // !pre_init(java/lang/String$CaseInsensitiveComparator)
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.add(__ROOT_index, __ROOT_element);
        }
//        __ROOT_this.add(__ROOT_index, __ROOT_element);
    }
    @Test
    public void test9() {
        //test case for state .1.1.2.1.1.1[50]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 1);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        java.lang.Object __ROOT_element = null; // {R2} == null
        int __ROOT_index = 1;  // {V6} == {V0}
        new AccessibleObject(__ROOT_this).set("header", newInstance("common.LinkedList$Entry")); // {R1} == Object[1] (fresh)
        ; // pre_init(common/LinkedList$Entry)
        new AccessibleObject(__ROOT_this).get("header").set("previous", newInstance("common.LinkedList$Entry")); // {R6} == Object[3] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("previous").set("_owner", __ROOT_this); // {R11} == Object[0] (aliases {ROOT}:this)
         // {V0} >= 1
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.add(__ROOT_index, __ROOT_element);
        }
//        __ROOT_this.add(__ROOT_index, __ROOT_element);
    }
    @Test
    public void test10() {
        //test case for state .1.1.2.1.2.1[50]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 0);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        java.lang.Object __ROOT_element = null; // {R2} == null
        int __ROOT_index = 0;  // {V6} == {V0}
        new AccessibleObject(__ROOT_this).set("header", newInstance("common.LinkedList$Entry")); // {R1} == Object[1] (fresh)
        ; // pre_init(common/LinkedList$Entry)
        new AccessibleObject(__ROOT_this).get("header").set("previous", new AccessibleObject(__ROOT_this).get("header").getValue()); // {R6} == Object[1] (aliases {ROOT}:this.header)
        new AccessibleObject(__ROOT_this).get("header").set("_owner", __ROOT_this); // {R7} == Object[0] (aliases {ROOT}:this)
         // {V0} == 0
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.add(__ROOT_index, __ROOT_element);
        }
//        __ROOT_this.add(__ROOT_index, __ROOT_element);
    }
    @Test(expected=java.lang.IndexOutOfBoundsException.class)
    public void test11() {
        //test case for state .1.1.2.2.1.1[40]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 0);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        java.lang.Object __ROOT_element = null; // {R2} == null
        int __ROOT_index = 1;  // {V6} != {V0}
         // {V6} >= 0
         // {V6} >= {V0}
        ; // pre_init(java/lang/IndexOutOfBoundsException)
        ; // pre_init(java/lang/RuntimeException)
        ; // pre_init(java/lang/Exception)
        ; // pre_init(java/lang/Throwable)
        ; // !pre_init(java/lang/String)
        ; // !pre_init(java/lang/String$CaseInsensitiveComparator)
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.add(__ROOT_index, __ROOT_element);
        }
//        __ROOT_this.add(__ROOT_index, __ROOT_element);
    }
    @Test
    public void test12() {
        //test case for state .1.1.2.2.1.2.1.1[52]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 1);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        java.lang.Object __ROOT_element = null; // {R2} == null
        int __ROOT_index = 0;  // {V6} != {V0}
         // {V6} >= 0
         // {V6} < {V0}
        new AccessibleObject(__ROOT_this).set("header", newInstance("common.LinkedList$Entry")); // {R1} == Object[1] (fresh)
        new AccessibleObject(__ROOT_this).get("header").set("next", newInstance("common.LinkedList$Entry")); // {R5} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("next").set("_owner", __ROOT_this); // {R11} == Object[0] (aliases {ROOT}:this)
         // 1 > {V6}
        ; // pre_init(common/LinkedList$Entry)
        new AccessibleObject(__ROOT_this).get("header").get("next").set("previous", new AccessibleObject(__ROOT_this).get("header").getValue()); // {R10} == Object[1] (aliases {ROOT}:this.header)
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.add(__ROOT_index, __ROOT_element);
        }
//        __ROOT_this.add(__ROOT_index, __ROOT_element);
    }
    @Test
    public void test13() {
        //test case for state .1.1.2.2.1.2.1.2.1.1[52]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 2);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        java.lang.Object __ROOT_element = null; // {R2} == null
        int __ROOT_index = 1;  // {V6} != {V0}
         // {V6} >= 0
         // {V6} < {V0}
        new AccessibleObject(__ROOT_this).set("header", newInstance("common.LinkedList$Entry")); // {R1} == Object[1] (fresh)
        new AccessibleObject(__ROOT_this).get("header").set("next", newInstance("common.LinkedList$Entry")); // {R5} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("next").set("_owner", __ROOT_this); // {R11} == Object[0] (aliases {ROOT}:this)
         // 1 <= {V6}
        new AccessibleObject(__ROOT_this).get("header").get("next").set("next", newInstance("common.LinkedList$Entry")); // {R9} == Object[3] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").set("_owner", __ROOT_this); // {R15} == Object[0] (aliases {ROOT}:this)
         // 2 > {V6}
        ; // pre_init(common/LinkedList$Entry)
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").set("previous", new AccessibleObject(__ROOT_this).get("header").get("next").getValue()); // {R14} == Object[2] (aliases {ROOT}:this.header.next)
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.add(__ROOT_index, __ROOT_element);
        }
//        __ROOT_this.add(__ROOT_index, __ROOT_element);
    }
    @Test
    public void test14() {
        //test case for state .1.1.2.2.1.2.1.2.1.2.1.1[9]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 3);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        java.lang.Object __ROOT_element = null; // {R2} == null
        int __ROOT_index = 2;  // {V6} != {V0}
         // {V6} >= 0
         // {V6} < {V0}
        new AccessibleObject(__ROOT_this).set("header", newInstance("common.LinkedList$Entry")); // {R1} == Object[1] (fresh)
        new AccessibleObject(__ROOT_this).get("header").set("next", newInstance("common.LinkedList$Entry")); // {R5} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("next").set("_owner", __ROOT_this); // {R11} == Object[0] (aliases {ROOT}:this)
         // 1 <= {V6}
        new AccessibleObject(__ROOT_this).get("header").get("next").set("next", newInstance("common.LinkedList$Entry")); // {R9} == Object[3] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").set("_owner", __ROOT_this); // {R15} == Object[0] (aliases {ROOT}:this)
         // 2 <= {V6}
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").set("next", newInstance("common.LinkedList$Entry")); // {R13} == Object[4] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").get("next").set("_owner", __ROOT_this); // {R19} == Object[0] (aliases {ROOT}:this)
         // 3 > {V6}
        ; // pre_init(common/LinkedList$Entry)
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").get("next").set("previous", new AccessibleObject(__ROOT_this).get("header").get("next").get("next").getValue()); // {R18} == Object[3] (aliases {ROOT}:this.header.next.next)
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.add(__ROOT_index, __ROOT_element);
        }
//        __ROOT_this.add(__ROOT_index, __ROOT_element);
    }
    @Test
    public void test15() {
        //test case for state .1.1.2.2.1.2.1.2.1.2.1.2.1[0]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 4);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        java.lang.Object __ROOT_element = null; // {R2} == null
        int __ROOT_index = 3;  // {V6} != {V0}
         // {V6} >= 0
         // {V6} < {V0}
        new AccessibleObject(__ROOT_this).set("header", newInstance("common.LinkedList$Entry")); // {R1} == Object[1] (fresh)
        new AccessibleObject(__ROOT_this).get("header").set("next", newInstance("common.LinkedList$Entry")); // {R5} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("next").set("_owner", __ROOT_this); // {R11} == Object[0] (aliases {ROOT}:this)
         // 1 <= {V6}
        new AccessibleObject(__ROOT_this).get("header").get("next").set("next", newInstance("common.LinkedList$Entry")); // {R9} == Object[3] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").set("_owner", __ROOT_this); // {R15} == Object[0] (aliases {ROOT}:this)
         // 2 <= {V6}
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").set("next", newInstance("common.LinkedList$Entry")); // {R13} == Object[4] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").get("next").set("_owner", __ROOT_this); // {R19} == Object[0] (aliases {ROOT}:this)
         // 3 <= {V6}
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").get("next").set("next", newInstance("common.LinkedList$Entry")); // {R17} == Object[5] (fresh)
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.add(__ROOT_index, __ROOT_element);
        }
//        __ROOT_this.add(__ROOT_index, __ROOT_element);
    }
    @Test
    public void test16() {
        //test case for state .1.1.2.2.1.2.1.2.1.2.1.2.2[1]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 4);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        java.lang.Object __ROOT_element = null; // {R2} == null
        int __ROOT_index = 3;  // {V6} != {V0}
         // {V6} >= 0
         // {V6} < {V0}
        new AccessibleObject(__ROOT_this).set("header", newInstance("common.LinkedList$Entry")); // {R1} == Object[1] (fresh)
        new AccessibleObject(__ROOT_this).get("header").set("next", newInstance("common.LinkedList$Entry")); // {R5} == Object[2] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("next").set("_owner", __ROOT_this); // {R11} == Object[0] (aliases {ROOT}:this)
         // 1 <= {V6}
        new AccessibleObject(__ROOT_this).get("header").get("next").set("next", newInstance("common.LinkedList$Entry")); // {R9} == Object[3] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").set("_owner", __ROOT_this); // {R15} == Object[0] (aliases {ROOT}:this)
         // 2 <= {V6}
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").set("next", newInstance("common.LinkedList$Entry")); // {R13} == Object[4] (fresh)
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").get("next").set("_owner", __ROOT_this); // {R19} == Object[0] (aliases {ROOT}:this)
         // 3 <= {V6}
        new AccessibleObject(__ROOT_this).get("header").get("next").get("next").get("next").set("next", new AccessibleObject(__ROOT_this).get("header").getValue()); // {R17} == Object[1] (aliases {ROOT}:this.header)
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.add(__ROOT_index, __ROOT_element);
        }
//        __ROOT_this.add(__ROOT_index, __ROOT_element);
    }
    @Test(expected=java.lang.IndexOutOfBoundsException.class)
    public void test17() {
        //test case for state .1.1.2.2.2[40]
        this.nullObjectFields = new HashSet<>();
        common.LinkedList __ROOT_this = (common.LinkedList) newInstance("common.LinkedList"); // {R0} == Object[0] (fresh)
        ; // pre_init(common/LinkedList)
        ; // pre_init(java/util/AbstractSequentialList)
        ; // pre_init(java/util/AbstractList)
        ; // pre_init(java/util/AbstractCollection)
        ; // !pre_init(java/lang/Object)
        new AccessibleObject(__ROOT_this).set("size", 4);  // {V0} >= 0
        ; // pre_init(jbse/meta/Analysis)
        java.lang.Object __ROOT_element = null; // {R2} == null
        int __ROOT_index = -1;  // {V6} != {V0}
         // {V6} < 0
        ; // pre_init(java/lang/IndexOutOfBoundsException)
        ; // pre_init(java/lang/RuntimeException)
        ; // pre_init(java/lang/Exception)
        ; // pre_init(java/lang/Throwable)
        ; // !pre_init(java/lang/String)
        ; // !pre_init(java/lang/String$CaseInsensitiveComparator)
        if (__ROOT_this.repOK()) {
        	System.out.println("true");
        	__ROOT_this.add(__ROOT_index, __ROOT_element);
        }
//        __ROOT_this.add(__ROOT_index, __ROOT_element);
    }
}

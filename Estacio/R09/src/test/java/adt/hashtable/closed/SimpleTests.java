package adt.hashtable.closed;

import static adt.hashtable.hashfunction.HashFunctionClosedAddressMethod.DIVISION;
import static adt.hashtable.hashfunction.HashFunctionClosedAddressMethod.MULTIPLICATION;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Assert;;

public class SimpleTests {

    static HashtableClosedAddressImpl<Integer> hash;

    public static void main(String[] args) {
        SimpleTests inst = new SimpleTests();
        try {
            for (Method m : SimpleTests.class.getDeclaredMethods()) {
                if (m.getName().equals("main"))
                    continue;
                System.out.println("Executando: " + m.getName());
                hash = new HashtableClosedAddressImpl<>(30, DIVISION);
                m.invoke(inst, null);
                hash = new HashtableClosedAddressImpl<>(30, MULTIPLICATION);
                m.invoke(inst, null);
            }
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("fim");
    }

    public void testColisoes() {
        Assert.assertEquals(0, hash.getCOLLISIONS());

        hash.insert(1);
        hash.insert(4);
        hash.insert(5);
        hash.insert(6);
        hash.insert(7);
        hash.insert(8);
        Assert.assertEquals(0, hash.getCOLLISIONS());

        hash.insert(63);
        Assert.assertEquals(1, hash.getCOLLISIONS());

        hash.insert(35);
        Assert.assertEquals(2, hash.getCOLLISIONS());

        hash.insert(38);
        Assert.assertEquals(3, hash.getCOLLISIONS());

        hash.remove(38);
        Assert.assertEquals(2, hash.getCOLLISIONS());

        hash.remove(63);
        Assert.assertEquals(1, hash.getCOLLISIONS());
    }

    public void testIndexOf() {
        Assert.assertTrue(hash.isEmpty());
        Assert.assertEquals(hash.indexOf(1), -1);
        Assert.assertEquals(hash.indexOf(null), -1);

        hash.insert(new Integer(1));
        Assert.assertFalse(hash.isEmpty());
        Assert.assertNotEquals(hash.indexOf(1), -1);
        Assert.assertEquals(hash.indexOf(null), -1);

        hash.insert(2);
        hash.insert(5);
        hash.insert(10);
        Assert.assertNotEquals(hash.indexOf(2), -1);
        Assert.assertNotEquals(hash.indexOf(5), -1);
        Assert.assertNotEquals(hash.indexOf(10), -1);
        Assert.assertEquals(hash.indexOf(null), -1);
        Assert.assertEquals(hash.indexOf(3), -1);
        Assert.assertEquals(hash.indexOf(31), -1);

        Assert.assertEquals(4, hash.size());
        Assert.assertFalse(hash.isEmpty());

        hash.remove(2);
        Assert.assertEquals(hash.indexOf(2), -1);
        Assert.assertEquals(3, hash.size());
        hash.insert(2);
        Assert.assertNotEquals(hash.indexOf(2), -1);
        hash.remove(2);
        hash.remove(null);
        Assert.assertEquals(hash.indexOf(2), -1);
        Assert.assertEquals(3, hash.size());
        Assert.assertFalse(hash.isEmpty());
    }

    public void testAdd() {
        Assert.assertTrue(hash.isEmpty());
        hash.insert(new Integer(1));
        Assert.assertFalse(hash.isEmpty());
        hash.insert(new Integer(2));
        hash.insert(new Integer(29));
        hash.insert(new Integer(44));
        hash.insert(new Integer(1111));
        hash.insert(new Integer(654));
        hash.insert(null);

        Assert.assertNull(hash.search(null));
        Assert.assertFalse(hash.isEmpty());

        Assert.assertNotNull(hash.search(new Integer(1)));
        Assert.assertTrue(hash.search(new Integer(1)).equals(1));

        Assert.assertNotNull(hash.search(new Integer(29)));
        Assert.assertTrue(hash.search(new Integer(29)).equals(29));

        Assert.assertNotNull(hash.search(new Integer(1111)));
        Assert.assertTrue(hash.search(new Integer(1111)).equals(1111));

        Assert.assertNotNull(hash.search(new Integer(654)));
        Assert.assertTrue(hash.search(new Integer(654)).equals(654));
    }

    public void testContains() {
        Assert.assertNull(hash.search(1));
        Assert.assertNull(hash.search(2));
        Assert.assertNull(hash.search(1231231));
        Assert.assertNull(hash.search(32131));
        Assert.assertNull(hash.search(12113211));

        Assert.assertNull(hash.search(null));
    }

    public void testSize() {
        Assert.assertTrue(hash.isEmpty());
        Assert.assertEquals(0, hash.size());
        hash.insert(null);
        Assert.assertTrue(hash.isEmpty());
        Assert.assertEquals(0, hash.size());

        hash.insert(0);
        hash.insert(null);
        Assert.assertEquals(1, hash.size());
        int i = 0;
        while (i++ < 200) {
            hash.insert(new Integer(i));
            Assert.assertEquals(i + 1, hash.size());
        }
    }

    public void testRemove() {
        hash.remove(null);
        Assert.assertEquals(0, hash.size());
        Assert.assertTrue(hash.isEmpty());
        hash.remove(1);
        hash.remove(200000);

        hash.insert(new Integer(1));
        Assert.assertFalse(hash.isEmpty());
        hash.remove(200000);
        hash.remove(null);
        Assert.assertEquals(1, hash.size());
        Assert.assertFalse(hash.isEmpty());

        hash.remove(1);
        Assert.assertEquals(0, hash.size());
        Assert.assertTrue(hash.isEmpty());

        hash.remove(1);
        hash.remove(200000);

        hash.insert(new Integer(1));
        hash.insert(new Integer(100));
        hash.insert(new Integer(5646));
        hash.insert(new Integer(987888));
        Assert.assertEquals(4, hash.size());

        hash.remove(1);
        Assert.assertEquals(3, hash.size());

        hash.remove(null);
        hash.remove(1);
        Assert.assertEquals(3, hash.size());

        hash.remove(987888);
        Assert.assertEquals(2, hash.size());
    }
}
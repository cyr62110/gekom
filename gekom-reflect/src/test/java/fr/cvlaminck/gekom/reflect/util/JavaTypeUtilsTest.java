package fr.cvlaminck.gekom.reflect.util;

import fr.cvlaminck.gekom.reflect.type.FakeParameterizedType;
import fr.cvlaminck.gekom.reflect.type.FakeWildcardType;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JavaTypeUtilsTest {

    @Test
    public void testGetRawType() throws Exception {
        assertEquals(Integer.class, TypeUtils.INSTANCE.getRawType(getAttributeType("concreteVar")));
        assertEquals(Integer[].class, TypeUtils.INSTANCE.getRawType(getAttributeType("concreteArray")));

        assertEquals(Collection.class, TypeUtils.INSTANCE.getRawType(getAttributeType("concreteCollection")));

        assertEquals(Object.class, TypeUtils.INSTANCE.getRawType(((ParameterizedType) getAttributeType("genericCollection")).getActualTypeArguments()[0]));
        assertEquals(Integer.class, TypeUtils.INSTANCE.getRawType(((ParameterizedType) getAttributeType("genericExtendsCollection")).getActualTypeArguments()[0]));
        assertEquals(Object.class, TypeUtils.INSTANCE.getRawType(((ParameterizedType) getAttributeType("genericSuperCollection")).getActualTypeArguments()[0]));
    }

    @Test
    public void testEquals() throws NoSuchFieldException {
        // null cases
        assertTrue(TypeUtils.INSTANCE.equals(null, null));
        assertFalse(TypeUtils.INSTANCE.equals(String.class, null));
        assertFalse(TypeUtils.INSTANCE.equals(null, String.class));

        // Class cases
        assertTrue(TypeUtils.INSTANCE.equals(String.class, String.class));
        assertFalse(TypeUtils.INSTANCE.equals(String.class, Integer.class));
        assertFalse(TypeUtils.INSTANCE.equals(Collection.class, List.class));

        // Parameterized Type
        assertFalse(TypeUtils.INSTANCE.equals(getAttributeType("concreteList"), new FakeParameterizedType(JavaTypeUtilsTest.class, List.class, new Type[]{Integer.class})));
        assertFalse(TypeUtils.INSTANCE.equals(getAttributeType("concreteCollection"), new FakeParameterizedType(JavaTypeUtilsTest.class, Collection.class, new Type[]{String.class})));
        assertFalse(TypeUtils.INSTANCE.equals(getAttributeType("genericExtendsCollection"),
                new FakeParameterizedType(JavaTypeUtilsTest.class, Collection.class, new Type[]{
                        new FakeWildcardType(Integer.class, null)
                })));

        // Wildcard Type
        assertTrue(TypeUtils.INSTANCE.equals(
                ((ParameterizedType) getAttributeType("genericExtendsCollection")).getActualTypeArguments()[0],
                ((ParameterizedType) getAttributeType("genericExtendsMap")).getActualTypeArguments()[0]));
        assertFalse(TypeUtils.INSTANCE.equals(
                ((ParameterizedType) getAttributeType("genericCollection")).getActualTypeArguments()[0],
                ((ParameterizedType) getAttributeType("genericExtendsCollection")).getActualTypeArguments()[0]));
    }

    @Test
    public void testIsClassAssignableFrom() {
        assertFalse(TypeUtils.INSTANCE.isAssignableFrom(Integer.class, Number.class));
        assertTrue(TypeUtils.INSTANCE.isAssignableFrom(Number.class, Integer.class));
    }

    @Test
    public void testIsParameterizedTypeAssignableFrom() {
        FakeParameterizedType collectionOfInteger = new FakeParameterizedType(null, Collection.class, new Type[]{Integer.class});
        FakeParameterizedType collectionOfNumber = new FakeParameterizedType(null, Collection.class, new Type[]{Number.class});
        FakeParameterizedType listOfInteger = new FakeParameterizedType(null, List.class, new Type[]{Integer.class});
        FakeParameterizedType listOfNumber = new FakeParameterizedType(null, List.class, new Type[]{Number.class});

        assertFalse(TypeUtils.INSTANCE.isAssignableFrom(Integer.class, collectionOfInteger));

        assertTrue(TypeUtils.INSTANCE.isAssignableFrom(Collection.class, collectionOfInteger));
        assertTrue(TypeUtils.INSTANCE.isAssignableFrom(Collection.class, listOfInteger));
        assertTrue(TypeUtils.INSTANCE.isAssignableFrom(List.class, listOfInteger));
    }

    private Type getAttributeType(String attribute) throws NoSuchFieldException {
        Field field = DummyClass.class.getDeclaredField(attribute);
        return field.getGenericType();
    }

    @SuppressWarnings("unused")
    static class DummyClass {
        public Integer concreteVar;
        public Integer[] concreteArray;
        public List<Integer> concreteList;
        public Collection<Integer> concreteCollection;
        public Collection<String> concreteStringCollection;
        public Collection<?> genericCollection;
        public Collection<? extends Integer> genericExtendsCollection;
        public Collection<? super Integer> genericSuperCollection;
        public Map<? extends Integer, ?> genericExtendsMap;
    }

    static class ArrayListOfInteger extends ArrayList<Integer> {

    }

    static class ArrayListOfNumber extends ArrayList<Number> {

    }
}

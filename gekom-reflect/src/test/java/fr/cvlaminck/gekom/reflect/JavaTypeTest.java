package fr.cvlaminck.gekom.reflect;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.WildcardType;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JavaTypeTest {

    @Test
    public void testTypeOfFields() throws NoSuchFieldException {
        Field field;
        ParameterizedType parameterizedType;

        field = DummyClass.class.getDeclaredField("concreteVar");
        assertEquals("class java.lang.Integer", field.getGenericType().toString());
        assertEquals("java.lang.Class", field.getGenericType().getClass().getName());

        field = DummyClass.class.getDeclaredField("concreteArray");
        assertEquals("class [Ljava.lang.Integer;", field.getGenericType().toString());
        assertEquals("java.lang.Class", field.getGenericType().getClass().getName());

        field = DummyClass.class.getDeclaredField("concreteCollection");
        assertEquals("java.util.Collection<java.lang.Integer>", field.getGenericType().toString());
        assertTrue(ParameterizedType.class.isAssignableFrom(field.getGenericType().getClass()));
        parameterizedType = (ParameterizedType) field.getGenericType();
        assertEquals(1, parameterizedType.getActualTypeArguments().length);
        assertEquals("class java.lang.Integer", parameterizedType.getActualTypeArguments()[0].toString());

        field = DummyClass.class.getDeclaredField("genericCollection");
        assertEquals("java.util.Collection<?>", field.getGenericType().toString());
        assertTrue(ParameterizedType.class.isAssignableFrom(field.getGenericType().getClass()));
        parameterizedType = (ParameterizedType) field.getGenericType();
        assertEquals(1, parameterizedType.getActualTypeArguments().length);
        assertEquals("?", parameterizedType.getActualTypeArguments()[0].toString());
        assertTrue(WildcardType.class.isAssignableFrom(parameterizedType.getActualTypeArguments()[0].getClass()));

        field = DummyClass.class.getDeclaredField("genericExtendsCollection");
        assertEquals("java.util.Collection<? extends java.lang.Integer>", field.getGenericType().toString());
        assertTrue(ParameterizedType.class.isAssignableFrom(field.getGenericType().getClass()));

        field = DummyClass.class.getDeclaredField("genericSuperCollection");
        assertEquals("java.util.Collection<? super java.lang.Integer>", field.getGenericType().toString());
        assertTrue(ParameterizedType.class.isAssignableFrom(field.getGenericType().getClass()));
    }

    static class DummyClass {
        public Integer concreteVar;
        public Integer[] concreteArray;
        public Collection<Integer> concreteCollection;
        public Collection<?> genericCollection;
        public Collection<? extends Integer> genericExtendsCollection;
        public Collection<? super Integer> genericSuperCollection;
    }
}

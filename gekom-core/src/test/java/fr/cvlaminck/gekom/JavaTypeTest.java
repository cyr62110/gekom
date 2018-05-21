package fr.cvlaminck.gekom;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JavaTypeTest {

    @Test
    public void testTypeOfFields() throws NoSuchFieldException {
        Field field = null;

        field = DummyClass.class.getDeclaredField("concreteVar");
        assertEquals("java.lang.Integer", field.getGenericType().getTypeName());

        field = DummyClass.class.getDeclaredField("concreteArray");
        assertEquals("java.lang.Integer[]", field.getGenericType().getTypeName());

        field = DummyClass.class.getDeclaredField("concreteCollection");
        assertEquals("java.util.Collection<java.lang.Integer>", field.getGenericType().getTypeName());

        field = DummyClass.class.getDeclaredField("genericExtendsCollection");
        assertEquals("java.util.Collection<? extends java.lang.Integer>", field.getGenericType().getTypeName());
    }

    static class DummyClass {
        public Integer concreteVar;
        public Integer[] concreteArray;
        public Collection<Integer> concreteCollection;
        public Collection<? extends Integer> genericExtendsCollection;
    }
}

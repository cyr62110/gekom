package fr.cvlaminck.gekom.core.reflect;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaFakeParameterizedType {

    @Test
    public void testHashCode() throws NoSuchFieldException {
        ParameterizedType parameterizedType = getParameterizedType("concreteCollection");
        FakeParameterizedType fakeParameterizedType = new FakeParameterizedType(null, Collection.class, new Type[]{Integer.class});
        assertEquals(parameterizedType.hashCode(), fakeParameterizedType.hashCode());
    }

    private ParameterizedType getParameterizedType(String attribute) throws NoSuchFieldException {
        Field field = JavaFakeParameterizedType.DummyClass.class.getDeclaredField(attribute);
        return (ParameterizedType) field.getGenericType();
    }

    static class DummyClass {
        public Collection<Integer> concreteCollection;
    }
}

package fr.cvlaminck.gekom.reflect.type;

import fr.cvlaminck.gekom.reflect.type.FakeWildcardType;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.WildcardType;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaFakeWildcardTypeTest {

    @Test
    public void testHashCode() throws NoSuchFieldException {
        WildcardType wildcardType;
        FakeWildcardType fakeWildcardType;

        wildcardType = getWildcardType("genericExtendsCollection");
        fakeWildcardType = new FakeWildcardType(Integer.class, null);
        assertEquals(wildcardType.hashCode(), fakeWildcardType.hashCode());

        wildcardType = getWildcardType("genericSuperCollection");
        fakeWildcardType = new FakeWildcardType(Object.class, Integer.class);
        assertEquals(wildcardType.hashCode(), fakeWildcardType.hashCode());
    }

    private WildcardType getWildcardType(String attribute) throws NoSuchFieldException {
        Field field = DummyClass.class.getDeclaredField(attribute);
        ParameterizedType type = (ParameterizedType) field.getGenericType();
        return (WildcardType) type.getActualTypeArguments()[0];
    }

    @SuppressWarnings("unused")
    static class DummyClass {
        public Collection<? extends Integer> genericExtendsCollection;
        public Collection<? super Integer> genericSuperCollection;
    }
}

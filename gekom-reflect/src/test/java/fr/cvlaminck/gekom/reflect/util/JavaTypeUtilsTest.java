package fr.cvlaminck.gekom.reflect.util;

import fr.cvlaminck.gekom.reflect.util.TypeUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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
        // TODO Find true test. Once forged
        assertFalse(TypeUtils.INSTANCE.equals(getAttributeType("concreteList"), getAttributeType("concreteCollection")));
        assertFalse(TypeUtils.INSTANCE.equals(getAttributeType("concreteCollection"), getAttributeType("concreteStringCollection")));
        assertFalse(TypeUtils.INSTANCE.equals(getAttributeType("genericExtendsCollection"), getAttributeType("genericSuperCollection")));

        // Wildcard Type
        assertTrue(TypeUtils.INSTANCE.equals(
                ((ParameterizedType) getAttributeType("genericExtendsCollection")).getActualTypeArguments()[0],
                ((ParameterizedType) getAttributeType("genericExtendsMap")).getActualTypeArguments()[0]));
        assertFalse(TypeUtils.INSTANCE.equals(
                ((ParameterizedType) getAttributeType("genericCollection")).getActualTypeArguments()[0],
                ((ParameterizedType) getAttributeType("genericExtendsCollection")).getActualTypeArguments()[0]));
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
}

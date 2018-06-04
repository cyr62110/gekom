package fr.cvlaminck.gekom.core.type

import fr.cvlaminck.gekom.core.type.utils.TypeNameUtils
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.lang.reflect.Type

internal class TypeNameUtilsTest {

    @Test
    fun getTypeName() {
        // Class
        assertEquals("int", TypeNameUtils.getTypeName(Int::class.java))
        assertEquals("java.lang.String", TypeNameUtils.getTypeName(String::class.java))
        assertEquals("java.util.Collection", TypeNameUtils.getTypeName(Collection::class.java))

        // ParameterizeType & WildcardType
        assertEquals("java.util.Collection<java.lang.Integer>", TypeNameUtils.getTypeName(getAttributeType("concreteCollection")))
        assertEquals("java.util.Collection<?>", TypeNameUtils.getTypeName(getAttributeType("genericCollection")))

        assertEquals("java.lang.Integer[]", TypeNameUtils.getTypeName(getAttributeType("concreteArray")))
    }

    private fun getAttributeType(attribue: String): Type {
        val field = DummyClass::class.java.getDeclaredField(attribue)
        return field.genericType
    }

    @Suppress("unused")
    internal class DummyClass {
        var concreteArray: Array<Int>? = null
        var concreteCollection: Collection<Int>? = null
        var genericCollection: Collection<*>? = null
        var genericExtendsCollection: Collection<Int>? = null
    }
}
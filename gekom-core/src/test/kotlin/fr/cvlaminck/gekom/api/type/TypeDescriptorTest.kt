package fr.cvlaminck.gekom.api.type

import fr.cvlaminck.gekom.api.reflect.TypeDescriptor
import fr.cvlaminck.gekom.api.reflect.TypeDescriptorFactory
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TypeDescriptorTest {

    @Test
    fun testGeneric() {
        assertFalse(getAttributeType("concreteArray").generic)
        assertFalse(getAttributeType("concreteCollection").generic)

        assertTrue(getAttributeType("genericCollection").generic)
        assertTrue(getAttributeType("genericMap").generic)
    }

    private fun getAttributeType(attribute: String): TypeDescriptor {
        val field = DummyClass::class.java.getDeclaredField(attribute)
        return TypeDescriptorFactory().ofField(field)
    }

    @Suppress("unused")
    internal class DummyClass {
        var concreteArray: Array<Int>? = null
        var concreteCollection: Collection<Int>? = null
        var genericCollection: Collection<*>? = null
        var genericMap: Map<*, Int>? = null
    }
}
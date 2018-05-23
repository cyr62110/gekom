package fr.cvlaminck.gekom

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.lang.reflect.Field

class KotlinTypeTest {

    @Test
    fun testTypeOfFields() {
        var field: Field? = null

        field = DummyClass::class.java.getDeclaredField("concreteVar")
        assertEquals("java.lang.String", field.genericType.typeName)
    }

    class DummyClass {
        var concreteVar: String = ""
        var concreteArray: Array<String> = arrayOf()
        var concreteCollection: Collection<String> = arrayListOf()
        var genericExtendsCollection: Collection<String> = arrayListOf()
        var genericSuperCollection: Collection<*>? = null
    }
}

package fr.cvlaminck.gekom.api.type

import java.lang.reflect.Field
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class TypeDescriptor private constructor(
        val type: Type
) {

    /**
     * Returns true if a value of the provided [type] can be assignable
     */
    fun isAssignableFrom(type: Type): Boolean {
        // FIXME
        return false
    }

    companion object {

        /**
         * Returns a [TypeDescriptor] describing the type of the provided [field].
         */
        fun of(field: Field) = TypeDescriptor(field.genericType)

        /**
         * Returns a [TypeDescriptor] describing the type of the return value of the provided [method].
         */
        fun ofReturnValue(method: Method) = TypeDescriptor(method.genericReturnType)

        /**
         * Return a [TypeDescriptor] describing the type of the ith parameters of the provided [method].
         */
        fun ofParameter(method: Method, index: Int): TypeDescriptor = TypeDescriptor(method.genericParameterTypes[index])
    }
}

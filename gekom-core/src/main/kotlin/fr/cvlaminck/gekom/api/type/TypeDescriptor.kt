package fr.cvlaminck.gekom.api.type

import fr.cvlaminck.gekom.core.type.utils.TypeNameUtils
import java.lang.reflect.*
import java.util.*

class TypeDescriptor private constructor(
        val type: Type
) {

    /**
     * A string describing this type, including information about any type parameters.
     */
    val typeName
        get() = TypeNameUtils.getTypeName(type)

    /**
     * A type is generic if this [type] is a wildcard type or one of its parameterized type
     * is a wildcard type.
     */
    val generic: Boolean
        get() = when (type) {
            is WildcardType -> true
            is ParameterizedType -> ofActualTypeArguments(type).any { it.generic }
            else -> false
        }

    /**
     * Returns true if a value of the provided [type] can be assignable
     */
    fun isAssignableFrom(type: TypeDescriptor): Boolean {
        // FIXME
        return false
    }

    companion object {

        /**
         * Returns a [TypeDescriptor] describing the type of the provided class.
         */
        fun of(clazz: Class<*>) = TypeDescriptor(clazz)

        /**
         * Returns a [TypeDescriptor] describing the type of the provided [type].
         */
        fun of(type: Type) = TypeDescriptor(type)

        /**
         * Return a list of [TypeDescriptor] describing all the types of the type arguments for the provided [type].
         */
        fun ofActualTypeArguments(type: ParameterizedType): List<TypeDescriptor> = arrayListOf(*type.actualTypeArguments)
                .map { of(it) }

        /**
         * Returns a [TypeDescriptor] describing the type of the provided [field].
         */
        fun ofField(field: Field) = TypeDescriptor(field.genericType)

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

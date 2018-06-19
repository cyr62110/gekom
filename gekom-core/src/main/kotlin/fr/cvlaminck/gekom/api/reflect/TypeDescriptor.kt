package fr.cvlaminck.gekom.api.reflect

import fr.cvlaminck.gekom.core.reflect.utils.TypeNameUtils
import fr.cvlaminck.gekom.core.reflect.utils.TypeUtils
import java.lang.reflect.*

class TypeDescriptor internal constructor(
        val type: Type
) {

    val rawType: Class<*> = TypeUtils.getRawType(type)

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
            is ParameterizedType -> TypeDescriptorFactory().ofActualTypeArguments(type).any { it.generic }
            else -> false
        }

    /**
     * Returns true if a value of the provided [type] can be assignable
     *
     * @see [TypeUtils.isAssignableFrom]
     */
    fun isAssignableFrom(type: TypeDescriptor): Boolean = TypeUtils.isAssignableFrom(this.type, type.type)
}

package fr.cvlaminck.gekom.core.reflect.utils

import java.lang.reflect.GenericArrayType
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.lang.reflect.WildcardType
import java.util.*

/**
 * Provide a method returning the type information (including generic information) compatible with JDK 1.6.
 */
internal object TypeNameUtils {

    private val ARRAY_OF_OBJECT_CLASS = arrayOf(Object::class.java)

    /**
     * Returns a string describing this type, including information about any type parameters.
     */
    fun getTypeName(type: Type) = when (type) {
        is Class<*> -> getClassName(type)
        is GenericArrayType -> getGenericArrayType(type)
        is ParameterizedType -> getParameterizedTypeName(type)
        is WildcardType -> getWildcardTypeName(type)
        else -> type.toString()
    }

    private fun getClassName(clazz: Class<*>): String {
        if (clazz.isArray) {
            return clazz.componentType.name + "[]"
        } else {
            return clazz.name
        }
    }

    private fun getGenericArrayType(type: GenericArrayType): String {
        return getTypeName(type.genericComponentType) + "[]"
    }

    private fun getParameterizedTypeName(type: ParameterizedType): String {
        val sb = StringBuilder()
        if (type.ownerType != null) {
            sb.append(getTypeName(type.ownerType))
            sb.append("$")
        }
        sb.append(getTypeName(type.rawType))
        sb.append("<")
        sb.append(
                type.actualTypeArguments
                        .map(this::getTypeName)
                        .joinToString(", ")
        )
        sb.append(">")
        return sb.toString()
    }

    private fun getWildcardTypeName(type: WildcardType): String {
        val sb = StringBuilder("?")
        if (type.lowerBounds != null && !type.lowerBounds.isEmpty()) {
            sb.append(" super ")
            sb.append(
                    type.lowerBounds
                            .map(this::getTypeName)
                            .joinToString(", ")
            )
        }
        if (!Arrays.equals(type.upperBounds, ARRAY_OF_OBJECT_CLASS)) {
            sb.append(" extends ")
            sb.append(
                    type.upperBounds
                            .map(this::getTypeName)
                            .joinToString(", ")
            )
        }
        return sb.toString()
    }
}
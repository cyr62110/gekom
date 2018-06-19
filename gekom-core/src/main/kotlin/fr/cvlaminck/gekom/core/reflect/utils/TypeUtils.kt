package fr.cvlaminck.gekom.core.reflect.utils

import java.lang.reflect.GenericArrayType
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.lang.reflect.WildcardType

object TypeUtils {

    /**
    Returns the [Class] object representing the class or interface that declared this type.
     */
    fun getRawType(type: Type): Class<*> = when (type) {
        is Class<*> -> type
        is GenericArrayType -> getRawType(type.genericComponentType)
        is ParameterizedType -> getRawType(type.rawType)
        is WildcardType -> getRawType(type.upperBounds[0])
        else -> throw IllegalArgumentException("Cannot retrieve raw type of unsupported type '${type::class.java.name}'")
    }

    /**
     * Returns true if [t1] and [t2] are equal.
     *
     * @param t1 First type to compare.
     * @param t2 Second type to compare.
     * @return true if [t1] and [t2] are equals.
     */
    fun equals(t1: Type?, t2: Type?): Boolean {
        if (t1 === t2) {
            return true
        }
        if (t1 == null || t2 == null) {
            return (t1 == null && t2 == null)
        }
        return when (t1) {
            is Class<*> -> classEquals(t1, t2)
            is GenericArrayType -> genericArrayTypeEquals(t1, t2)
            is ParameterizedType -> parameterizedTypeEquals(t1, t2)
            is WildcardType -> wildcardTypeEquals(t1, t2)
            else -> throw IllegalArgumentException("Cannot retrieve compare unsupported type '${t1::class.java.name}'")
        }
    }

    /**
     * Returns true if [a1] and [a2] are equal. Two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal.
     *
     * @param a1 First array of [Type] to compare.
     * @param a2 Second array of [Type] to compare.
     */
    fun arrayEquals(a1: Array<Type>, a2: Array<Type>): Boolean {
        if (a1 === a2) {
            return true
        }
        var i = 0
        var result = true
        while (result && i < a1.size) {
            result = equals(a1[i], a2[i])
            i++
        }
        return result
    }

    private fun classEquals(t1: Class<*>, t2: Type): Boolean {
        if (t2 !is Class<*>) {
            return false
        }
        return t1 == t2
    }

    private fun genericArrayTypeEquals(t1: GenericArrayType, t2: Type): Boolean {
        if (t2 !is GenericArrayType) {
            return false
        }
        return equals(t1.genericComponentType, t2.genericComponentType)
    }

    private fun parameterizedTypeEquals(t1: ParameterizedType, t2: Type): Boolean {
        if (t2 !is ParameterizedType) {
            return false
        }
        return equals(t1.ownerType, t2.ownerType) &&
                equals(t1.rawType, t2.rawType) &&
                arrayEquals(t1.actualTypeArguments, t2.actualTypeArguments)
    }

    private fun wildcardTypeEquals(t1: WildcardType, t2: Type): Boolean {
        if (t2 !is WildcardType) {
            return false
        }
        return arrayEquals(t1.upperBounds, t2.upperBounds) &&
                arrayEquals(t2.lowerBounds, t2.lowerBounds)
    }

    /**
     * Returns true if a value of the [from] type can be assigned to a
     * - for classes, it will check if [to] is a superclass or a superinterface of [from].
     * - for parameterized type, it will check if raw types and parameters types are assignable to each other.
     * - for generic array type,
     * - for wildcard type,
     *
     * The actual implementation differs from the one of JDK to take into account generic type information.
     */
    fun isAssignableFrom(to: Type, from: Type): Boolean {
        if (to == from) {
            return true
        }
        return when (to) {
            is Class<*> -> isClassAssignableFrom(to, from)
            is ParameterizedType -> isParameterizedTypeAssignableFrom(to, from)
            else -> TODO()
        }
    }

    private fun isClassAssignableFrom(to: Class<*>, from: Type): Boolean {
        if (from !is Class<*>) {
            return false
        }
        return to.isAssignableFrom(from)
    }

    private fun isGenericArrayAssignableFrom(to: GenericArrayType, from: Type): Boolean {
        TODO()
    }

    private fun isParameterizedTypeAssignableFrom(to: ParameterizedType, from: Type): Boolean {
        if (from !is ParameterizedType) {
            return false
        }
        TODO()
    }

    private fun isWildcardTypeAssignableFrom(to: WildcardType, from: Type): Boolean {
        TODO()
    }
}
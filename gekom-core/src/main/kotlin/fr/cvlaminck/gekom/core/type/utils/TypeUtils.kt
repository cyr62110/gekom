package fr.cvlaminck.gekom.core.type.utils

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

internal object TypeUtils {

    fun getRawType(type: Type): Class<*> = when(type) {
        is Class<*> -> type
        else -> TODO()
    }

    /**
     * Returns true if a value of the [from] type can be assigned to a
     * - for classes, it will check if [to] is a superclass or a superinterface of [from].
     * - for parameterized type,
     * - for wildcard type, 
     *
     * The actual implementation differs from the one of JDK to take into account generic type information.
     */
    fun isAssignableFrom(to: Type, from: Type): Boolean {
        if (to == from) {
            return true
        }
        return when(to) {
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

    private fun isParameterizedTypeAssignableFrom(to: ParameterizedType, from: Type): Boolean {
        if (from !is ParameterizedType) {
            return false
        }
        TODO()
    }
}
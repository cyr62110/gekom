package fr.cvlaminck.gekom.api.mapper

import fr.cvlaminck.gekom.api.reflect.TypeDescriptor

/**
 *
 */
data class TypedValue<T>(
        val type: TypeDescriptor,
        val value: T
)

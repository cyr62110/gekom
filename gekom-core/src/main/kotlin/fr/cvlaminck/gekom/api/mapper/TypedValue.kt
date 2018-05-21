package fr.cvlaminck.gekom.api.mapper

import fr.cvlaminck.gekom.api.type.TypeDescriptor

/**
 *
 */
data class TypedValue<T>(
        val type: TypeDescriptor<T>,
        val value: T
)

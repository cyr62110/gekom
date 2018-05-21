package fr.cvlaminck.gekom.core.type

import fr.cvlaminck.gekom.api.type.TypeModel
import fr.cvlaminck.gekom.api.type.TypeProperty

internal data class DefaultTypeModel<T: Any>(
        override val type: Class<T>,
        override val properties: Collection<TypeProperty<Any, Any>>
) : TypeModel<T>

package fr.cvlaminck.gekom.api.type

import fr.cvlaminck.gekom.reflect.TypeDescriptor

/**
 * A [TypeModel] describe the properties that can be mapped for a given class.
 *
 *
 *
 * @since 1.0.0
 */
interface TypeModel {

    /**
     * Returns the type from which we have generated the model.
     *
     * @since 1.0.0
     */
    val type: TypeDescriptor

    /**
     * Return the tag associated to this model of the [type].
     *
     * @since 1.0.0
     */
    val tag: String?

    /**
     * Returns the collection of properties.
     *
     * @since 1.0.0
     */
    val properties: Collection<TypeProperty<Any, Any>>

    /**
     *
     */
    fun getPropertyByNameOrAlias(nameOrAlias: String): TypeProperty<Any, Any>?
}

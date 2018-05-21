package fr.cvlaminck.gekom.api.type

/**
 * A [TypeModel] describe the properties that can be mapped for a given class.
 */
interface TypeModel<T : Any> {

    /**
     * Returns the type from which we have generated the model.
     */
    val type: Class<T>

    /**
     * Returns the collection of properties.
     */
    val properties: Collection<TypeProperty<Any, Any>>
}

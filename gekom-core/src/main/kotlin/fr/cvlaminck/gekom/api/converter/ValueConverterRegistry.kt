package fr.cvlaminck.gekom.api.converter

import fr.cvlaminck.gekom.api.reflect.TypeDescriptor

/**
 *
 */
interface ValueConverterRegistry {

    /**
     * Returns a converter supporting the conversion of [inputType] into the [outputType].
     *
     * @param inputType
     * @param outputType
     * @return a converter supporting the conversion of [inputType] into the [outputType].
     */
    fun getConverter(inputType: TypeDescriptor, outputType: TypeDescriptor): ValueConverter

    /**
     * Returns a converter of the provided [type].
     *
     * @param type Class of the converter to get.
     * @return a converter of the provided [type].
     */
    fun <T : ValueConverter> getConverter(type: Class<T>): T

    /**
     * Register a new converter in this registry.
     *
     * @param converter [ValueConverter] to register.
     */
    fun registerConverter(converter: ValueConverter)

    /**
     * Register a new converter factory in this registry.
     *
     * @param factory [ValueConverterFactory] to register.
     */
    fun registerConverterFactory(factory: ValueConverterFactory)
}

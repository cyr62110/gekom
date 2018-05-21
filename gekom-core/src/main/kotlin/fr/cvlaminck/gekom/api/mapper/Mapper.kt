package fr.cvlaminck.gekom.api.mapper

/**
 * A mapper copies the values in the [Input] object into the [Output] object.
 *
 * A common example of mapping in JPA is the conversion of the ResultSet into a business object.
 *
 * @param Input Input class we are mapping from.
 * @param Output Output class we are mapping to.
 */
interface Mapper<Input, Output> {

    /**
     * Map the values from the [input] object to the [output] object.
     *
     * @param input Objects providing the values.
     * @param output Object into which we will inject the values.
     */
    fun map(input: Input, output: Output): Output

    /**
     * Instantiate the [Output] and map the values from the [input] object into this newly created
     * output object.
     */
    fun map(input: Input): Output
}

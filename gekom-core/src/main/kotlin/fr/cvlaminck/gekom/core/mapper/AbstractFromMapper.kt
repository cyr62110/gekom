package fr.cvlaminck.gekom.core.mapper

import fr.cvlaminck.gekom.api.converter.ValueConverterRegistry
import fr.cvlaminck.gekom.api.mapper.Mapper

class AbstractFromMapper<Input, Output> (
        converterRegistry: ValueConverterRegistry
) : Mapper<Input, Output> {

    override fun map(input: Input, output: Output): Output {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun map(input: Input): Output {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

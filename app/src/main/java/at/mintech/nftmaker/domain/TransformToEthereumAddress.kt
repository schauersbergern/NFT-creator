package at.mintech.nftmaker.domain

import at.mintech.nftmaker.helper.config.ADDRESS_PREFIXES
import at.mintech.nftmaker.helper.config.ADDRESS_REGEX
import at.mintech.nftmaker.helper.config.INVALID_ADDRESS
import at.mintech.nftmaker.helper.usecase.UseCase

class TransformToEthereumAddress : UseCase<String, String>() {
    override fun run(params: String): Result<String> = Result.runCatching {

        var result = ""

        ADDRESS_PREFIXES.map {
            result = params.replaceFirst(it, "")
        }

        if (!result.matches(ADDRESS_REGEX.toRegex())) {
            error(INVALID_ADDRESS)
        }

        result
    }
}
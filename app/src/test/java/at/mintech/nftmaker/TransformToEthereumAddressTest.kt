package at.mintech.nftmaker

import at.mintech.nftmaker.helper.config.component1
import at.mintech.nftmaker.helper.config.component2
import at.mintech.nftmaker.domain.TransformToEthereumAddress
import at.mintech.nftmaker.utils.BaseUnit5Test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import strikt.api.expectThat
import strikt.assertions.isNotNull
import strikt.assertions.isNull

@ExperimentalCoroutinesApi
class TransformToEthereumAddressTest : BaseUnit5Test() {

    @ParameterizedTest
    @MethodSource("addressSource")
    fun `Ethereum Address is transformed correctly`(address: String, valid: Boolean) {
        val (_, error) = TransformToEthereumAddress().run(address)
        if (valid) {
            expectThat(error).isNull()
        } else {
            expectThat(error).isNotNull()
        }
    }

    companion object {
        @JvmStatic
        fun addressSource() = listOf(
            Arguments.of("ethereum:0x86682Ab52B1319dB3d7b08D9AD2357D6d4905351", true),
            Arguments.of("0x86682Ab52B1319dB3d7b08D9AD2357D6d4905351", true),
            Arguments.of("abcd1234", false),
            Arguments.of("abc./281$@", false)
        )
    }
}
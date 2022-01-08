package at.mintech.nftmaker.ui.displayNfts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.mintech.nftmaker.domain.GetImageBmp
import at.mintech.nftmaker.domain.GetPersistedNfts
import at.mintech.nftmaker.domain.entities.DisplayNft
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

data class DisplayNftsState(
    val displayNfts: List<DisplayNft> = listOf()
)

sealed class DisplayNftsSideEffects {
    object ContentLoading : DisplayNftsSideEffects()
    object ContentLoaded : DisplayNftsSideEffects()
}

internal class DisplayNftsViewModel(
    private val getPersistedNfts: GetPersistedNfts,
    private val getImageBmp: GetImageBmp
) : ViewModel(), ContainerHost<DisplayNftsState, DisplayNftsSideEffects> {

    override val container =
        container<DisplayNftsState, DisplayNftsSideEffects>(DisplayNftsState())

    var loadJob: Job? = null


    fun fetchNfts() = intent {
        loadJob?.cancel()
        loadJob = viewModelScope.launch {
            postSideEffect(DisplayNftsSideEffects.ContentLoading)
            getPersistedNfts(Unit).onSuccess {

                val displayNfts = mutableListOf<DisplayNft>()
                for(nft in it) {
                    when (nft.fileType) {
                        "jpeg" , "jpg", "png" -> {
                            val bmp = getImageBmp(nft.nftUrl).getOrNull()
                            displayNfts.add(DisplayNft(bmp, nft.fileType))
                        }
                        else -> displayNfts.add(DisplayNft(null, nft.fileType))
                    }
                }

                reduce {
                    state.copy(displayNfts = displayNfts)
                }
                postSideEffect(DisplayNftsSideEffects.ContentLoaded)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        loadJob?.cancel()
    }

}
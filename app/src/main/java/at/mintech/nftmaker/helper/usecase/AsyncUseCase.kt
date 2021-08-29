package at.mintech.nftmaker.helper.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal abstract class AsyncUseCase<out R, in P>(var dispatcher: CoroutineDispatcher = Dispatchers.IO) {
    abstract suspend fun run(params: P): Result<R>
    suspend operator fun invoke(params: P): Result<R> {
        return withContext(dispatcher) { run(params) }
    }
}
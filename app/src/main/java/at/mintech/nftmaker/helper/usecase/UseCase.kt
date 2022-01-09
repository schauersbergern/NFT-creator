package at.mintech.nftmaker.helper.usecase

abstract class UseCase<out R, in Params> {
    abstract fun run(params: Params): Result<R>
    operator fun invoke(params: Params) = run(params)
}
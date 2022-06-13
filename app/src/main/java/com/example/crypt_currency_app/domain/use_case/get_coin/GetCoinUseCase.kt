package com.example.crypt_currency_app.domain.use_case.get_coin

import com.example.crypt_currency_app.common.Resource
import com.example.crypt_currency_app.data.remote.dto.toCoinDetail
import com.example.crypt_currency_app.domain.model.CoinDetail
import com.example.crypt_currency_app.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coins = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coins)) // forwards to vm
        } catch (e: HttpException) {
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An expected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<CoinDetail>("Could not reach server. Check your internet connection"))
        }
    }
}
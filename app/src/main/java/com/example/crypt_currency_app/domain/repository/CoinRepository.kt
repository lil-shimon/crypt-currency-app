package com.example.crypt_currency_app.domain.repository

import com.example.crypt_currency_app.data.remote.dto.CoinDetailDto
import com.example.crypt_currency_app.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}
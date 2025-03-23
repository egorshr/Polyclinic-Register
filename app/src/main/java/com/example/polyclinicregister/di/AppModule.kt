package com.example.polyclinicregister.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import org.koin.dsl.module
import io.ktor.serialization.kotlinx.json.json

fun appModule() = listOf(
    provideHttpClientModule
)


val provideViewModelModule = module {

}

val provideHttpClientModule = module {
    single {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json()
            }
        }
    }
}
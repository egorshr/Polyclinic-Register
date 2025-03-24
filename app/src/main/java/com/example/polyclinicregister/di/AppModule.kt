package com.example.polyclinicregister.di

import com.example.polyclinicregister.data.remote.EmployeeApi
import com.example.polyclinicregister.data.remote.ServiceApi
import com.example.polyclinicregister.data.remote.VisitApi
import com.example.polyclinicregister.data.repository.EmployeeRepoImpl
import com.example.polyclinicregister.data.repository.ServiceRepoImpl
import com.example.polyclinicregister.data.repository.VisitRepoImpl
import com.example.polyclinicregister.domain.repository.EmployeeRepo
import com.example.polyclinicregister.domain.repository.ServiceRepo
import com.example.polyclinicregister.domain.repository.VisitRepo
import com.example.polyclinicregister.domain.usecases.employee.EmployeeUseCases
import com.example.polyclinicregister.domain.usecases.employee.GetEmployees
import com.example.polyclinicregister.presentation.employee.EmployeeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import org.koin.dsl.module
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf

fun appModule() = listOf(
    provideHttpClientModule,
    provideApiModule,
    provideRepositoryModule,
    provideEmployeeUseCases,
    provideViewModelModule
)


val provideViewModelModule = module {
    viewModelOf(::EmployeeViewModel)
}

val provideEmployeeUseCases = module {
    singleOf(::EmployeeUseCases)
    singleOf(::GetEmployees)
}

val provideApiModule = module {
    singleOf(::VisitApi)
    singleOf(::EmployeeApi)
    singleOf(::ServiceApi)
}

val provideRepositoryModule = module {
    singleOf(::VisitRepoImpl) { bind<VisitRepo>() }
    singleOf(::EmployeeRepoImpl) { bind<EmployeeRepo>() }
    singleOf(::ServiceRepoImpl) { bind< ServiceRepo>() }
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
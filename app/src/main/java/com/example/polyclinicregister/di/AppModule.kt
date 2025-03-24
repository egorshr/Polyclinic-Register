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
import com.example.polyclinicregister.domain.usecases.employee.DeleteEmployee
import com.example.polyclinicregister.domain.usecases.employee.EmployeeUseCases
import com.example.polyclinicregister.domain.usecases.employee.GetEmployees
import com.example.polyclinicregister.domain.usecases.service.DeleteService
import com.example.polyclinicregister.domain.usecases.service.GetServices
import com.example.polyclinicregister.domain.usecases.service.ServiceUseCases
import com.example.polyclinicregister.domain.usecases.visit.DeleteVisit
import com.example.polyclinicregister.domain.usecases.visit.GetVisits
import com.example.polyclinicregister.domain.usecases.visit.VisitUseCases
import com.example.polyclinicregister.presentation.employee.EmployeeViewModel
import com.example.polyclinicregister.presentation.service.ServiceViewModel
import com.example.polyclinicregister.presentation.visit.VisitViewModel
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
    provideViewModelModule,
    provideVisitUseCases,
    provideServiceUseCases
)


val provideViewModelModule = module {
    viewModelOf(::EmployeeViewModel)
    viewModelOf(::VisitViewModel)
    viewModelOf(::ServiceViewModel)

}

val provideEmployeeUseCases = module {
    singleOf(::EmployeeUseCases)
    singleOf(::GetEmployees)
    singleOf(::DeleteEmployee)
}

val provideVisitUseCases = module {
    singleOf(::VisitUseCases)
    singleOf(::GetVisits)
    singleOf(::DeleteVisit)
}

val provideServiceUseCases = module {
    singleOf(::ServiceUseCases)
    singleOf(::GetServices)
    singleOf(::DeleteService)
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
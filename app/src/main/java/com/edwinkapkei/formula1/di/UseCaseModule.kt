package com.edwinkapkei.formula1.di

import com.edwinkapkei.formula1.domain.usecase.GetCurrentConstructorsUseCase
import com.edwinkapkei.formula1.domain.usecase.GetCurrentDriverPhotosUseCase
import com.edwinkapkei.formula1.domain.usecase.GetCurrentDriversUseCase
import com.edwinkapkei.formula1.domain.usecase.GetCurrentScheduleUseCase
import com.edwinkapkei.formula1.domain.usecase.GetCurrentTeamCarPhotosUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single {
        GetCurrentScheduleUseCase(mainRepository = get())
    }
    single {
        GetCurrentDriversUseCase(mainRepository = get(), driverPhotosUseCase = get())
    }
    single {
        GetCurrentConstructorsUseCase(mainRepository = get(), teamCarPhotosUseCase = get())
    }
    single {
        GetCurrentDriverPhotosUseCase(mainRepository = get())
    }
    single {
        GetCurrentTeamCarPhotosUseCase(mainRepository = get())
    }
}

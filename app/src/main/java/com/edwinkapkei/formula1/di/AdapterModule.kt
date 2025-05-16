package com.edwinkapkei.formula1.di

import com.edwinkapkei.formula1.views.constructors.adapter.ConstructorsAdapter
import com.edwinkapkei.formula1.views.drivers.adapter.DriversAdapter
import com.edwinkapkei.formula1.views.schedule.adapter.ScheduleAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {
    @Provides
    @Singleton
    fun provideScheduleAdapter(): ScheduleAdapter {
        return ScheduleAdapter()
    }

    @Provides
    @Singleton
    fun provideDriversAdapter(): DriversAdapter {
        return DriversAdapter()
    }

    @Provides
    @Singleton
    fun provideConstructorAdapter(): ConstructorsAdapter {
        return ConstructorsAdapter()
    }
}

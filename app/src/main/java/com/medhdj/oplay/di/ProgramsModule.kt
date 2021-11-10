package com.medhdj.oplay.di

import com.medhdj.business.programs.SearchProgramsRepository
import com.medhdj.business.programs.SearchProgramsUseCase
import com.medhdj.business.programs.SearchProgramsUseCaseImpl
import com.medhdj.data.common.OcsApi
import com.medhdj.data.programs.ProgramsFeedDataSource
import com.medhdj.data.programs.SearchProgramsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ProgramsModule {

    // feed
    @Provides
    fun provideSearchProgramsUseCase(searchProgramsRepository: SearchProgramsRepository):
            SearchProgramsUseCase = SearchProgramsUseCaseImpl(searchProgramsRepository)

    @Provides
    fun provideSearchProgramsRepository(programsFeedDataSource: ProgramsFeedDataSource):
            SearchProgramsRepository = SearchProgramsRepositoryImpl(programsFeedDataSource)

    @Provides
    fun provideProgramsFeedDataSource(ocsApi: OcsApi):
            ProgramsFeedDataSource = ProgramsFeedDataSource(ocsApi)
}

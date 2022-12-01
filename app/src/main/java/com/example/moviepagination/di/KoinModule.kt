package com.example.moviepagination.di

import com.example.moviepagination.domain.repository.ILocalRepo
import com.example.moviepagination.domain.repository.IRemoteRepo
import com.example.moviepagination.data.repository.LocalRepoImpl
import com.example.moviepagination.data.repository.RemoteRepoImpl
import com.example.moviepagination.data.network.ApiFactory
import com.example.moviepagination.data.database.MovieDataBase
import com.example.moviepagination.domain.usecases.*
import com.example.moviepagination.presentation.*
import com.example.moviepagination.presentation.viewmodel.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
//    single { Room.databaseBuilder(androidContext(), MovieDataBase::class.java, "movie.db").fallbackToDestructiveMigration().build() }
    single { MovieDataBase.getInstance(androidContext()).movieItemListDao}
    single { ApiFactory.api }
    single<ILocalRepo> { LocalRepoImpl(movieItemListDao = get()) }
    single<IRemoteRepo> { RemoteRepoImpl(apiService = get()) }
    single { GetComingSoonMovieUseCase(repository = get())}
    single { GetMovieNowInTheatreUseCase(repository = get())}
    single { GetMostPopularTVsUseCase(repository = get())}
    single { GetMostPopularMoviesUseCase(repository = get())}
    single { GetAllSavedMoviesUseCase(repository = get()) }
    single { GetMovieByIdUseCase(repository = get())}
    single { GetMoviesTrailerUseCase(repository = get())}
    single { GetActorInfoByIdUseCase(repository = get())}
    single { GetSearchListUseCase(repository = get())}
    single { GetTop250TvsUseCase(repository = get())}
    single { GetTop250MoviesUseCase(repository = get())}
    single { SaveMovieToMyListUseCase(repository = get())}
    single { DeleteMovieFromMyList(repository = get())}
    single { GetSavedMovieByIdUseCase(repository = get())}
}

val movieListScreen = module {
    scope<MovieListFragment> {
        viewModel { MovieListViewModel(
            getComingSoonUseCase = get(),
            getNowInTheatreUseCase = get(),
            getMostPopularTVsUseCase = get(),
            getPopularMoviesUseCase = get()
        ) }
    }
}

val movieInfoScreen = module {
    scope<MovieInfoFragment> {
        viewModel { MovieInfoViewModel(getMovieByIdUseCase = get(), getMoviesTrailerUseCase = get(),
        saveMovieToMyListUseCase = get(), deleteMovieFromMyList = get(), getSavedMovieByIdUseCase = get()) }
    }
}

val actorInfoScreen = module {
    scope<ActorInfoFragment> {
        viewModel { ActorInfoViewModel(getActorInfoByIdUseCase = get()) }
    }
}

val searchResultScreen = module {
    scope<SearchFragment> {
        viewModel { SearchViewModel(getSearchListUseCase = get()) }
    }
}

val top250Screen = module {
    scope<Top250Fragment> {
        viewModel { Top250ViewModel(getTop250MoviesUseCase = get(), getTop250TvsUseCase = get()) }
    }
}

val savedMovieListScreen = module {
    scope<SavedMovieListFragment> {
        viewModel { SavedMovieListViewModel(getAllSavedMoviesUseCase = get()) }
    }
}
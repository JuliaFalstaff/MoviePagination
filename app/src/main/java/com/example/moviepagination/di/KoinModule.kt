package com.example.moviepagination.di

import com.example.moviepagination.domain.repository.ILocalRepo
import com.example.moviepagination.domain.repository.IRemoteRepo
import com.example.moviepagination.data.repository.LocalRepoImpl
import com.example.moviepagination.data.repository.RemoteRepoImpl
import com.example.moviepagination.data.network.RetrofitImpl
import com.example.moviepagination.data.database.MovieDataBase
import com.example.moviepagination.presentation.*
import com.example.moviepagination.presentation.viewmodel.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
//    single { Room.databaseBuilder(androidContext(), MovieDataBase::class.java, "movie.db").fallbackToDestructiveMigration().build() }
    single { MovieDataBase.getInstance(androidContext()).movieItemListDao}
    single<ILocalRepo> { LocalRepoImpl(movieItemListDao = get()) }
    single<IRemoteRepo> { RemoteRepoImpl(apiService = RetrofitImpl().api) }

}

val movieListScreen = module {
    scope<MovieListFragment> {
        viewModel { MovieListViewModel(remoteRepo = get(), localRepo = get()) }
    }
}

val movieInfoScreen = module {
    scope<MovieInfoFragment> {
        viewModel { MovieInfoViewModel(remoteRepo = get()) }
    }
}

val actorInfoScreen = module {
    scope<ActorInfoFragment> {
        viewModel { ActorInfoViewModel(remoteRepo = get()) }
    }
}

val searchResultScreen = module {
    scope<SearchFragment> {
        viewModel { SearchViewModel(remoteRepo = get()) }
    }
}

val top250Screen = module {
    scope<Top250Fragment> {
        viewModel { Top250ViewModel(remoteRepo = get(), localRepo = get()) }
    }
}
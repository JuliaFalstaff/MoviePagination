package com.example.moviepagination.di

import androidx.room.Room
import com.example.moviepagination.model.repository.ILocalRepo
import com.example.moviepagination.model.repository.IRemoteRepo
import com.example.moviepagination.model.repository.LocalRepoImpl
import com.example.moviepagination.model.repository.RemoteRepoImpl
import com.example.moviepagination.model.retrofit.RetrofitImpl
import com.example.moviepagination.model.room.MovieDataBase
import com.example.moviepagination.ui.*
import com.example.moviepagination.viewmodel.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
//    single { Room.databaseBuilder(androidContext(), MovieDataBase::class.java, "movie.db").fallbackToDestructiveMigration().build() }
    single {MovieDataBase.getInstance(androidContext()).movieItemListDao}
    single<ILocalRepo> {LocalRepoImpl(movieItemListDao = get())}
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
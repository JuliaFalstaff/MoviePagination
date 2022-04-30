package com.example.moviepagination.di

import androidx.room.Room
import com.example.moviepagination.model.repository.ILocalRepo
import com.example.moviepagination.model.repository.IRemoteRepo
import com.example.moviepagination.model.repository.LocalRepoImpl
import com.example.moviepagination.model.repository.RemoteRepoImpl
import com.example.moviepagination.model.retrofit.RetrofitImpl
import com.example.moviepagination.model.room.MovieDataBase
import com.example.moviepagination.ui.MovieInfoFragment
import com.example.moviepagination.ui.MovieListFragment
import com.example.moviepagination.viewmodel.MovieInfoViewModel
import com.example.moviepagination.viewmodel.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), MovieDataBase::class.java, "movie.db").fallbackToDestructiveMigration().build() }
    single {get<MovieDataBase>().movieItemListDao}
    single<ILocalRepo> {LocalRepoImpl(movieItemListDao = get())}
    single<IRemoteRepo> { RemoteRepoImpl(apiService = RetrofitImpl().api) }

}

val movieListScreen = module {
    scope<MovieListFragment> {
        viewModel { MovieListViewModel(remoteRepo = get()) }
    }
}

val movieInfoScreen = module {
    scope<MovieInfoFragment> {
        viewModel { MovieInfoViewModel(remoteRepo = get()) }
    }
}
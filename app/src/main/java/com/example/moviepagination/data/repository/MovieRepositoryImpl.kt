//package com.example.moviepagination.data.repository
//
//import androidx.lifecycle.LiveData
//import com.example.moviepagination.BuildConfig
//import com.example.moviepagination.data.database.MovieItemListDao
//import com.example.moviepagination.data.network.ApiService
//import com.example.moviepagination.data.network.model.MovieItemListDto
//import com.example.moviepagination.domain.entities.MovieItemList
//import com.example.moviepagination.domain.entities.castInfo.ActorInfo
//import com.example.moviepagination.domain.entities.info.MovieInfo
//import com.example.moviepagination.domain.entities.info.YouTubeTrailer
//import com.example.moviepagination.domain.entities.search.SearchResult
//import com.example.moviepagination.domain.repository.ILocalRepo
//import com.example.moviepagination.domain.repository.IMovieRepository
//import com.example.moviepagination.domain.repository.IRemoteRepo
//import com.example.moviepagination.utils.convertFromEntityToMovieList
//import io.reactivex.rxjava3.core.Single
//
//class MovieRepositoryImpl(
//    private val localRepo: ILocalRepo,
//    private val remoteRepo: IRemoteRepo
//    ): IMovieRepository {
//
//    override fun getMovieNowInTheatre(): Single<MovieItemList> {
//        return apiService.getActiveMoviesInTheatres(MOVIE_API_KEY)
//    }
//
//    override fun getMovieByIdFromServer(movieId: String): Single<MovieInfo> {
//        return apiService.getMovieById(EN_LANG, movieId, MOVIE_API_KEY)
//    }
//
//    override fun getComingSoonMoviesFromServer(): Single<MovieItemList> {
//        return apiService.getComingSoonMovies(EN_LANG, MOVIE_API_KEY)
//    }
//
//    override fun getTOP250Movies(): Single<MovieItemList> {
//        return apiService.getTOP250Movies(MOVIE_API_KEY)
//    }
//
//    override fun getMostPopularMovies(): Single<MovieItemList> {
//        return apiService.getMostPopularMovies(EN_LANG, MOVIE_API_KEY)
//    }
//
//    override fun getMostPopularTVs(): Single<MovieItemList> {
//        return apiService.getMostPopularTVs(EN_LANG, MOVIE_API_KEY)
//    }
//
//    override fun getActorInfoById(actorId: String): Single<ActorInfo> {
//        return apiService.getActorInfoById(EN_LANG, MOVIE_API_KEY, actorId)
//    }
//
//    override fun getSearchList(expression: String): Single<SearchResult> {
//        return apiService.getSearchList(MOVIE_API_KEY, expression)
//    }
//
//    override fun getTOP250TVs(): Single<MovieItemList> {
//        return apiService.getTOP250TVs(MOVIE_API_KEY)
//    }
//
//    override fun getMovieTrailerById(movieId: String): Single<YouTubeTrailer> {
//        return apiService.getMovieTrailerById(movieId, MOVIE_API_KEY)
//    }
//
//    override fun saveMovieList(list: MovieItemList) {
//        movieItemListDao.insertAllMovieList(MovieItemListDto(0, list.items))
//    }
//
//    override fun getAllMovieList(): Single<MovieItemList> {
//        return movieItemListDao.getMovieList().map {
//            convertFromEntityToMovieList(it)
//        }
//    }
//
//
//    companion object {
//        private const val MOVIE_API_KEY = BuildConfig.IMDb_API_KEY
//        private const val EN_LANG = "en"
//        private const val RU_LANG = "ru"
//    }
//}
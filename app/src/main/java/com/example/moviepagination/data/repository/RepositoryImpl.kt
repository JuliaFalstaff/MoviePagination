package com.example.moviepagination.data.repository

import com.example.moviepagination.BuildConfig
import com.example.moviepagination.data.database.MovieItemListDao
import com.example.moviepagination.data.mapper.*
import com.example.moviepagination.data.network.ApiService
import com.example.moviepagination.domain.entities.MovieItemList
import com.example.moviepagination.domain.entities.castInfo.ActorInfo
import com.example.moviepagination.domain.entities.info.MovieInfo
import com.example.moviepagination.domain.entities.info.YouTubeTrailer
import com.example.moviepagination.domain.entities.search.SearchResult
import com.example.moviepagination.domain.repository.IRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RepositoryImpl(
    private val apiService: ApiService,
    private val movieItemListDao: MovieItemListDao
) : IRepository {

    private val movieItemListMapper = MovieItemListMapper()
    private val movieInfoMapper = MovieInfoMapper()
    private val actorMapper = ActorMapper()
    private val youtubeTrailerMapper = YoutubeTrailerMapper()
    private val searchResultMapper = SearchResultMapper()

    override suspend fun getMovieNowInTheatre() = flow {
        val list = apiService.getActiveMoviesInTheatres(MOVIE_API_KEY)
        emit(movieItemListMapper.mapMovieItemListDtoToEntity(list))
    }.flowOn(Dispatchers.IO)

    override suspend fun getMovieByIdFromServer(movieId: String) = flow {
        val movie = apiService.getMovieById(EN_LANG, movieId, MOVIE_API_KEY)
        emit(movieInfoMapper.mapMovieInfoDtoToEntity(movie))
    }.flowOn(Dispatchers.IO)

    override suspend fun getComingSoonMoviesFromServer() = flow {
        val list = apiService.getComingSoonMovies(EN_LANG, MOVIE_API_KEY)
        emit(movieItemListMapper.mapMovieItemListDtoToEntity(list))
    }.flowOn(Dispatchers.IO)

    override suspend fun getTOP250Movies() = flow {
        val list = apiService.getTOP250Movies(MOVIE_API_KEY)
        emit(movieItemListMapper.mapMovieItemListDtoToEntity(list))
    }.flowOn(Dispatchers.IO)

    override suspend fun getMostPopularMovies() = flow {
        val list = apiService.getMostPopularMovies(EN_LANG, MOVIE_API_KEY)
        emit(movieItemListMapper.mapMovieItemListDtoToEntity(list))
    }.flowOn(Dispatchers.IO)

    override suspend fun getMostPopularTVs() = flow {
        val list = apiService.getMostPopularTVs(EN_LANG, MOVIE_API_KEY)
        emit(movieItemListMapper.mapMovieItemListDtoToEntity(list))
    }.flowOn(Dispatchers.IO)

    override suspend fun getActorInfoById(actorId: String) = flow {
        val actor = apiService.getActorInfoById(EN_LANG, MOVIE_API_KEY, actorId)
        emit(actorMapper.mapActorInfoDtoToEntity(actor))
    }.flowOn(Dispatchers.IO)

    override suspend fun getSearchList(expression: String) = flow {
        val search = apiService.getSearchList(MOVIE_API_KEY, expression)
        emit(searchResultMapper.mapSearchResultDtoToEntity(search))
    }.flowOn(Dispatchers.IO)

    override suspend fun getTOP250TVs() = flow {
        val list = apiService.getTOP250TVs(MOVIE_API_KEY)
        emit(movieItemListMapper.mapMovieItemListDtoToEntity(list))
    }.flowOn(Dispatchers.IO)

    override suspend fun getMovieTrailerById(movieId: String) = flow {
        val trailer = apiService.getMovieTrailerById(movieId, MOVIE_API_KEY)
        emit(youtubeTrailerMapper.mapYoutubeTrailerDtoToEntity(trailer))
    }.flowOn(Dispatchers.IO)

    override suspend fun saveMovie(movie: MovieInfo) {
        return movieItemListDao.insertMovieToMyList(
            movieInfoMapper.mapMovieInfoEntityToDbModel(
                movie
            )
        )
    }

    override suspend fun getAllSavedMovieList(): List<MovieInfo> {
        val list = movieItemListDao.getMovieList()
        return movieInfoMapper.mapMovieInfoListDbModelToListEntity(list)
    }

    override suspend fun getSavedMovieInfo(movieId: String?): MovieInfo {
        val movie = movieItemListDao.getSavedMovieInfo(movieId)
        return movieInfoMapper.mapMovieInfoDbModelToEntity(movie)
    }

    override suspend fun deleteMovieFromMyList(id: String?) {
        return movieItemListDao.deleteMovieFromMyList(id)
    }

    companion object {
        private const val MOVIE_API_KEY = BuildConfig.IMDb_API_KEY
        private const val EN_LANG = "en"
    }
}
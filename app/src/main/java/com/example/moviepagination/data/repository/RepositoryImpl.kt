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

class RepositoryImpl(
    private val apiService: ApiService,
    private val movieItemListDao: MovieItemListDao
) : IRepository {

    private val movieItemListMapper = MovieItemListMapper()
    private val movieInfoMapper = MovieInfoMapper()
    private val actorMapper = ActorMapper()
    private val youtubeTrailerMapper = YoutubeTrailerMapper()
    private val searchResultMapper = SearchResultMapper()

    override suspend fun getMovieNowInTheatre(): MovieItemList {
        val list = apiService.getActiveMoviesInTheatres(MOVIE_API_KEY)
        return movieItemListMapper.mapMovieItemListDtoToEntity(list)
    }

    override suspend fun getMovieByIdFromServer(movieId: String): MovieInfo {
        val movie = apiService.getMovieById(EN_LANG, movieId, MOVIE_API_KEY)
        return movieInfoMapper.mapMovieInfoDtoToEntity(movie)
    }

    override suspend fun getComingSoonMoviesFromServer(): MovieItemList {
        val list = apiService.getComingSoonMovies(EN_LANG, MOVIE_API_KEY)
        return movieItemListMapper.mapMovieItemListDtoToEntity(list)
    }

    override suspend fun getTOP250Movies(): MovieItemList {
        val list = apiService.getTOP250Movies(MOVIE_API_KEY)
        return movieItemListMapper.mapMovieItemListDtoToEntity(list)
    }

    override suspend fun getMostPopularMovies(): MovieItemList {
        val list = apiService.getMostPopularMovies(EN_LANG, MOVIE_API_KEY)
        return movieItemListMapper.mapMovieItemListDtoToEntity(list)
    }

    override suspend fun getMostPopularTVs(): MovieItemList {
        val list = apiService.getMostPopularTVs(EN_LANG, MOVIE_API_KEY)
        return movieItemListMapper.mapMovieItemListDtoToEntity(list)
    }

    override suspend fun getActorInfoById(actorId: String): ActorInfo {
        val actor = apiService.getActorInfoById(EN_LANG, MOVIE_API_KEY, actorId)
        return actorMapper.mapActorInfoDtoToEntity(actor)
    }

    override suspend fun getSearchList(expression: String): SearchResult {
        val search = apiService.getSearchList(MOVIE_API_KEY, expression)
        return searchResultMapper.mapSearchResultDtoToEntity(search)
    }

    override suspend fun getTOP250TVs(): MovieItemList {
        val list = apiService.getTOP250TVs(MOVIE_API_KEY)
        return movieItemListMapper.mapMovieItemListDtoToEntity(list)
    }

    override suspend fun getMovieTrailerById(movieId: String): YouTubeTrailer {
        val trailer = apiService.getMovieTrailerById(movieId, MOVIE_API_KEY)
        return youtubeTrailerMapper.mapYoutubeTrailerDtoToEntity(trailer)
    }

    override suspend fun saveMovieList(list: MovieItemList) {
        return movieItemListDao.insertAllMovieList(movieItemListMapper.mapListEntityToDbModel(list))
    }

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
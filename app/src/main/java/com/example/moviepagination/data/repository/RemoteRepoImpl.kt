package com.example.moviepagination.data.repository

import com.example.moviepagination.BuildConfig
import com.example.moviepagination.data.mapper.*
import com.example.moviepagination.data.network.ApiService
import com.example.moviepagination.domain.entities.MovieItemList
import com.example.moviepagination.domain.entities.castInfo.ActorInfo
import com.example.moviepagination.domain.entities.info.MovieInfo
import com.example.moviepagination.domain.entities.info.YouTubeTrailer
import com.example.moviepagination.domain.entities.search.SearchResult
import com.example.moviepagination.domain.repository.IRemoteRepo
import io.reactivex.rxjava3.core.Single

class RemoteRepoImpl(private val apiService: ApiService) : IRemoteRepo {

    private val movieItemListMapper = MovieItemListMapper()
    private val movieInfoMapper = MovieInfoMapper()
    private val actorMapper = ActorMapper()
    private val youtubeTrailerMapper = YoutubeTrailerMapper()
    private val searchResultMapper = SearchResultMapper()

    override fun getMovieNowInTheatre(): Single<MovieItemList> {
        return apiService.getActiveMoviesInTheatres(MOVIE_API_KEY).map {
            movieItemListMapper.mapMovieItemListDtoToEntity(it)
        }
    }

    override fun getMovieByIdFromServer(movieId: String): Single<MovieInfo> {
        return apiService.getMovieById(EN_LANG, movieId, MOVIE_API_KEY).map {
            movieInfoMapper.mapMovieInfoDtoToEntity(it)
        }
    }

    override fun getComingSoonMoviesFromServer(): Single<MovieItemList> {
        return apiService.getComingSoonMovies(EN_LANG, MOVIE_API_KEY).map {
            movieItemListMapper.mapMovieItemListDtoToEntity(it)
        }
    }

    override fun getTOP250Movies(): Single<MovieItemList> {
        return apiService.getTOP250Movies(MOVIE_API_KEY).map {
            movieItemListMapper.mapMovieItemListDtoToEntity(it)
        }
    }

    override fun getMostPopularMovies(): Single<MovieItemList> {
        return apiService.getMostPopularMovies(EN_LANG, MOVIE_API_KEY).map {
            movieItemListMapper.mapMovieItemListDtoToEntity(it)
        }
    }

    override fun getMostPopularTVs(): Single<MovieItemList> {
        return apiService.getMostPopularTVs(EN_LANG, MOVIE_API_KEY).map {
            movieItemListMapper.mapMovieItemListDtoToEntity(it)
        }
    }

    override fun getActorInfoById(actorId: String): Single<ActorInfo> {
        return apiService.getActorInfoById(EN_LANG, MOVIE_API_KEY, actorId).map {
            actorMapper.mapActorInfoDtoToEntity(it)
        }
    }

    override fun getSearchList(expression: String): Single<SearchResult> {
        return apiService.getSearchList(MOVIE_API_KEY, expression).map {
            searchResultMapper.mapSearchResultDtoToEntity(it)
        }
    }

    override fun getTOP250TVs(): Single<MovieItemList> {
        return apiService.getTOP250TVs(MOVIE_API_KEY).map {
            movieItemListMapper.mapMovieItemListDtoToEntity(it)
        }
    }

    override fun getMovieTrailerById(movieId: String): Single<YouTubeTrailer> {
        return apiService.getMovieTrailerById(movieId, MOVIE_API_KEY).map {
            youtubeTrailerMapper.mapYoutubeTrailerDtoToEntity(it)
        }
    }

    companion object {
        private const val MOVIE_API_KEY = BuildConfig.IMDb_API_KEY
        private const val EN_LANG = "en"
        private const val RU_LANG = "ru"
    }
}
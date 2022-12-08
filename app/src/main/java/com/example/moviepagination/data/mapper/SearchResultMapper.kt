package com.example.moviepagination.data.mapper

import com.example.moviepagination.data.network.dto.searchdb.ResultDto
import com.example.moviepagination.data.network.dto.searchdb.SearchResultDto
import com.example.moviepagination.domain.entities.search.Result
import com.example.moviepagination.domain.entities.search.SearchResult

class SearchResultMapper {
    fun mapSearchResultDtoToEntity(searchResultDbModel: SearchResultDto): SearchResult {
        return SearchResult(
            searchType = searchResultDbModel.searchType,
            expression = searchResultDbModel.expression,
            results = convertResultListDtoToEntity(searchResultDbModel.results),
            errorMessage = searchResultDbModel.errorMessage
        )
    }

    private fun mapResultDtoToEntity(resultDto: ResultDto): Result {
        return Result(
            id = resultDto.id,
            resultType = resultDto.resultType,
            image = resultDto.image,
            title = resultDto.title,
            description = resultDto.description
        )
    }

    private fun convertResultListDtoToEntity(list: List<ResultDto>?): List<Result>? {
        return list?.map {
            mapResultDtoToEntity(it)
        }
    }
}
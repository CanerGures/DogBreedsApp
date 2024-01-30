package com.canergures.data.dataSource.remote

import com.canergures.data.dataSource.remote.dto.BreedImagesResponse
import com.canergures.data.dataSource.remote.dto.BreedSingleImageResponse
import com.canergures.data.dataSource.remote.dto.BreedsListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogBreedsApi {
    @GET("breeds/list/all")
    suspend fun fetchDogBreedsList(): Response<BreedsListResponse>

    @GET("breed/{breed_name}/images/random/4")
    suspend fun fetchDogBreedImages(@Path("breed_name") breedName: String): Response<BreedImagesResponse>

    @GET("breed/{breed_name}/images/random")
    suspend fun fetchDogBreedsSingleImage(@Path("breed_name") breedName: String): Response<BreedSingleImageResponse>
}
package com.canergures.data.dataSource.local.persistance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import com.canergures.data.model.DogBreed
import com.canergures.data.model.DogBreedImages
import com.canergures.data.model.FavouriteImages
import kotlinx.coroutines.flow.Flow

@Dao
interface BreedsDao {

    @Query("SELECT * FROM dogbreed")
    fun getDogBreedsList(): Flow<List<DogBreed>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDogBreedList(dogBreeds: List<DogBreed>)

    @Query("SELECT dog_breed_image_urls FROM dogbreedimages WHERE breed_name =:breedName")
    fun getDogBreedImagesList(breedName: String): List<String>

    @Query("UPDATE dogbreedimages SET dog_breed_image_urls = :imageUrlString WHERE breed_name =:breedName")
    suspend fun updateDogBreedImagesList(imageUrlString: List<String>, breedName: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogBreedImagesList(dogBreeds: DogBreedImages)

    @Query("UPDATE dogbreed SET is_favourite = :isFavourite WHERE name LIKE :dogBreedName")
    suspend fun updateDogBreeds(dogBreedName: String, isFavourite: Boolean)

    @Query("SELECT * FROM dogbreed WHERE is_favourite = 1")
    fun getFavouriteDogBreedsList(): Flow<List<DogBreed>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteDogBreedImages(favouriteImage: FavouriteImages)

    @Query("SELECT * FROM favouriteimages WHERE is_favourite = 1")
    fun getFavouriteDogBreedsImages(): Flow<List<FavouriteImages>>

    @Delete(entity = FavouriteImages::class)
    fun deleteFavouriteDogBreedsImage(favouriteImage: FavouriteImages)

}
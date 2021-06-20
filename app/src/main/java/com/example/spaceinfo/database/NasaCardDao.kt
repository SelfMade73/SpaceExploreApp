package com.example.spaceinfo.database

import androidx.room.*
import com.example.spaceinfo.models.ThemeItem

@Dao
interface NasaCardDao {
    
    @Query("SELECT * FROM nasa_cards")
    suspend fun getAllCards() : MutableList<ThemeItem>

    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCard( item: ThemeItem ) : Long

    @Delete
    suspend fun deleteCard ( item: ThemeItem )

    @Query("DELETE FROM nasa_cards WHERE id = :id")
    suspend fun deleteCardById( id : Long )

    @Query("SELECT EXISTS(SELECT * FROM nasa_cards WHERE id = :id)")
    suspend fun isItemExists( id : Long ) : Boolean

}
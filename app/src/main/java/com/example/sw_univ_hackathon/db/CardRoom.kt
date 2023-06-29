package com.example.sw_univ_hackathon.db

import androidx.room.*

@Entity(tableName = "md_card_database")
data class BusinessCard(
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "phoneNumber")
    val phoneNumber: String,

    @ColumnInfo(name = "company")
    val company: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

@Dao
interface BusinessCardDao {
    @Query("SELECT * FROM md_card_database")
    fun getAll(): List<BusinessCard>

    @Insert
    fun insert(recentSearch: BusinessCard)

    @Insert
    fun insertAll(recentData: List<BusinessCard>)

    @Delete
    fun delete(recentData: BusinessCard)

//    @Query("SELECT * FROM md_card_database WHERE searchText = :searchTxt")
//    fun findRecentSearchBySearchText(searchTxt: String): BusinessCard

}

@Database(entities = [BusinessCard::class], version = 1)
abstract class BusinessCardDatabase: RoomDatabase() {
    abstract fun businessCardDao(): BusinessCardDao
}
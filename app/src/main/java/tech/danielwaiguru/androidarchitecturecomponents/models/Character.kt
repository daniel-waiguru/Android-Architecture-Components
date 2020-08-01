package tech.danielwaiguru.androidarchitecturecomponents.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "character")
@Parcelize
data class Character (
    @PrimaryKey(autoGenerate = true)
    @field:Json(name = "id") val id: Int = 0,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "status") val status: String,
    @field:Json(name = "species") val species: String,
    @field:Json(name = "gender") val gender: String,
    @field:Json(name = "image") val image: String,
    @field:Json(name = "created") val createdAt: String
): Parcelable
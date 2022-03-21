package com.example.myartsycollection.persistence

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.myartsycollection.model.ArtworkLink
import com.example.myartsycollection.model.Link
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

@ProvidedTypeConverter
class TypeResponseConverter @Inject constructor(
    private val moshi: Moshi
) {
    @TypeConverter
    fun fromString(value: String): List<String>? {
        val listType = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter: JsonAdapter<List<String>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromList(type: List<String>?): String {
        val listType = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter: JsonAdapter<List<String>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }

    @TypeConverter
    fun fromArtworkLink(type: ArtworkLink): String {
        val adapter: JsonAdapter<ArtworkLink> = moshi.adapter(ArtworkLink::class.java)
        return adapter.toJson(type)
    }

    @TypeConverter
    fun toArtworkLink(value: String): ArtworkLink? {
        val adapter: JsonAdapter<ArtworkLink> = moshi.adapter(ArtworkLink::class.java)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun toLink(value: String): Link? {
        val adapter: JsonAdapter<Link> = moshi.adapter(Link::class.java)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromLink(type: Link): String? {
        val adapter: JsonAdapter<Link> = moshi.adapter(Link::class.java)
        return adapter.toJson(type)
    }
}
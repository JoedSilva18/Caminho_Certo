package com.core.converter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class AppMoshiConverter (private val moshi: Moshi) :
    MoshiConverter {

    override fun <T> createAdapter(
        classType: Class<T>
    ): JsonAdapter<T>? {
        val type = Types.newParameterizedType(classType)
        return moshi.adapter(type)
    }

    override fun <T> createListAdapter(
        classType: Class<T>
    ): JsonAdapter<List<T>>? {
        val type = Types.newParameterizedType(List::class.java, classType)
        return moshi.adapter(type)
    }

    override fun <X, Y> createMapAdapter(
        firstType: Class<X>,
        secondType: Class<Y>
    ): JsonAdapter<Map<X, Y>>? {
        val type = Types.newParameterizedType(Map::class.java, firstType, secondType)
        return moshi.adapter(type)
    }

    override fun <T> fromJsonObject(
        json: String,
        classType: Class<T>,
        onError: (Throwable) -> Unit
    ): T? {
        try {
            if (json.isNotBlank()) {
                return createAdapter(classType)?.fromJson(json)
            }
        } catch (e: Throwable) {
            onError(e)
        }
        return null
    }

    override fun <X, Y> fromJsonObject(
        json: String,
        keyType: Class<X>,
        valueType: Class<Y>,
        serializeNulls: Boolean,
        onError: (Throwable) -> Unit
    ): Map<X, Y>? = try {
        val adapter = if (serializeNulls) {
            createMapAdapter(keyType, valueType)?.serializeNulls()
        } else {
            createMapAdapter(keyType, valueType)
        }
        adapter?.fromJson(json)
    } catch (e: Throwable) {
        onError(e)
        null
    }

    override fun <T> fromJsonArray(
        json: String,
        classType: Class<T>,
        onError: (Throwable) -> Unit
    ): List<T>? {
        try {
            if (json.isNotBlank()) {
                return createListAdapter(classType)?.fromJson(json)
            }
        } catch (e: Throwable) {
            onError(e)
        }
        return null
    }

    override fun <T> fromJsonValue(
        json: Any,
        classType: Class<T>,
        onError: (Throwable) -> Unit
    ): T? {
        try {
            return createAdapter(classType)?.fromJsonValue(json)
        } catch (e: Throwable) {
            onError(e)
        }
        return null
    }

    override fun <T> fromJsonObjectList(
        json: Any,
        classType: Class<T>,
        onError: (Throwable) -> Unit
    ): List<T>? {
        try {
            return createListAdapter(classType)?.fromJsonValue(json)
        } catch (e: Throwable) {
            onError(e)
        }
        return null
    }

    override fun <T> toJsonObject(
        `object`: T,
        classType: Class<T>,
        serializeNulls: Boolean,
        onError: (Throwable) -> Unit
    ): String? = try {
        val adapter = if (serializeNulls) {
            createAdapter(classType)?.serializeNulls()
        } else {
            createAdapter(classType)
        }
        adapter?.toJson(`object`)
    } catch (e: Throwable) {
        onError(e)
        null
    }

    override fun <X, Y> toJsonObject(
        map: Map<X, Y>,
        keyType: Class<X>,
        valueType: Class<Y>,
        serializeNulls: Boolean,
        onError: (Throwable) -> Unit
    ): String? = try {
        val adapter = if (serializeNulls) {
            createMapAdapter(keyType, valueType)?.serializeNulls()
        } else {
            createMapAdapter(keyType, valueType)
        }
        adapter?.toJson(map)
    } catch (e: Throwable) {
        onError(e)
        null
    }

    override fun <T> toJsonArray(
        list: List<T>,
        classType: Class<T>,
        serializeNulls: Boolean,
        onError: (Throwable) -> Unit
    ): String? = try {
        val adapter = if (serializeNulls) {
            createListAdapter(classType)?.serializeNulls()
        } else {
            createListAdapter(classType)
        }
        adapter?.toJson(list)
    } catch (e: Throwable) {
        onError(e)
        null
    }

    override fun <T> convertToJson(
        `object`: T,
        classType: Class<T>,
        defaultValue: String
    ): String = try {
        createAdapter(classType)?.toJson(`object`) ?: defaultValue
    } catch (e: JsonDataException) {
        defaultValue
    } catch (e: IllegalArgumentException) {
        defaultValue
    }
}

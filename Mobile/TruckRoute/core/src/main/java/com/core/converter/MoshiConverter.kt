package com.core.converter

import com.squareup.moshi.JsonAdapter

private const val JSON_DEFAULT_STRING = "{}"

interface MoshiConverter {

    fun <T> fromJsonObject(
        json: String,
        classType: Class<T>,
        onError: (Throwable) -> Unit = {}
    ): T?

    fun <X, Y> fromJsonObject(
        json: String,
        keyType: Class<X>,
        valueType: Class<Y>,
        serializeNulls: Boolean = false,
        onError: (Throwable) -> Unit = {}
    ): Map<X, Y>?

    fun <T> fromJsonArray(
        json: String,
        classType: Class<T>,
        onError: (Throwable) -> Unit = {}
    ): List<T>?

    fun <T> fromJsonValue(
        json: Any,
        classType: Class<T>,
        onError: (Throwable) -> Unit = {}
    ): T?

    fun <T> fromJsonObjectList(
        json: Any,
        classType: Class<T>,
        onError: (Throwable) -> Unit = {}
    ): List<T>?

    fun <T> toJsonObject(
        `object`: T,
        classType: Class<T>,
        serializeNulls: Boolean = false,
        onError: (Throwable) -> Unit = {}
    ): String?

    fun <X, Y> toJsonObject(
        map: Map<X, Y>,
        keyType: Class<X>,
        valueType: Class<Y>,
        serializeNulls: Boolean = false,
        onError: (Throwable) -> Unit = {}
    ): String?

    fun <T> toJsonArray(
        list: List<T>,
        classType: Class<T>,
        serializeNulls: Boolean = false,
        onError: (Throwable) -> Unit = {}
    ): String?

    fun <T> createAdapter(classType: Class<T>): JsonAdapter<T>?

    fun <T> createListAdapter(classType: Class<T>): JsonAdapter<List<T>>?

    fun <X, Y> createMapAdapter(
        firstType: Class<X>,
        secondType: Class<Y>
    ): JsonAdapter<Map<X, Y>>?

    fun <T> convertToJson(
        `object`: T,
        classType: Class<T>,
        defaultValue: String = JSON_DEFAULT_STRING
    ): String

}

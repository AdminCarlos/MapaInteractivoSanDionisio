package com.odesar.mapabioculturalinteractivosandionisio.TypeConverters

import androidx.room.TypeConverter

class BooleanConverter {

    @TypeConverter
    fun fromBoolean(value : Int?) : Boolean? {

        if (value == 1) {

            return true

        }

        else {

            return false

        }

    }

    @TypeConverter
    fun booleanToInt(value : Boolean) : Int? {

        if (value) {

            return 1

        }

        else {

            return 0

        }

    }

}
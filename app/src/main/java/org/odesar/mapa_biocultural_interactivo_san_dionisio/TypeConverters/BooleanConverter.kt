package org.odesar.mapa_biocultural_interactivo_san_dionisio.TypeConverters

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
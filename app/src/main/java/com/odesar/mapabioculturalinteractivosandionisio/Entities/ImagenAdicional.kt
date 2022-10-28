package com.odesar.mapabioculturalinteractivosandionisio.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "imagenes_adicionales")
data class ImagenAdicional(

    @PrimaryKey
    var id : Int?,

    @ColumnInfo(name = "lugar_id")
    var lugarId : Int,

    var src : String?,

    var thumbnail : String?

)

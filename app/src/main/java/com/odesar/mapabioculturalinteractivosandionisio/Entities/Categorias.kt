package com.odesar.mapabioculturalinteractivosandionisio.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categorias")
data class Categorias(

    @PrimaryKey(autoGenerate = true)
    var id : Int?,

    var nombre : String,

    @ColumnInfo(name = "ruta_icono")
    var rutaIcono : String

)

package com.odesar.mapabioculturalinteractivosandionisio.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Leyendas(

    @PrimaryKey(autoGenerate = true)
    val id : Int?,

    var categoria : String?,

    var shortName : String?,

    var rutaIcono : String?,

    var nombre : String?,

    var descripcion: String?

)

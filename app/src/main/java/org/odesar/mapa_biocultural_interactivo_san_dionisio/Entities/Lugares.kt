package org.odesar.mapa_biocultural_interactivo_san_dionisio.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Lugares")
data class Lugares(

    @PrimaryKey(autoGenerate = true)
    var id: Int?,

    var nombre : String,

    var categoria : String?,

    var descripcion : String?,

    var icono : String?,

    var coordX : Double?,

    var coordY : Double?,

    @ColumnInfo(name = "imagen_adicional", defaultValue = "0")
    var imagenAdicional : Int?,

    @ColumnInfo(name = "tamaño_adicional", defaultValue = "0")
    var tamanioAdicional : Int?
)

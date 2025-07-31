package org.odesar.mapa_biocultural_interactivo_san_dionisio.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "informacion_adicional")
data class InformacionAdicional(

    @PrimaryKey
    var id : Int?,

    var nombre : String,

    var texto : String,

    var imagen : String?

) : java.io.Serializable

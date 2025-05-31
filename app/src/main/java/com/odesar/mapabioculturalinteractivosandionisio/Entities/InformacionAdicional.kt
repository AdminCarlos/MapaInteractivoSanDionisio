package com.odesar.mapabioculturalinteractivosandionisio.Entities

import androidx.room.ColumnInfo
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

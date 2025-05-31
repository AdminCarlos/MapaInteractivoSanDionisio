package com.odesar.mapabioculturalinteractivosandionisio.DAOs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.odesar.mapabioculturalinteractivosandionisio.Entities.InformacionAdicional

@Dao
interface InformacionAdicionalDAO {

    @Insert
    fun insertInformacionAdicional(informacionAdicional: InformacionAdicional)

    @Update
    fun updateInformacionAdicional(informacionAdicional: InformacionAdicional)

    @Delete
    fun deleteInformacionAdicional(informacionAdicional: InformacionAdicional)

    @Query("""
        SELECT * FROM informacion_adicional
    """)
    fun getAllInformacionAdicional() : List<InformacionAdicional>

}
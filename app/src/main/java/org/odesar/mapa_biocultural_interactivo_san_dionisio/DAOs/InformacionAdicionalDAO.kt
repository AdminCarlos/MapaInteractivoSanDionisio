package org.odesar.mapa_biocultural_interactivo_san_dionisio.DAOs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import org.odesar.mapa_biocultural_interactivo_san_dionisio.Entities.InformacionAdicional

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
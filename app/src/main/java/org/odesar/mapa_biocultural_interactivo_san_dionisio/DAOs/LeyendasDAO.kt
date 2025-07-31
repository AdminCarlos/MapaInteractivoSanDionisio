package org.odesar.mapa_biocultural_interactivo_san_dionisio.DAOs

import androidx.room.*
import org.odesar.mapa_biocultural_interactivo_san_dionisio.Entities.Leyendas

@Dao
interface LeyendasDAO {

    @Insert
    fun insertLeyenda(leyenda : Leyendas)

    @Update
    fun updateLeyenda(leyenda: Leyendas)

    @Delete
    fun deleteLeyenda(leyenda: Leyendas)

    @Query("""
        SELECT * FROM leyendas WHERE categoria IS NOT NULL OR categoria != ''
    """)
    fun getAllLeyendas() : List<Leyendas>

}
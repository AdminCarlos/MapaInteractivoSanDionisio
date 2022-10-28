package com.odesar.mapabioculturalinteractivosandionisio.DAOs

import androidx.room.*
import com.odesar.mapabioculturalinteractivosandionisio.Entities.Leyendas

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
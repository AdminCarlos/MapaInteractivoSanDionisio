package com.odesar.mapabioculturalinteractivosandionisio.DAOs

import androidx.room.*
import com.odesar.mapabioculturalinteractivosandionisio.Entities.Lugares

@Dao
interface LugaresDAO {

    @Insert
    fun insertLugar(lugar : Lugares)

    @Update
    fun updateLugar(lugar : Lugares)

    @Delete
    fun deleteLugar(lugar : Lugares)

    @Query("""
        SELECT * FROM lugares WHERE nombre LIKE '%Temp'
    """)
    fun getAllTemp() : List<Lugares>

    @Query("""
        SELECT * FROM lugares
    """)
    fun getAll() : List<Lugares>

}
package com.odesar.mapabioculturalinteractivosandionisio.DAOs

import androidx.room.*
import com.odesar.mapabioculturalinteractivosandionisio.Entities.Categorias

@Dao
interface CategoriasDAO {

    @Insert
    fun insertCategoria(categorias: Categorias)

    @Update
    fun updateCategoria(categorias: Categorias)

    @Delete
    fun deleteCategoria(categorias: Categorias)

    @Query("""
        SELECT DISTINCT * FROM categorias
    """)
    fun getAllCategorias() : List<Categorias>

}
package org.odesar.mapa_biocultural_interactivo_san_dionisio.DAOs

import androidx.room.*
import org.odesar.mapa_biocultural_interactivo_san_dionisio.Entities.Categorias

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
package org.odesar.mapa_biocultural_interactivo_san_dionisio.DAOs

import androidx.room.*
import org.odesar.mapa_biocultural_interactivo_san_dionisio.Entities.ImagenAdicional

@Dao
interface ImagenAdicionalDAO {

    @Insert
    fun insertImagenAdicional(imagenAdicional: ImagenAdicional)

    @Update
    fun updateImagenAdicional(imagenAdicional: ImagenAdicional)

    @Delete
    fun deleteImagenAdicional(imagenAdicional: ImagenAdicional)

    @Query("""
        SELECT DISTINCT img.* FROM imagenes_adicionales img WHERE lugar_id = :lugarId
    """)
    fun getImagenesAdicionalesByLugar(lugarId : Int) : List<ImagenAdicional>

}
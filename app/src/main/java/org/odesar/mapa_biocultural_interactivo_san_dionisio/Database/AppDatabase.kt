package org.odesar.mapa_biocultural_interactivo_san_dionisio.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.odesar.mapa_biocultural_interactivo_san_dionisio.DAOs.*
import org.odesar.mapa_biocultural_interactivo_san_dionisio.Entities.*

@Database(entities = [Lugares::class, ImagenAdicional::class, Leyendas::class, Categorias::class, InformacionAdicional::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun lugaresDAO(): LugaresDAO
    abstract fun imagenAdicionalDAO(): ImagenAdicionalDAO
    abstract fun leyendasDAO(): LeyendasDAO
    abstract fun categoriasDAO() : CategoriasDAO
    abstract fun informacionAdicionalDAO() : InformacionAdicionalDAO

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(

                    context.applicationContext,
                    AppDatabase::class.java,
                    "lugaresSanDionisio.db"
                ).createFromAsset("lugaresSanDionisio.db").build()

                INSTANCE = instance

                instance

            }

        }

    }

}
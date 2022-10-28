package com.odesar.mapabioculturalinteractivosandionisio.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.odesar.mapabioculturalinteractivosandionisio.DAOs.CategoriasDAO
import com.odesar.mapabioculturalinteractivosandionisio.DAOs.ImagenAdicionalDAO
import com.odesar.mapabioculturalinteractivosandionisio.DAOs.LeyendasDAO
import com.odesar.mapabioculturalinteractivosandionisio.DAOs.LugaresDAO
import com.odesar.mapabioculturalinteractivosandionisio.Entities.Categorias
import com.odesar.mapabioculturalinteractivosandionisio.Entities.ImagenAdicional
import com.odesar.mapabioculturalinteractivosandionisio.Entities.Leyendas
import com.odesar.mapabioculturalinteractivosandionisio.Entities.Lugares

@Database(entities = [Lugares::class, ImagenAdicional::class, Leyendas::class, Categorias::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun lugaresDAO(): LugaresDAO
    abstract fun imagenAdicionalDAO(): ImagenAdicionalDAO
    abstract fun leyendasDAO(): LeyendasDAO
    abstract fun categoriasDAO() : CategoriasDAO

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
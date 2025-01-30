package com.example.practiceonroom.todos.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 2, exportSchema = false)

abstract class TodoDataBase : RoomDatabase() {
    //    ازاي اربط الـdao بالداتابيز ؟
    abstract val dao: TodoDao

    companion object {
        @Volatile
        //        فانكشن تجيب الانستانس بتاع الـdao
        private var daoInstance: TodoDao? = null

        //        عملنا كومبنيون اوبجكت عشان نقدر نأكسس الفانكشن دي من غير ما نعمل انستانس من الكلاس ده
        private fun buildDataBase(context: Context): TodoDataBase {

            return Room.databaseBuilder(
                context.applicationContext,
                TodoDataBase::class.java,
                "TodoDataBase"
            ).fallbackToDestructiveMigration().build()

        }//            الفانكشن ال هتجيب الانستانس دي

        fun getDaoInstance(context: Context): TodoDao {
            synchronized(this) {
                if (daoInstance == null) {
                    daoInstance = buildDataBase(context).dao
                }
                return daoInstance as TodoDao
            }
        }
    }
}
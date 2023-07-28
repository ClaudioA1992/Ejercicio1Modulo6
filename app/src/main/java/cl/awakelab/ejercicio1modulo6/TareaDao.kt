package cl.awakelab.ejercicio1modulo6

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TareaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTarea(tarea: Tarea)

    @Query("select * from tabla_tarea order by id asc")
    fun getTareas(): List<Tarea>

}

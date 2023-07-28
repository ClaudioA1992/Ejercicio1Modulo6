package cl.awakelab.ejercicio1modulo6

import androidx.lifecycle.LiveData

class Repositorio(private val tareaDao: TareaDao) {

    suspend fun insertarTarea(tarea: Tarea) {
        tareaDao.insertarTarea(tarea)

    }

    fun getTareas(): LiveData<List<Tarea>> {
        return tareaDao.getTareas()
    }


}
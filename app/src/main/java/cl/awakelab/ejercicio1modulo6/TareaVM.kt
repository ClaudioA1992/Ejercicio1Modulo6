package cl.awakelab.ejercicio1modulo6

import android.app.Application
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TareaVM(aplicacion: Application): AndroidViewModel(aplicacion) {

    private val repositorio: Repositorio
    init {
        repositorio = Repositorio(TareaBaseDatos.getDatabase(aplicacion).getTareaDao())
    }

    fun obtenerTareas(): LiveData<List<Tarea>> {
        return repositorio.getTareas()
    }

    fun insertarTarea(tarea: Tarea) = viewModelScope.launch {
        repositorio.insertarTarea(tarea)
    }

}


package cl.awakelab.ejercicio1modulo6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cl.awakelab.ejercicio1modulo6.databinding.FragmentAgregarBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AgregarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AgregarFragment : Fragment() {

    lateinit var binding: FragmentAgregarBinding

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAgregarBinding.inflate(layoutInflater)
        initListener()
        cargarTareas()
        // Inflate the layout for this fragment
        return binding.root
    }

    fun initListener() {
        binding.buttonAgregar.setOnClickListener {
            val texto = binding.editText.text.toString()
            guardarTexto(texto)
            Toast.makeText(requireContext(), "Se ha agregado un texto", Toast.LENGTH_SHORT).show()
        }
    }

    fun guardarTexto(text: String) {
        val dao = TareaBaseDatos.getDatabase(requireContext()).getTaskDao()
        val tarea = Tarea(text, " ")
        GlobalScope.launch { dao.insertarTarea(tarea) }
    }

    private fun cargarTareas() {
        val dao = TareaBaseDatos.getDatabase(requireContext()).getTaskDao()
        GlobalScope.launch {
            val tareas = dao.getTareas()
            val tareasAsText = tareas.joinToString("\n") { it.nombre }
            binding.textView.text = tareasAsText
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AgregarFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AgregarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}


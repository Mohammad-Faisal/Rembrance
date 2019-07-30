package com.example.rembrance


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_add_new_word.view.*


class AddNewWordFragment : Fragment() {


    private lateinit var fragmentContext: Context
    private lateinit var communicator: Communicator
    private lateinit var firestoreRef : FirebaseFirestore


    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentContext=  context
        communicator = context as Communicator
        firestoreRef = FirebaseFirestore.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view :View = inflater.inflate(R.layout.fragment_add_new_word, container, false)



        view.btn_add_word_add.setOnClickListener {

            communicator.showLoadingDialog("Saving Word to Database . . . ")


            if(isValid(view)){
                val id : String = firestoreRef.collection("faisal").document().id
                val wordToUpload = getWordObjectToUpload(view , id);
                firestoreRef.collection("faisal").document(id).set(wordToUpload).addOnSuccessListener {
                    communicator.hideLoadingDialog()

                    val args = AddNewWordFragmentDirections.actionAddNewWordFragmentToAllWordsFragment()
                    view.findNavController().navigate(args)
                }
            }

        }

        return view
    }

    private fun isValid (view: View): Boolean {
        val word = view.txt_add_word_word.text.toString().toUpperCase()
        val meaning = view.txt_add_word_meaning.text.toString()

        if(word.length == 0 || meaning.length == 0) {
            Toast.makeText(fragmentContext , "You Must provide a Word and its Meaning" , Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun getWordObjectToUpload (view: View , id: String ): Word {

        val word = view.txt_add_word_word.text.toString().toUpperCase()
        val meaning = view.txt_add_word_meaning.text.toString()
        val example = view.txt_add_word_example.text.toString()
        val synonym = view.txt_add_word_synonym.text.toString().toUpperCase()
        //val wordToUpload: Word=  Word()

        val wordToUpload: Word=  Word(id , word , meaning ,  arrayListOf<String>(synonym) , arrayListOf<String>(example) , false , false)

        return wordToUpload
    }


}

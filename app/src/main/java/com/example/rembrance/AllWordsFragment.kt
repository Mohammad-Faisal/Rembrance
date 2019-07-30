package com.example.rembrance


import android.content.Context
import android.os.Bundle
import android.provider.UserDictionary
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_all_words.view.*
import com.google.firebase.firestore.FirebaseFirestoreSettings




class AllWordsFragment : Fragment() {

    private lateinit var fragmentContext: Context
    private lateinit var communicator: Communicator
    private lateinit var firestoreRef : FirebaseFirestore


    private lateinit var wordsAdapter: WordsAdapter
    private lateinit var wordsRecyclerAdapter: WordsRecyclerAdapter


    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentContext=  context
        communicator = context as Communicator
        firestoreRef = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
            .setTimestampsInSnapshotsEnabled(true)
            .build()
        firestoreRef.setFirestoreSettings(settings)
        wordsAdapter = WordsAdapter(fragmentContext)


        wordsRecyclerAdapter = WordsRecyclerAdapter(fragmentContext)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        val view: View = inflater.inflate(R.layout.fragment_all_words, container, false)



        firestoreRef.collection("faisal")
            .get()
            .addOnSuccessListener {
                var wordsList : ArrayList<Word> = ArrayList()
                for (document in it.documents ) {
                   val word = document.toObject(Word::class.java)
                    wordsList.add(word!!)
                }
                wordsRecyclerAdapter.setWords(wordsList)
        }


        val gridLayoutManager = GridLayoutManager(context  ,2)
        view.recycler_words.layoutManager = gridLayoutManager
        view.recycler_words.adapter = wordsRecyclerAdapter



        view.btn_allwords_addnew.setOnClickListener {
            val args = AllWordsFragmentDirections.actionAllWordsFragmentToAddNewWordFragment()
            view.findNavController().navigate(args)
        }




//        val  infiniteCycleViewPager : HorizontalInfiniteCycleViewPager =  view.findViewById(R.id.viewpager_alllwords_words);
//        infiniteCycleViewPager.setAdapter(wordsAdapter)
//        infiniteCycleViewPager.setScrollDuration(100);
//        //infiniteCycleViewPager.setInterpolator();
//        infiniteCycleViewPager.setMediumScaled(true);
//        infiniteCycleViewPager.setMaxPageScale(0.8F);
//        infiniteCycleViewPager.setMinPageScale(0.5F);
//        infiniteCycleViewPager.setCenterPageScaleOffset(30.0F);
//        infiniteCycleViewPager.setMinPageScaleOffset(5.0F);
//        //infiniteCycleViewPager.setOnInfiniteCyclePageTransformListener(...);



        return view
    }



}

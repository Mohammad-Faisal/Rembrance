package com.example.rembrance

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView


class WordsRecyclerAdapter internal constructor(
    context: Context
) :  RecyclerView.Adapter<WordsRecyclerAdapter.wordViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var wordList = emptyList<Word>()

    inner class wordViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val wordName     : TextView = itemView.findViewById(R.id.txt_itemword_word)
        val wordMeaning : TextView =itemView.findViewById(R.id.txt_itemword_meaning)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsRecyclerAdapter.wordViewHolder {
        val itemView = inflater.inflate(R.layout.item_word, parent, false)
        return wordViewHolder(itemView)    }

    override fun getItemCount()=  wordList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: WordsRecyclerAdapter.wordViewHolder, position: Int) {


        val current = wordList[position]
        holder.wordName.text = current.word
        holder.wordMeaning.setOnClickListener {
            holder.wordMeaning.text = current.meaning
        }

    }

    internal fun setWords(Words: List<Word>) {
        this.wordList = Words
        notifyDataSetChanged()
    }


}
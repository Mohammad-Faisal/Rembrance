package com.example.rembrance

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.get
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter


class WordsAdapter internal constructor(
    context: Context
) : PagerAdapter() {

    //private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)as LayoutInflater
    private var wordsList = emptyList<Word>()



    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return wordsList.size
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {


        val itemView : View = inflater.inflate(R.layout.item_word , container , false)

        val word     : TextView = itemView.findViewById(R.id.txt_itemword_word)
        val meaning : TextView =itemView.findViewById(R.id.txt_itemword_meaning)

        word.setText(wordsList.get(position).word)
        meaning.setText(wordsList.get(position).meaning)

        container.addView(itemView);
        return itemView
    }
//
//    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//
//
//        inflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        var view:View=inflater.inflate(R.layout.card_item,container,false)
//        image=view.findViewById(R.id.movie_image_view)
//        image.setBackgroundResource(images[position])
//        container!!.addView(view)
//        return view
//    }

//    override fun isViewFromObject(p0: View, p1: Any): Boolean =p0==p1 as LinearLayout



    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        if(container.getChildAt(position) != null )container.removeViewAt(position)
        //container.removeView(object  as LinearLayout )
    }




    inner class buildingViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val word     : TextView = itemView.findViewById(R.id.txt_itemword_word)
        val meaning  : Button =itemView.findViewById(R.id.txt_itemword_meaning)
    }



    internal fun setWords(Words: List<Word>) {
        this.wordsList = Words
        notifyDataSetChanged()
    }


}
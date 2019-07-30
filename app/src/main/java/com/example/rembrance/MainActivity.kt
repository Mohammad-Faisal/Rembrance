package com.example.rembrance

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity()  , Communicator{



    private lateinit var builder: AlertDialog.Builder
    private lateinit var dialogView: View
    private lateinit var loadingDialog: Dialog


    override fun showLoadingDialog(message: String) {


        builder = AlertDialog.Builder(this)
        dialogView = layoutInflater.inflate(R.layout.progress_dialog , null)
        builder.setCancelable(false)


        val dialogTextVIew =  dialogView.findViewById<TextView>(R.id.progress_dialog_message)

        if (dialogTextVIew.getParent() != null) {
            (dialogTextVIew.getParent() as ViewGroup).removeView(dialogTextVIew) // <- fix
        }

        dialogTextVIew.text = message
        builder.setView(dialogView)
        loadingDialog = builder.create()

        loadingDialog.show()
    }

    override fun hideLoadingDialog(){
        loadingDialog.dismiss()
    }







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

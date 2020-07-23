package com.example.kotlinuberriderremake.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.example.kotlinuberriderremake.Common
import com.example.kotlinuberriderremake.model.TokenModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

/**
 * Created by iddangunawan on 23/07/20
 */
object UserUtils {
    fun updateUser(view: View?, updateData: HashMap<String, Any>) {
        FirebaseDatabase.getInstance()
            .getReference(Common.RIDER_INFO_REFERENCE)
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
            .updateChildren(updateData)
            .addOnFailureListener { error ->
                Snackbar.make(view!!, error.message.toString(), Snackbar.LENGTH_LONG).show()
            }
            .addOnSuccessListener {
                Snackbar.make(view!!, "Update information success!", Snackbar.LENGTH_LONG).show()
            }
    }

    fun updateToken(context: Context, token: String) {
        val tokenModel = TokenModel()
        tokenModel.token = token

        FirebaseDatabase.getInstance()
            .getReference(Common.TOKEN_REFERENCE)
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
            .setValue(tokenModel)
            .addOnFailureListener { error ->
                Toast.makeText(context, error.message.toString(), Toast.LENGTH_LONG).show()
            }
            .addOnSuccessListener {
//                Toast.makeText(context, "", Toast.LENGTH_LONG).show()
            }
    }
}
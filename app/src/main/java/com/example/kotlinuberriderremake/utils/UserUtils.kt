package com.example.kotlinuberriderremake.utils

import android.view.View
import com.example.kotlinuberriderremake.Common
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
}
package com.example.dynamiclayouts.Repo

import android.util.Log
import com.example.dynamiclayouts.FakeLayoutRepo
import com.example.dynamiclayouts.data.RetrofitClient
import com.example.dynamiclayouts.models.APIResponse
import com.example.dynamiclayouts.models.LayoutModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class LayoutRepoImpl : LayoutRepo {
    companion object {
        private const val TAG = "LayoutRepo"
    }

    val database = Firebase.database
    val myRef = database.getReference("layouts")
    fun writeToFirebase() {

        myRef.setValue(FakeLayoutRepo().layouts)
    }

    suspend fun getLayoutListener(layoutId: String): Flow<LayoutModel?> {
        val flow = callbackFlow<LayoutModel?> {
            myRef.child(layoutId).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {


                    val layout = snapshot.getValue(LayoutModel::class.java)
                    var json = Gson().toJson(layout)
                    Log.d(TAG, "onDataChange() called with: snapshot = $json")
                    trySend(layout)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
            awaitClose()
        }
        return flow

    }

    override suspend fun getLayout(layoutId: String): LayoutModel? {
        val response = RetrofitClient.getAPI().getLayout()
        Log.d(TAG, "getLayout: $response")
        return response[layoutId]
    }
}
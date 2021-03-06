package com.appcosmetics.app.ui.auth

import android.content.Context
import com.appcosmetics.app.data.model.ActionState
import com.appcosmetics.app.data.repository.AuthRepository
import kotlinx.coroutines.*

object AppcosmeticsAuth {

    fun logout(context: Context, callback: ((ActionState<Boolean>) -> Unit)? = null) {
        val repo = AuthRepository(context)
       CoroutineScope(Dispatchers.IO).launch {
           val resp = repo.logout()
           withContext(Dispatchers.Main) {
               if (callback != null) callback.invoke(resp)
       }
            }
        }
    }
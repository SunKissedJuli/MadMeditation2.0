package com.coolgirl.madmeditation.screens.Profile

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.coolgirl.madmeditation.GetLocalUser
import com.coolgirl.madmeditation.ImageLoader
import com.coolgirl.madmeditation.Models.UserLoginDataResponse
import com.coolgirl.madmeditation.R
import com.coolgirl.madmeditation.pref
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.lang.reflect.Type


class ProfileViewModel : ViewModel() {

    private var user: UserLoginDataResponse? = SetUser()
    var imagee = ""
    var imageLoader = ImageLoader()
    private val imageList = imageLoader.LoadPhotos()

    fun GetUser(): UserLoginDataResponse? {
        return user
    }

    fun SetUser(): UserLoginDataResponse? {
        val user = GetLocalUser()
        return user
    }
    fun SetImageList(): List<Any> {
        return imageList
    }

    @SuppressLint("Range")
    @Composable
    fun NewImage(context: Context = LocalContext.current): ManagedActivityResultLauncher<String, Uri?> {
        var filePath by remember { mutableStateOf<String?>(null) }
        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri!!.scheme.equals("content")) {
                val cursor: Cursor? = context.getContentResolver().query(uri, null, null, null, null)
                try {
                    if (cursor != null && cursor.moveToFirst()) {
                        var fileName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                        val iStream : InputStream = context.contentResolver.openInputStream(uri!!)!!
                        val outputDir : File = context.cacheDir
                        val outputFile : File = File(outputDir,fileName)
                        copyStreamToFile(iStream, outputFile)
                        iStream.close()
                        filePath = outputFile.invariantSeparatorsPath
                    }
                } finally { cursor!!.close() }
            }
        }
        if (filePath != null && filePath.toString() != imagee) {
            imageList.add(ProfileState.ImageUri(filePath))
            imageLoader.SavePhotos(imageList)
            imagee = filePath.toString()
        }
        return launcher
    }

    fun copyStreamToFile(inputStream: InputStream, outputFile: File) {
        inputStream.use { input ->
            val outputStream = FileOutputStream(outputFile)
            outputStream.use { output ->
                val buffer = ByteArray(4 * 1024) // buffer size
                while (true) {
                    val byteCount = input.read(buffer)
                    if (byteCount < 0) break
                    output.write(buffer, 0, byteCount)
                }
                output.flush()
            }
        }
    }

}
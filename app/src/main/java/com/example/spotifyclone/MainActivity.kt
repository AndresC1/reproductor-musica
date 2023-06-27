package com.example.spotifyclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.spotifyclone.ViewModel.SongViewModel
import com.example.spotifyclone.databinding.ActivityListSongBinding
import com.example.spotifyclone.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAuth: FirebaseAuth
    private val Google_SIGN_IN = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        logeo()

        binding.btnOpenWindow.setOnClickListener{
            val intent = Intent(this, ListSong::class.java)
            startActivity(intent)
        }
    }
    fun logeo(){
        binding.btnOpenGoogle.setOnClickListener{
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            val googleClient = GoogleSignIn.getClient(this, googleConf)
            val signInIntent = googleClient.signInIntent
            startActivityForResult(signInIntent, Google_SIGN_IN)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == Google_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                if (account!=null){
                    firebaseAuthWithGoogle(account.idToken!!)
                }else{
                    Toast.makeText(this, "correo no existe", Toast.LENGTH_SHORT).show()
                }
            }catch (e:ApiException){
                Log.w("Tag", "google sign in failed $e")
            }

        }
    }

    private fun firebaseAuthWithGoogle(idToken: String){
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    val user = mAuth.currentUser?.email.toString()
                    val name = mAuth.currentUser?.displayName.toString()
                    login(user, name)
                }else{
                    Toast.makeText(this, "no se pudo logear", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
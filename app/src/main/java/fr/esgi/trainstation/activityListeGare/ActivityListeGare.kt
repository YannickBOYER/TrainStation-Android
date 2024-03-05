package fr.esgi.trainstation.activityListeGare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.esgi.trainstation.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ActivityListeGare : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch {
            getGareFromLocation()
        }
        setContentView(R.layout.activity_liste_gare)
    }

    private suspend fun getGareFromLocation() {
        val gares = ListeGareApi.getGaresFromLocation()
        print(gares)
    }
}
package  com.vasquez.recyclerview_list.heroeDatail

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vasquez.recyclerview_list.R
import com.vasquez.recyclerview_list.HeroeList.HEROE_ID



class HeroeMainActivity : AppCompatActivity() {

    private val heroeMainViewModel by viewModels<HeroeDetailViewModel> {
        HeroeDetailViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.heroe_detail_activity)

        var currentHeroeId: Long? = null

        /* Connect variables to UI elements. */
        val heroeName: TextView = findViewById(R.id.heroe_detail_name)
        val heroeImage: ImageView = findViewById(R.id.heroe_detail_image)
        val heroeDescription: TextView = findViewById(R.id.heroe_detail_description)
        val removeHeroeButton: Button = findViewById(R.id.remove_button)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            currentHeroeId = bundle.getLong(HEROE_ID)
        }

        /* If currentFlowerId is not null, get corresponding flower and set name, image and
        description */
        currentHeroeId?.let {
            val currentHeroe = heroeMainViewModel.getHeroeForId(it)
            heroeName.text = currentHeroe?.name
            if (currentHeroe?.image == null) {
                heroeImage.setImageResource(R.drawable.ic_add_black_24dp)
            } else {
                heroeImage.setImageResource(currentHeroe.image)
            }
            heroeDescription.text = currentHeroe?.description

            removeHeroeButton.setOnClickListener {
                if (currentHeroe != null) {
                    heroeMainViewModel.removeFlower(currentHeroe)
                }
                finish()
            }
        }

    }
}
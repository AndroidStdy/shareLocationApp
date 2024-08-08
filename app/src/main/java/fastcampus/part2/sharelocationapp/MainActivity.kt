package fastcampus.part2.sharelocationapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.common.util.Utility
import fastcampus.part2.sharelocationapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var keyHash = Utility.getKeyHash(this)
        println(keyHash)
        Log.e("KeyHash", keyHash.toString())

        startActivity(Intent(this,LoginActivity::class.java))


    }
}
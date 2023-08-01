package kartikey.dailyround.self.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.AndroidEntryPoint
import kartikey.dailyround.self.PrefUtil
import kartikey.dailyround.self.R
import kartikey.dailyround.self.login.LoginActivity
import kartikey.dailyround.self.network.ResultData
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var textView: TextView
    private lateinit var btn_Logout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        textView = findViewById<TextView>(R.id.tV_text);
        btn_Logout = findViewById<Button>(R.id.btn_Logout);

        PrefUtil(this).getLastLoginId.asLiveData().observe(this, Observer {
            setUpViewModel(it)
        })

        btn_Logout.setOnClickListener{
            runBlocking {
                PrefUtil(this@DashboardActivity).saveLastLogin("No")
                PrefUtil(this@DashboardActivity).saveLastLoginID(-1L)
            }
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun setUpViewModel(id: Long) {
        viewModel.getUserDataDetails(id)

        viewModel.getUserDataDetails.observe(this, Observer{
            when(it) {
                is ResultData.Success  -> {
                    textView.setText("Data: "+it.toString())
                }

                is ResultData.Failed  -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
                else -> {

                }

            }
        })
    }
}
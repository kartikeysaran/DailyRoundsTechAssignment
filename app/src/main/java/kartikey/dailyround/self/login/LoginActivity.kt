package kartikey.dailyround.self.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.asLiveData
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kartikey.dailyround.self.PrefUtil
import kartikey.dailyround.self.R
import kartikey.dailyround.self.dashboard.DashboardActivity
import kartikey.dailyround.self.network.ResultData
import kartikey.dailyround.self.registration.RegistrationActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var eT_email: EditText;
    private lateinit var eT_password: EditText;
    private lateinit var btn_Login: Button;
    private lateinit var tV_register: TextView;
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        PrefUtil(this).getLastLogin.asLiveData().observe(this, Observer {
            if (it == "Yes") {
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            }
        })

        setUpViews();
        tV_register.setOnClickListener{
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
        btn_Login.setOnClickListener{
            setUpViewModel()
        }
    }



    fun setUpViews() {
        eT_email = findViewById(R.id.eT_username);
        eT_password = findViewById(R.id.eT_password);
        btn_Login = findViewById(R.id.btn_login);
        tV_register = findViewById(R.id.tV_register);
    }

    private fun setUpViewModel() {
        viewModel.getUserLoginDataStatus(
            eT_email.text.toString(),
            eT_password.text.toString()
        )

        viewModel.getUserLoginDataStatus.observe(this, Observer {
            when (it) {

                is ResultData.Success  -> {
                    Log.d("it.messageSuccess = ", it.toString())
                    if (it.data != null) {
                        runBlocking {
                            PrefUtil(this@LoginActivity).saveLastLoginID(it.data.id)
                            PrefUtil(this@LoginActivity).saveLastLogin("Yes")
                        }
                        startActivity(Intent(this, DashboardActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "User does not exist in database.", Toast.LENGTH_LONG)
                            .show()
                    }

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
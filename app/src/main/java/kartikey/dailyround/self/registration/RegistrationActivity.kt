package kartikey.dailyround.self.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kartikey.dailyround.self.PrefUtil
import kartikey.dailyround.self.R
import kartikey.dailyround.self.dashboard.DashboardActivity
import kartikey.dailyround.self.db.UserEntity
import kartikey.dailyround.self.network.ResultData
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class RegistrationActivity : AppCompatActivity() {

    private val viewModel: RegistrationViewModel by viewModels()
    val TAG = RegistrationActivity::class.java.simpleName
    private lateinit var iV_back: ImageView
    var arraylist = ArrayList<String>()
    private lateinit var spinner: Spinner
    private lateinit var eT_email: EditText
    private lateinit var eT_password: EditText
    private lateinit var eT_confirmPassword: EditText
    private lateinit var btn_Register: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        setObserver()
        setViews()
        System.out.println(" Data " + viewModel.data.value)
    }

    private fun setObserver() {
        viewModel.data.observe(this@RegistrationActivity) {
            when (it) {
                is ResultData.Loading -> {
                    //binding.progressPb.visibility = View.VISIBLE
                }

                is ResultData.Success -> {
                    //binding.progressPb.visibility = View.GONE
                    Log.d(TAG, "VALUES FROM API call:- ${it.data}")
                    Toast.makeText(this, "Values fetched in LOGCAT", Toast.LENGTH_SHORT).show()
                    //it.data.getAsJsonObject("data")
                }

                else -> {
                    //binding.progressPb.visibility = View.GONE
                    Toast.makeText(
                        this@RegistrationActivity,
                        "Something went wrong",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    private fun setViews() {
        findViewById<ImageView?>(R.id.iV_back).setOnClickListener {
            finish()
        }
        val countries = resources.getStringArray(R.array.countries)
        // access the spinner
        eT_email = findViewById(R.id.eT_email_id)
        eT_password = findViewById(R.id.eT_password)
        eT_confirmPassword = findViewById(R.id.eT_confirm_password)
        btn_Register = findViewById(R.id.btn_register)
        spinner = findViewById<Spinner>(R.id.sP_countries)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, countries
            )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        this@RegistrationActivity,
                        "Selected Country" + " " +
                                "" + countries[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        btn_Register.setOnClickListener {
            if (validation()) {
                val users = UserEntity(
                    email = eT_email.text.toString(),
                    password = eT_password.text.toString(),
                    country = spinner.selectedItem.toString()
                )
                viewModel.insertUserData(users)
                viewModel.insertUsersDataStatus.observe(this, Observer {
                    when (it) {
                        is ResultData.Success -> {
                            //binding!!.progressCircular.visibility = View.GONE
                            startActivity(Intent(this, DashboardActivity::class.java))
                            runBlocking {
                                PrefUtil(this@RegistrationActivity).saveLastLogin("Yes")
                                PrefUtil(this@RegistrationActivity).saveLastLoginID(it.data!!)
                            }
                            finish()
                        }

                        is ResultData.Loading -> {
                            //binding!!.progressCircular.visibility = View.VISIBLE
                        }

                        is ResultData.Failed -> {
                            it.message?.let { it1 -> Log.d("it.message ", it1) }
                            Toast.makeText(
                                this@RegistrationActivity,
                                "Error Occurred",
                                Toast.LENGTH_SHORT
                            )
                        }

                        else -> {

                        }
                    }
                })
            }
        }

    }

    private fun validation(): Boolean {
//        val regex: String = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$"
//        if(eT_password.text.toString().matches(regex))
        return when {
            eT_email.text.isNullOrEmpty() -> {
                eT_email.setError("Required")
                android.widget.Toast.makeText(
                    this@RegistrationActivity,
                    "Enter Email",
                    android.widget.Toast.LENGTH_SHORT
                );
                false
            }

            eT_password.text.isNullOrEmpty() -> {
                eT_password.setError("Required")
                android.widget.Toast.makeText(
                    this@RegistrationActivity,
                    "Enter Password",
                    android.widget.Toast.LENGTH_SHORT
                );
                false
            }

            eT_confirmPassword.text.isNullOrEmpty() -> {
                eT_confirmPassword.setError("Required")
                android.widget.Toast.makeText(
                    this@RegistrationActivity,
                    "Enter Password",
                    android.widget.Toast.LENGTH_SHORT
                );
                false
            }

            spinner.selectedItem.toString().isNullOrEmpty() -> {
                android.widget.Toast.makeText(
                    this@RegistrationActivity,
                    "Enter Country",
                    android.widget.Toast.LENGTH_SHORT
                );
                false
            }

            checkEmail(eT_email.text.toString()) == false -> {
                android.widget.Toast.makeText(
                    this@RegistrationActivity,
                    "Invalid Email",
                    android.widget.Toast.LENGTH_SHORT
                );
                eT_email.setError("Email ID")
                false
            }

            checkPassword(eT_password.text.toString()) == false -> {
                android.widget.Toast.makeText(
                    this@RegistrationActivity,
                    "Invalid Password",
                    android.widget.Toast.LENGTH_SHORT
                );
                eT_password.setError("min 8 characters with atleast one number, special characters, one lowercase letter, and one uppercase letter")
                false
            }

            (eT_password.text.toString() != eT_confirmPassword.text.toString()) == true -> {
                android.widget.Toast.makeText(
                    this@RegistrationActivity,
                    "Re-enter password does not matches the Password",
                    android.widget.Toast.LENGTH_SHORT
                );
                return false
            }

            else -> {
                true
            }
        }
    }

    fun checkPassword(args: String): Boolean {
        var re = Regex("(?=[A-Za-z0-9@#\$%^&+!=]+\$)^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#\$%^&+!=])(?=.{8,}).*\$")
        return args.matches(re)
    }

    fun checkEmail(args: String): Boolean {
        var re = Regex("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\$")
        return args.matches(re)
    }
}

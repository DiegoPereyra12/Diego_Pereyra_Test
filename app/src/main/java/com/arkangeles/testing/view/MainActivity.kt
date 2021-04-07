package com.arkangeles.testing.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.arkangeles.testing.R
import com.arkangeles.testing.databinding.ActivityMainBinding
import com.arkangeles.testing.presenter.IMainView
import com.arkangeles.testing.presenter.MainPresenter
import com.arkangeles.testing.presenter.MainPresenterImpl

class MainActivity : AppCompatActivity(), IMainView {

    lateinit var binding: ActivityMainBinding

    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainPresenterImpl(this, this)

        binding.btnMaps.setOnClickListener {
            presenter.onButtonMapsClick()
        }
        binding.btnOther.setOnClickListener {
            presenter.onButtonOtherClick()
        }
        binding.btnRefresh.setOnClickListener {
            presenter.onButtonRefreshClick()
        }
    }

    override fun loadFragment(fragment: Fragment?) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment!!)
        transaction.commit()
    }

    override fun showErrorToast() {
        Toast.makeText(
            applicationContext,
            getString(R.string.error_message),
            Toast.LENGTH_SHORT
        ).show()
    }

}
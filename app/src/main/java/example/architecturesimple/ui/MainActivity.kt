package example.architecturesimple.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import example.architecturesimple.Response
import example.architecturesimple.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userAdapter = UserAdapter()
        binding.rvUsers.adapter = userAdapter

        binding.btnTryAgain.setOnClickListener {
            it.visibility = View.GONE
            binding.tvConnectionError.visibility = View.GONE
            viewModel.retryUsersFetch()
        }

        subscribeUI(binding, userAdapter)
    }

    private fun subscribeUI(
        binding: ActivityMainBinding,
        userAdapter: UserAdapter
    ) {
        viewModel.usersResponse.observe(this, Observer { response ->
            when (response.status) {
                Response.Status.SUCCESS -> {
                    response.data.let { userAdapter.submitList(it) }
                    binding.prLoadingUsers.visibility = View.GONE
                }
                Response.Status.LOADING -> binding.prLoadingUsers.visibility = View.VISIBLE
                Response.Status.ERROR -> {
                    binding.prLoadingUsers.visibility = View.GONE
                    binding.btnTryAgain.visibility = View.VISIBLE
                    binding.tvConnectionError.visibility=View.VISIBLE
                    Snackbar.make(binding.root, response.message!!, Snackbar.LENGTH_SHORT).show()
                }
            }
        })
    }
}
package example.architecturesimple.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import example.architecturesimple.data.UsersRepository
import example.architecturesimple.util.Response
import example.architecturesimple.data.User

class UsersViewModel @ViewModelInject constructor(private val repository: UsersRepository) :
    ViewModel() {

    private var usersFetched: MutableLiveData<Boolean?> =
        MutableLiveData(false)  // used for retry functionality

    val usersResponse: LiveData<Response<out List<User>>>

    init {
        usersResponse =
            usersFetched.switchMap {
                repository.getUsersResponse()
            }
    }

    fun retryUsersFetch() {
        usersFetched.value = true
    }
}
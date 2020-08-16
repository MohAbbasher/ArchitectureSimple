package example.architecturesimple.data

import androidx.lifecycle.liveData
import example.architecturesimple.util.Response
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val remote: ApiService
) {

    fun getUsersResponse() = liveData(Dispatchers.IO) {
        emit(Response.loading(data = null))
        try {
            emit(Response.success(data = remote.getUsers()))

        } catch (e: Exception) {
            emit(
                Response.error(
                    data = null,
                    message = e.message ?: "Error occurred!"
                )
            )
        }
    }
}
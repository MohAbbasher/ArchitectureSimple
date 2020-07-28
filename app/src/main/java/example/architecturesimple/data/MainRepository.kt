package example.architecturesimple.data

import androidx.lifecycle.liveData
import example.architecturesimple.util.Response
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val remote: ApiService
) {

    fun getUsersResponse() = liveData(Dispatchers.IO) {
        emit(Response.loading(data = null))
        try {
            emit(Response.success(data = remote.getUser()))

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

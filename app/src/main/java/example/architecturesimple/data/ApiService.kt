package example.architecturesimple.data

import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUser(): List<User>
}

package example.architecturesimple.util

data class Response<T>(var status: Status, var data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): Response<T> {
            return Response(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(message: String, data: T? = null): Response<T> {
            return Response(
                Status.ERROR,
                data,
                message
            )
        }

        fun <T> loading(data: T? = null): Response<T> {
            return Response(
                Status.LOADING,
                data,
                null
            )
        }
    }
}

package hr.fer.ruazosa.networkquiz.net

object RestFactory {
    //ipconfig IPv4 address
    const val BASE_IP = "192.168.1.5"
    //emulator
    //const val BASE_IP = "10.0.2.2"

    val instance: RestInterface
        get() = RestRetrofit()

}
package kroote.test

import libetal.kotlinx.kapt.plugins.kroote.annotations.routes.GET
import libetal.kotlinx.kapt.plugins.kroote.annotations.routes.PARAM
import libetal.kotlinx.kapt.plugins.kroote.annotations.routes.POST
import libetal.kotlinx.kapt.plugins.kroote.annotations.routes.ROUTE


@ROUTE("/api/users")
object Users {

    @GET(customCode = "call.respond(HttpStatusCode.InternalServerError,all)")
    val all = ""

    @POST(authenticator = "Smile")
    fun test() {

    }

    @POST(authenticator = "Smile")
    fun test2(@PARAM("defaultParameter") name: String) {
        println(name)
    }

    @POST
    fun nullableOnes(@PARAM("defaultvalue") name: String?) {

    }

}


@ROUTE("/api/users")
object Account {
    @GET(authenticator = "smile")
    val all = ""


}
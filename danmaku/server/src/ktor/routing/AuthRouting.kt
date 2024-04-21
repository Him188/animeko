package me.him188.ani.danmaku.server.ktor.routing

import io.bkbn.kompendium.core.metadata.PostInfo
import io.bkbn.kompendium.core.plugin.NotarizedRoute
import io.bkbn.kompendium.oas.payload.Parameter
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import me.him188.ani.danmaku.server.service.AuthService
import me.him188.ani.danmaku.server.service.JwtTokenManager
import org.koin.ktor.ext.inject

fun Route.authRouting() {
    val service: AuthService by inject()
    val jwtTokenManager: JwtTokenManager by inject()

    route("/login/bangumi") {
        documentation()
        post {
            val bangumiToken = call.receive<String>()
            val userId = service.loginBangumi(bangumiToken)
            val userToken = jwtTokenManager.createToken(userId)
            call.respond(userToken)
        }
    }
}

private fun Route.documentation() {
    install(NotarizedRoute()) {
        post = PostInfo.builder {
            summary("使用Bangumi token登录")
            description("使用Bangumi token登录并获取用户会话token")
            request {
                requestType<String>()
                description("Bangumi token字符串")
//                examples("Example Value" to String())
            }
            response {
                responseCode(HttpStatusCode.OK)
                responseType<String>()
                description("用户会话token字符串")
            }
            canRespond {
                responseCode(HttpStatusCode.Unauthorized)
                responseType<String>()
                description("Bangumi token无效")
            }
        }
    }
}
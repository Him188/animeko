package me.him188.ani.danmaku.server.service

import me.him188.ani.danmaku.server.data.UserRepository
import me.him188.ani.danmaku.server.util.exception.OperationFailedException
import me.him188.ani.danmaku.server.util.exception.UnauthorizedException
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface AuthService {
    suspend fun loginBangumi(bangumiToken: String): String
}

class AuthServiceImpl : AuthService, KoinComponent {
    private val bangumiLoginHelper: BangumiLoginHelper by inject()
    private val userRepository: UserRepository by inject()

    override suspend fun loginBangumi(bangumiToken: String): String {
        val bangumiUser = bangumiLoginHelper.login(bangumiToken) ?: throw UnauthorizedException()
        val userId = userRepository.getUserIdOrNull(bangumiUser.id) ?: run {
            registerAndGetId(bangumiUser)
        }
        return userId
    }

    private suspend fun registerAndGetId(user: BangumiUser): String {
        return userRepository.addAndGetId(
            bangumiId = user.id,
            nickname = user.nickname,
            smallAvatar = user.smallAvatar,
            mediumAvatar = user.mediumAvatar,
            largeAvatar = user.largeAvatar
        ) ?: throw OperationFailedException()
    }
}
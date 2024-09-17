/*
 * Copyright (C) 2024 OpenAni and contributors.
 *
 * 此源代码的使用受 GNU AFFERO GENERAL PUBLIC LICENSE version 3 许可证的约束, 可以在以下链接找到该许可证.
 * Use of this source code is governed by the GNU AGPLv3 license, which can be found at the following link.
 *
 * https://github.com/open-ani/ani/blob/main/LICENSE
 */

@file:Suppress("ConstPropertyName")

package me.him188.ani.app.ui.foundation.theme

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import me.him188.ani.app.ui.foundation.animation.StandardAccelerate
import me.him188.ani.app.ui.foundation.animation.StandardDecelerate

@Stable
object AniThemeDefaults {
    // 参考 M3 配色方案:
    // https://m3.material.io/styles/color/roles#63d6db08-59e2-4341-ac33-9509eefd9b4f

    /**
     * Navigation rail on desktop, bottom navigation on mobile.
     */
    val navigationContainerColor
        @Composable
        get() = MaterialTheme.colorScheme.surfaceContainer

    // Use surface roles for more neutral backgrounds, and container colors for components like cards, sheets, and dialogs.
    val appContentPaneColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.surface

    @Composable
    fun topAppBarColors(): TopAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        scrolledContainerColor = MaterialTheme.colorScheme.surfaceContainer,
    )

    /**
     * 仅充当背景作用的卡片颜色, 例如 RSS 设置页中的那些圆角卡片背景
     */
    @Composable
    fun backgroundCardColors(): CardColors =
        CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            contentColor = contentColorFor(MaterialTheme.colorScheme.surfaceContainerLow),
        )

    /**
     * 适用于整个 pane 都是一堆卡片, 而且这些卡片有一定的作用. 例如追番列表的卡片.
     */
    @Composable
    fun primaryCardColors(): CardColors =
        CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            contentColor = contentColorFor(MaterialTheme.colorScheme.surfaceContainerHigh),
        )

    /**
     * 适用中小型组件.
     */
    @Stable
    val standardAnimatedContentTransition: AnimatedContentTransitionScope<*>.() -> ContentTransform = {
        // Follow M3 Clean fades
        val fadeIn = fadeIn(
            animationSpec = tween(
                EasingDurations.standardDecelerate,
                delayMillis = EasingDurations.standardAccelerate,
                easing = StandardDecelerate,
            ),
        )
        val fadeOut = fadeOut(animationSpec = tween(EasingDurations.standardAccelerate, easing = StandardAccelerate))
        fadeIn.togetherWith(fadeOut)
    }
}

/**
 * M3 推荐的 [tween] 动画时长
 */
@Stable
object EasingDurations {
    // https://m3.material.io/styles/motion/easing-and-duration/applying-easing-and-duration#6409707e-1253-449c-b588-d27fe53bd025
    const val emphasized = 500
    const val emphasizedDecelerate = 400
    const val emphasizedAccelerate = 200
    const val standard = 300
    const val standardDecelerate = 250
    const val standardAccelerate = 200
}
package me.him188.ani.android

import me.him188.ani.android.ui.event.AndroidSubjectNavigator
import me.him188.ani.app.navigation.SubjectNavigator
import org.koin.dsl.module

val AndroidModules = module {
    single<SubjectNavigator> { AndroidSubjectNavigator() }
}
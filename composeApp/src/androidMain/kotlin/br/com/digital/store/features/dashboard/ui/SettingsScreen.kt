package br.com.digital.store.features.dashboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.digital.store.R
import br.com.digital.store.components.strings.StringsUtils.CHANGE_TO_OTHER_ACCOUNT
import br.com.digital.store.features.components.OptionButton
import br.com.digital.store.theme.Themes

@Composable
fun SettingsScreen(
    goToLoginScreen: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .background(color = Themes.colors.background)
            .fillMaxSize()
            .padding(all = Themes.size.spaceSize36)
    ) {
        OptionButton(
            icon = R.drawable.sync,
            label = CHANGE_TO_OTHER_ACCOUNT,
            onClick = goToLoginScreen
        )
    }
}

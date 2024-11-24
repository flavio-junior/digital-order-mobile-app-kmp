package br.com.digital.store.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import br.com.digital.store.components.ui.LoadingButton
import br.com.digital.store.components.ui.ObserveNetworkStateHandler
import br.com.digital.store.components.ui.SimpleText
import br.com.digital.store.components.ui.TextField
import br.com.digital.store.components.ui.TextPassword
import br.com.digital.store.networking.data.dto.SignInRequestDTO
import br.com.digital.store.networking.data.dto.TokenResponseDTO
import br.com.digital.store.networking.domain.type.TypeAccount
import br.com.digital.store.networking.utils.ObserveNetworkStateHandler
import br.com.digital.store.networking.utils.errorResult
import br.com.digital.store.networking.viewmodel.ApiViewModel
import br.com.digital.store.strings.StringsUtils.CREATE_ONE_ACCOUNT
import br.com.digital.store.strings.StringsUtils.EMAIL
import br.com.digital.store.strings.StringsUtils.ENTER_YOUR_ACCOUNT
import br.com.digital.store.strings.StringsUtils.FORGOT_PASS
import br.com.digital.store.strings.StringsUtils.NOT_BLANK_OR_EMPTY
import br.com.digital.store.strings.StringsUtils.OR
import br.com.digital.store.strings.StringsUtils.PASSWORD
import br.com.digital.store.strings.isNotBlankAndEmpty
import br.com.digital.store.theme.Themes
import br.com.digital.store.utils.CommonUtils.EMPTY_TEXT
import br.com.digital.store.utils.onBorder
import lojavirtual.composeapp.generated.resources.Res
import lojavirtual.composeapp.generated.resources.mail
import org.koin.mp.KoinPlatform.getKoin

@Composable
fun CardSignIn(
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .onBorder(
                onClick = {},
                spaceSize = Themes.size.spaceSize36,
                width = Themes.size.spaceSize2,
                color = Themes.colors.primary
            )
            .background(color = Themes.colors.background)
            .padding(all = Themes.size.spaceSize36)
            .size(size = Themes.size.spaceSize400),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = Themes.size.spaceSize16)
        ) {
            val viewModel: ApiViewModel = getKoin().get()
            var email: String by remember { mutableStateOf(value = EMPTY_TEXT) }
            var password: String by remember { mutableStateOf(value = EMPTY_TEXT) }
            var isError: Boolean by remember { mutableStateOf(value = false) }
            var errorMessage: String by remember { mutableStateOf(value = EMPTY_TEXT) }
            var isEnabled: Boolean by remember { mutableStateOf(value = false) }
            val checkSignIn = { emailArg: String, passwordArg: String ->
                checkDataToSignIn(
                    triple = Triple(first = emailArg, second = passwordArg, third = viewModel),
                    onError = {
                        isError = it.first
                        isEnabled = it.second
                        errorMessage = it.third
                    }
                )
            }
            GetDataInputsSignIn(
                email = email,
                password = password,
                checkSignIn = checkSignIn,
                isError = isError,
                errorMessage = errorMessage,
                onValueChange = {
                    email = it.first
                    password = it.second
                }
            )
            ObserveStateSignIn(
                viewModel = viewModel,
                onError = {
                    isError = it.first
                    isEnabled = it.second
                    errorMessage = it.third
                },
                goToLoginScreen = { onClick() },
                goToErrorScreen = {}
            )
            SimpleText(
                text = FORGOT_PASS,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )
            LoadingButton(
                onClick = {
                    isEnabled = true
                    checkSignIn(email, password)
                },
                isEnabled = isEnabled,
                label = ENTER_YOUR_ACCOUNT
            )
            SimpleText(
                text = OR,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            LoadingButton(
                label = CREATE_ONE_ACCOUNT,
                onClick = {}
            )
        }
    }
}

@Composable
private fun GetDataInputsSignIn(
    email: String,
    password: String,
    checkSignIn: (String, String) -> Unit,
    isError: Boolean,
    errorMessage: String,
    onValueChange: (Pair<String, String>) -> Unit
) {
    var emailMutable by remember { mutableStateOf(value = email) }
    var passwordMutable by remember { mutableStateOf(value = password) }
    TextField(
        label = EMAIL,
        value = emailMutable,
        icon = Res.drawable.mail,
        keyboardType = KeyboardType.Email,
        isError = isError,
        onValueChange = { emailMutable = it }
    )
    TextPassword(
        label = PASSWORD,
        value = passwordMutable,
        isError = isError,
        message = errorMessage,
        onValueChange = { passwordMutable = it },
        onGo = { checkSignIn(emailMutable, passwordMutable) }
    )
    onValueChange(Pair(first = emailMutable, second = passwordMutable))
}

@Composable
private fun ObserveStateSignIn(
    viewModel: ApiViewModel,
    onError: (Triple<Boolean, Boolean, String>) -> Unit = {},
    goToLoginScreen: (TypeAccount) -> Unit = {},
    goToErrorScreen: () -> Unit = {}
) {
    val accountState: ObserveNetworkStateHandler<TokenResponseDTO> by remember {
        viewModel.signIn
    }
    ObserveNetworkStateHandler(
        resultState = accountState,
        onError = {
            errorResult(
                description = accountState.exception,
                message = {
                    onError(Triple(first = true, second = false, third = it))
                },
                openErrorScreen = { goToErrorScreen() }
            )
        },
        onSuccess = {
            onError(Triple(first = false, second = false, third = EMPTY_TEXT))
            accountState.result?.let {
                viewModel.resetStateSignIn()
                goToLoginScreen(it.type)
            }
        }
    )
}

private fun checkDataToSignIn(
    triple: Triple<String, String, ApiViewModel>,
    onError: (Triple<Boolean, Boolean, String>) -> Unit = {},
) {
    if (triple.first.isNotBlankAndEmpty() && triple.second.isNotBlankAndEmpty()) {
        onError(Triple(first = false, second = true, third = EMPTY_TEXT))
        triple.third.signIn(SignInRequestDTO(email = triple.first, password = triple.second))
    } else {
        onError(Triple(first = true, second = false, third = NOT_BLANK_OR_EMPTY))
    }
}

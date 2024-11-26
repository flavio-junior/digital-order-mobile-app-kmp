package br.com.digital.store.ui.view.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.digital.store.common.category.vo.CategoryResponseVO
import br.com.digital.store.components.ui.Description
import br.com.digital.store.theme.Themes
import br.com.digital.store.utils.onClickable

@Composable
fun ListCategories(
    modifier: Modifier = Modifier,
    categories: List<CategoryResponseVO>,
    onItemSelected: (CategoryResponseVO) -> Unit = {}
) {
    Column(
        modifier = modifier
            .background(color = Themes.colors.success)
    ) {
        categories.forEach {
            Description(
                description = it.name,
                modifier = modifier.onClickable { onItemSelected(it) })
        }
    }
}

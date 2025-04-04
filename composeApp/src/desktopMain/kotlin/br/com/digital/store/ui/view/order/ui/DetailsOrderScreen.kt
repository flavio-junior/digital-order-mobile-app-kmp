package br.com.digital.store.ui.view.order.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.digital.store.components.ui.ResourceUnavailable
import br.com.digital.store.features.networking.resources.AlternativesRoutes
import br.com.digital.store.features.order.data.vo.ObjectResponseVO
import br.com.digital.store.features.order.data.vo.OrderResponseVO
import br.com.digital.store.features.order.domain.type.TypeOrder
import br.com.digital.store.utils.NumbersUtils.NUMBER_ZERO

@Composable
fun DetailsOrderScreen(
    orderResponseVO: OrderResponseVO,
    goToAlternativeRoutes: (AlternativesRoutes?) -> Unit = {},
    onItemSelected: (Pair<OrderResponseVO, Int>) -> Unit = {},
    objectSelected: (Pair<Long, ObjectResponseVO>) -> Unit = {},
    onRefresh: () -> Unit = {}
) {
    if (orderResponseVO.id > NUMBER_ZERO) {
        when (orderResponseVO.type) {
            TypeOrder.DELIVERY -> DeliveryDetailsScreen(
                orderResponseVO = orderResponseVO,
                onItemSelected = onItemSelected,
                objectSelected = objectSelected,
                goToAlternativeRoutes = goToAlternativeRoutes,
                onRefresh = onRefresh
            )

            TypeOrder.ORDER -> OrderDetailsScreen(
                orderResponseVO = orderResponseVO,
                onItemSelected = onItemSelected,
                objectSelected = objectSelected,
                goToAlternativeRoutes = goToAlternativeRoutes,
                onRefresh = onRefresh
            )

            TypeOrder.RESERVATION -> ReservationDetailsScreen(
                orderResponseVO = orderResponseVO,
                onItemSelected = onItemSelected,
                objectSelected = objectSelected,
                goToAlternativeRoutes = goToAlternativeRoutes,
                onRefresh = onRefresh
            )

            else -> {}
        }
    } else {
        ResourceUnavailable(modifier = Modifier.fillMaxSize())
    }
}

package br.com.digital.store.features.order.ui.view

import br.com.digital.store.features.order.utils.OrderUtils.ADD_MORE_ITEMS_ORDER
import br.com.digital.store.features.order.utils.OrderUtils.ALTER_ADDRESS
import br.com.digital.store.features.order.utils.OrderUtils.DETAILS_ORDER
import br.com.digital.store.features.order.utils.OrderUtils.PENDING_ORDERS

enum class ItemsOrder(val text: String) {
    PendingOrders(text = PENDING_ORDERS),
    DetailsOrder(text = DETAILS_ORDER),
    AlterAddress(text = ALTER_ADDRESS),
    AddItems(text = ADD_MORE_ITEMS_ORDER)
}

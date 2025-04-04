package br.com.digital.store.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.digital.store.ui.view.category.CategoryScreen
import br.com.digital.store.ui.view.dashboard.DashboardScreen
import br.com.digital.store.ui.view.employee.EmployeeScreen
import br.com.digital.store.ui.view.fee.FeeScreen
import br.com.digital.store.ui.view.food.FoodScreen
import br.com.digital.store.ui.view.item.ItemScreen
import br.com.digital.store.ui.view.login.SignInScreen
import br.com.digital.store.ui.view.order.ui.OrderScreen
import br.com.digital.store.ui.view.payment.PaymentScreen
import br.com.digital.store.ui.view.pdv.PdvScreen
import br.com.digital.store.ui.view.product.ProductScreen
import br.com.digital.store.ui.view.report.ReportScreen
import br.com.digital.store.ui.view.reservation.ReservationScreen
import br.com.digital.store.ui.view.resume.ResumeScreen
import br.com.digital.store.ui.view.settings.ui.CheckUpdatesSystemScreen
import br.com.digital.store.ui.view.settings.ui.SettingsScreen
import br.com.digital.store.ui.view.user.UserConfigScreen

@Composable
fun Navigation(
    navController: NavHostController,
    startDestination: String = RouteApp.SignIn.item
) {
    NavHost(navController = navController, startDestination = startDestination) {
        signInNavigation(navController = navController)
        dashboardNavigation(navController = navController)
        settingsNavigation(navController = navController)
    }
}

fun NavGraphBuilder.signInNavigation(
    navController: NavHostController
) {
    composable(RouteApp.SignIn.item) {
        SignInScreen(
            goToDashboardScreen = {
                navController.navigate(route = RouteApp.Dashboard.item) {
                    popUpTo(RouteApp.SignIn.item) {
                        inclusive = true
                    }
                }
            },
            goToAlternativeRoutes = {
                navigateToAlternativeRoutes(
                    navController = navController,
                    currentScreen = RouteApp.SignIn.item,
                    alternativeRoutes = it
                )
            }
        )
    }
}

fun NavGraphBuilder.dashboardNavigation(
    navController: NavHostController
) {
    composable(RouteApp.Dashboard.item) {
        DashboardScreen(
            goToNextScreen = {
                navigateBetweenMainRoutes(navGraph = navController, route = it)
            }
        )
    }

    composable(RouteApp.Pdv.item) {
        PdvScreen(
            goToBackScreen = {
                navController.popBackStack()
            },
            goToNextScreen = {
                goToNextScreen(
                    navHostController = navController,
                    currentScreen = RouteApp.Pdv.item,
                    nextScreen = it
                )
            }
        )
    }
    composable(RouteApp.Category.item) {
        CategoryScreen(
            goToBackScreen = {
                navController.popBackStack()
            },
            goToNextScreen = {
                goToNextScreen(
                    navHostController = navController,
                    currentScreen = RouteApp.Category.item,
                    nextScreen = it
                )
            },
            goToAlternativeRoutes = {
                navigateToAlternativeRoutes(
                    navController = navController,
                    currentScreen = RouteApp.Category.item,
                    alternativeRoutes = it
                )
            }
        )
    }
    composable(RouteApp.Item.item) {
        ItemScreen(
            goToBackScreen = {
                navController.popBackStack()
            },
            goToNextScreen = {
                goToNextScreen(
                    navHostController = navController,
                    currentScreen = RouteApp.Item.item,
                    nextScreen = it
                )
            },
            goToAlternativeRoutes = {
                navigateToAlternativeRoutes(
                    navController = navController,
                    currentScreen = RouteApp.Item.item,
                    alternativeRoutes = it
                )
            }
        )
    }
    composable(RouteApp.Food.item) {
        FoodScreen(
            goToBackScreen = {
                navController.popBackStack()
            },
            goToNextScreen = {
                goToNextScreen(
                    navHostController = navController,
                    currentScreen = RouteApp.Food.item,
                    nextScreen = it
                )
            },
            goToAlternativeRoutes = {
                navigateToAlternativeRoutes(
                    navController = navController,
                    currentScreen = RouteApp.Food.item,
                    alternativeRoutes = it
                )
            }
        )
    }
    composable(RouteApp.Product.item) {
        ProductScreen(
            goToBackScreen = {
                navController.popBackStack()
            },
            goToNextScreen = {
                goToNextScreen(
                    navHostController = navController,
                    currentScreen = RouteApp.Product.item,
                    nextScreen = it
                )
            },
            goToAlternativeRoutes = {
                navigateToAlternativeRoutes(
                    navController = navController,
                    currentScreen = RouteApp.Product.item,
                    alternativeRoutes = it
                )
            }
        )
    }
    composable(RouteApp.Order.item) {
        OrderScreen(
            goToBackScreen = {
                navController.popBackStack()
            },
            goToNextScreen = {
                goToNextScreen(
                    navHostController = navController,
                    currentScreen = RouteApp.Order.item,
                    nextScreen = it
                )
            }
        )
    }
    composable(RouteApp.Reservation.item) {
        ReservationScreen(
            goToBackScreen = {
                navController.popBackStack()
            },
            goToNextScreen = {
                goToNextScreen(
                    navHostController = navController,
                    currentScreen = RouteApp.Reservation.item,
                    nextScreen = it
                )
            },
            goToAlternativeRoutes = {
                navigateToAlternativeRoutes(
                    navController = navController,
                    currentScreen = RouteApp.Reservation.item,
                    alternativeRoutes = it
                )
            }
        )
    }
    composable(RouteApp.Payment.item) {
        PaymentScreen(
            goToBackScreen = {
                navController.popBackStack()
            },
            goToNextScreen = {
                goToNextScreen(
                    navHostController = navController,
                    currentScreen = RouteApp.Payment.item,
                    nextScreen = it
                )
            }
        )
    }
    composable(RouteApp.Report.item) {
        ReportScreen(
            goToBackScreen = {
                navController.popBackStack()
            },
            goToNextScreen = {
                goToNextScreen(
                    navHostController = navController,
                    currentScreen = RouteApp.Report.item,
                    nextScreen = it
                )
            },
            goToAlternativeRoutes = {
                navigateToAlternativeRoutes(
                    navController = navController,
                    currentScreen = RouteApp.Report.item,
                    alternativeRoutes = it
                )
            }
        )
    }
    composable(RouteApp.Resume.item) {
        ResumeScreen(
            goToBackScreen = {
                navController.popBackStack()
            },
            goToNextScreen = {
                goToNextScreen(
                    navHostController = navController,
                    currentScreen = RouteApp.Resume.item,
                    nextScreen = it
                )
            },
            goToAlternativeRoutes = {
                navigateToAlternativeRoutes(
                    navController = navController,
                    currentScreen = RouteApp.Resume.item,
                    alternativeRoutes = it
                )
            }
        )
    }
    composable(RouteApp.Settings.item) {
        SettingsScreen(
            goToBackScreen = {
                navController.popBackStack()
            },
            goToNextScreen = {
                goToNextScreen(
                    navHostController = navController,
                    currentScreen = RouteApp.Settings.item,
                    nextScreen = it
                )
            }
        )
    }
}

fun NavGraphBuilder.settingsNavigation(
    navController: NavHostController
) {
    composable(RouteApp.Fee.item) {
        FeeScreen(
            goToBackScreen = {
                navController.popBackStack()
            },
            goToNextScreen = {
                goToNextScreen(
                    navHostController = navController,
                    currentScreen = RouteApp.Fee.item,
                    nextScreen = it
                )
            },
            goToAlternativeRoutes = {
                navigateToAlternativeRoutes(
                    navController = navController,
                    currentScreen = RouteApp.Fee.item,
                    alternativeRoutes = it
                )
            }
        )
    }

    composable(RouteApp.CheckUpdates.item) {
        CheckUpdatesSystemScreen(
            goToBackScreen = {
                navController.popBackStack()
            },
            goToNextScreen = {
                goToNextScreen(
                    navHostController = navController,
                    currentScreen = RouteApp.Fee.item,
                    nextScreen = it
                )
            }
        )
    }

    composable(RouteApp.Employee.item) {
        EmployeeScreen(
            goToBackScreen = {
                navController.popBackStack()
            },
            goToNextScreen = {
                goToNextScreen(
                    navHostController = navController,
                    currentScreen = RouteApp.Employee.item,
                    nextScreen = it
                )
            }
        )
    }

    composable(RouteApp.UserConfig.item) {
        UserConfigScreen(
            goToBackScreen = {
                navController.popBackStack()
            },
            goToNextScreen = {
                goToNextScreen(
                    navHostController = navController,
                    currentScreen = RouteApp.UserConfig.item,
                    nextScreen = it
                )
            }
        )
    }
}

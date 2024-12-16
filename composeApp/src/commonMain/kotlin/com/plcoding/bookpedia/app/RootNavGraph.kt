package com.plcoding.bookpedia.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.plcoding.bookpedia.book.presentation.SelectedBookViewModel
import com.plcoding.bookpedia.book.presentation.booklist.BookListScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RootNavGraph(navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = Route.BookGraph
    ){

        navigation<Route.BookGraph>(
            startDestination = Route.BookList
        ){
            composable<Route.BookList> {

                val selectedBookViewModel = it.sharedKoinViewModel<SelectedBookViewModel>(navController)

                LaunchedEffect(true){
                    selectedBookViewModel.onSelectBook(null)
                }

                BookListScreen(
                    navigateToBookDetail = { book ->
                        selectedBookViewModel.onSelectBook(book)
                        navController.navigate(Route.BookDetail(book.id))
                    }
                )
            }

            composable<Route.BookDetail> { entry ->
                val selectedBookViewModel = entry.sharedKoinViewModel<SelectedBookViewModel>(navController)

                //val args = entry.toRoute<Route.BookDetail>()

            }
        }
    }
}



@Composable
private inline fun <reified T: ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}
package com.plcoding.bookpedia.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.plcoding.bookpedia.book.presentation.SelectedBookViewModel
import com.plcoding.bookpedia.book.presentation.bookdetail.BookDetailEvent
import com.plcoding.bookpedia.book.presentation.bookdetail.BookDetailScreen
import com.plcoding.bookpedia.book.presentation.bookdetail.BookDetailViewModel
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

                //val args = entry.toRoute<Route.BookDetail>()

                val selectedBookViewModel = entry.sharedKoinViewModel<SelectedBookViewModel>(navController)
                val selectedBook by selectedBookViewModel.selectedBook.collectAsStateWithLifecycle()
                val viewModel = koinViewModel<BookDetailViewModel>()

                LaunchedEffect(selectedBook){
                    selectedBook?.let {
                        viewModel.onTriggerEvent(BookDetailEvent.OnSelectedBookChange(it))
                    }
                }

                BookDetailScreen(
                    onBackClick = { navController.navigateUp() }
                )
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
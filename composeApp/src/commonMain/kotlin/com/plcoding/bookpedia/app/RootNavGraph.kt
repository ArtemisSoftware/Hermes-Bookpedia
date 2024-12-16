package com.plcoding.bookpedia.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.plcoding.bookpedia.book.presentation.booklist.BookListScreen

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
                BookListScreen(
                    navigateToBookDetail = { book ->
                        navController.navigate(Route.BookDetail(book.id))
                    }
                )
            }

            composable<Route.BookDetail> { entry ->
                val args = entry.toRoute<Route.BookDetail>()

            }
        }
    }
}
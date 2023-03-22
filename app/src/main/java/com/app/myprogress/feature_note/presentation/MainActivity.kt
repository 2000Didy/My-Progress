package com.app.myprogress.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.myprogress.feature_note.presentation.ajout_note_edit.AjoutEcranEditNote
import com.app.myprogress.feature_note.presentation.notes.composantes.EcranNote
import com.app.myprogress.feature_note.presentation.outils.Ecran
import com.app.myprogress.ui.theme.MyProgressTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
        @ExperimentalAnimationApi
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                MyProgressTheme() {
                    Surface(
                        color = MaterialTheme.colors.background
                    ) {
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = Ecran.EcranNotes.route
                        ) {
                            composable(route = Ecran.EcranNotes.route) {
                                EcranNote(navController = navController)
                            }
                            composable(
                                route = Ecran.AjoutEcranEditNote.route +
                                        "?noteId={noteId}&noteColor={noteColor}",
                                arguments = listOf(
                                    navArgument(
                                        name = "noteId"
                                    ) {
                                        type = NavType.IntType
                                        defaultValue = -1
                                    },
                                    navArgument(
                                        name = "noteColor"
                                    ) {
                                        type = NavType.IntType
                                        defaultValue = -1
                                    },
                                )
                            ) {
                                val color = it.arguments?.getInt("noteColor") ?: -1
                                AjoutEcranEditNote(
                                    navController = navController,
                                    noteColor = color
                                )
                            }
                        }
                    }
                }
            }
        }
    }
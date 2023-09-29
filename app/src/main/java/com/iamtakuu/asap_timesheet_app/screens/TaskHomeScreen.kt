package com.iamtakuu.asap_timesheet_app.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.iamtakuu.asap_timesheet_app.R
import com.iamtakuu.asap_timesheet_app.data.tasks.model.Task
import com.iamtakuu.asap_timesheet_app.navigation.graphs.HomeNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskHomeScreen(navController: NavHostController = rememberNavController()) {
        Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        HomeNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Task,
        BottomBarScreen.Categories,
        BottomBarScreen.Stats,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        NavigationBar {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}

@Composable
fun AuthorAndReadTime(
    task: Task,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        Text(
            text = stringResource(
                id = R.string.home_task_creation_time,
//                formatArgs = arrayOf(
//                    post.metadata.author.name,
//                    post.metadata.readTimeMinutes
//                )
            ),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun PostImage(task: Task, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(task.imageThumbId),
        contentDescription = null, // decorative
        modifier = modifier
            .size(40.dp, 40.dp)
            .clip(MaterialTheme.shapes.small)
    )
}

@Composable
fun TaskTitle(task: Task) {
    Text(
        text = task.title,
        style = MaterialTheme.typography.titleMedium,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
fun TaskCardSimple(
    task: Task,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit
) {
    val bookmarkAction = stringResource(if (isFavorite) R.string.unbookmark else R.string.bookmark)
    Row(
        modifier = Modifier
            .clickable(onClick = { })
            .semantics {
                // By defining a custom action, we tell accessibility services that this whole
                // composable has an action attached to it. The accessibility service can choose
                // how to best communicate this action to the user.
                customActions = listOf(
                    CustomAccessibilityAction(
                        label = bookmarkAction,
                        action = { onToggleFavorite(); true }
                    )
                )
            }
    ) {
        PostImage(task, Modifier.padding(16.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 10.dp)
        ) {
            TaskTitle(task)
            AuthorAndReadTime(task)
        }
//        BookmarkButton(
//            isBookmarked = isFavorite,
//            onClick = onToggleFavorite,
//            // Remove button semantics so action can be handled at row level
//            modifier = Modifier
//                .clearAndSetSemantics {}
//                .padding(vertical = 2.dp, horizontal = 6.dp)
//        )
    }
}
val task3 = Task(
    id = "ac552dcc1741",
    title = "Finish this app.",
    subtitle = "Learn how to get started converting Java Programming Language code to Kotlin, making it more idiomatic and avoid common pitfalls, by…",
    imageId = R.drawable.post_3,
    imageThumbId = R.drawable.post_3_thumb
)
val task4 = Task(
    id = "ac552dcc1741",
    title = "Fix the bugs.",
    subtitle = "Learn how to get started converting Java Programming Language code to Kotlin, making it more idiomatic and avoid common pitfalls, by…",
    imageId = R.drawable.post_3,
    imageThumbId = R.drawable.post_4_thumb
)
val task5 = Task(
    id = "ac552dcc1741",
    title = "Go to bed.",
    subtitle = "Learn how to get started converting Java Programming Language code to Kotlin, making it more idiomatic and avoid common pitfalls, by…",
    imageId = R.drawable.post_3,
    imageThumbId = R.drawable.post_5_thumb
)

@Preview
@Composable
fun TaskCardPrev(){
    TaskCardSimple(task = task3, isFavorite = true) {
    }
}
//@Composable
//fun BookmarkButton(id: String, isBookmarked: Boolean, onToggleBookmark: (String) -> Unit) {
//    Image(
//        provider = ImageProvider(
//            if (isBookmarked) {
//                R.drawable.ic_jetnews_bookmark_filled
//            } else {
//                R.drawable.ic_jetnews_bookmark
//            }
//        ),
//        colorFilter = ColorFilter.tint(GlanceTheme.colors.primary),
//        contentDescription = "${if (isBookmarked) R.string.unbookmark else R.string.bookmark}",
//        modifier = GlanceModifier.clickable { onToggleBookmark(id) }
//    )
//}
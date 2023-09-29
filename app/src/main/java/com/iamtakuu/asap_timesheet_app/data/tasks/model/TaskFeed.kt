package com.iamtakuu.asap_timesheet_app.data.tasks.model

/**
 * A container of [Task]s, partitioned into different categories.
 */
data class TaskFeed(
    val highlightedPost: Task,
    val recentPosts: List<Task>,
) {
    /**
     * Returns a flattened list of all posts contained in the feed.
     */
    val allPosts: List<Task> =
        listOf(highlightedPost) + recentPosts
}
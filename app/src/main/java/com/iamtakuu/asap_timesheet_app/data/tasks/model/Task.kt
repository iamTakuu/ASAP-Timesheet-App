package com.iamtakuu.asap_timesheet_app.data.tasks.model

import androidx.annotation.DrawableRes
data class Task(
val id: String,
val title: String,
val subtitle: String? = null,
//val metadata: Metadata,
val paragraphs: List<Paragraph> = emptyList(),
@DrawableRes val imageId: Int,
@DrawableRes val imageThumbId: Int
)
data class Paragraph(
    val type: ParagraphType,
    val text: String,
    val markups: List<Markup> = emptyList()
)

enum class ParagraphType {
    Title,
    Caption,
    Header,
    Subhead,
    Text,
    CodeBlock,
    Quote,
    Bullet,
}
data class Markup(
    val type: MarkupType,
    val start: Int,
    val end: Int,
    val href: String? = null
)
enum class MarkupType {
    Link,
    Code,
    Italic,
    Bold,
}

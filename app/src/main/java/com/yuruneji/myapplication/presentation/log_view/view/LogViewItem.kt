package com.yuruneji.myapplication.presentation.log_view.view

/**
 * @author henriko
 * @version 1.0
 */
data class LogViewItem(
    val uid: Int,
    val date: String,
    val priority: Int,
    val tag: String?,
    val message: String
)

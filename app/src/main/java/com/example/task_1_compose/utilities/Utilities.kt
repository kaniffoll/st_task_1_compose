package com.example.task_1_compose.utilities

fun String.getInitials(): String {
    return this[0].toString() + (if (this.trimEnd()
            .indexOf(' ') != -1
    ) this[this.indexOf(' ') + 1] else "")
}
package com.sunshine.mylibrary.network

/**
 * @author SunShine-Joex
 * @date   2020-09-11
 * @desc
 */
data class TextBean(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)
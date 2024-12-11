package com.yuruneji.myapplication.domain.repository

import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

/**
 * @author henriko
 * @version 1.0
 */
interface LogRepository {
    fun log(date: LocalDateTime): Flow<List<com.yuruneji.myapplication.data.local.db.LogEntity>>
    fun log(date: LocalDateTime, priority: IntArray): Flow<List<com.yuruneji.myapplication.data.local.db.LogEntity>>
    fun log(from: LocalDateTime, to: LocalDateTime, priority: IntArray): Flow<List<com.yuruneji.myapplication.data.local.db.LogEntity>>
}

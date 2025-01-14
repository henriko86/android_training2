package com.yuruneji.myapplication.presentation.log_view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.yuruneji.myapplication.data.local.db.LogEntity
import com.yuruneji.myapplication.domain.repository.LogRepository
import com.yuruneji.myapplication.presentation.log_view.state.LogPeriod
import com.yuruneji.myapplication.presentation.log_view.state.LogViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class LogViewViewModel @Inject constructor(
    private val repository: LogRepository
) : ViewModel() {

    private val _logViewState = MutableLiveData(LogViewState())
    val logViewState: LiveData<LogViewState> = _logViewState

    fun setDate(date: LocalDate) {
        _logViewState.value = _logViewState.value?.copy(
            date = date
        )
    }

    // fun setTime(time: LocalTime) {
    //     _logViewState.value = _logViewState.value?.copy(
    //         time = time
    //     )
    // }

    fun setPeriod(period: LogPeriod) {
        _logViewState.value = _logViewState.value?.copy(
            period = period
        )
    }

    fun setPriorityVerbose(priorityVerbose: Boolean) {
        _logViewState.value = _logViewState.value?.copy(
            priorityVerbose = priorityVerbose
        )
    }

    fun setPriorityDebug(priorityDebug: Boolean) {
        _logViewState.value = _logViewState.value?.copy(
            priorityDebug = priorityDebug
        )
    }

    fun setPriorityInfo(priorityInfo: Boolean) {
        _logViewState.value = _logViewState.value?.copy(
            priorityInfo = priorityInfo
        )
    }

    fun setPriorityWarn(priorityWarn: Boolean) {
        _logViewState.value = _logViewState.value?.copy(
            priorityWarn = priorityWarn
        )
    }

    fun setPriorityError(priorityError: Boolean) {
        _logViewState.value = _logViewState.value?.copy(
            priorityError = priorityError
        )
    }

    fun setPriorityAssert(priorityAssert: Boolean) {
        _logViewState.value = _logViewState.value?.copy(
            priorityAssert = priorityAssert
        )
    }


    // 検索条件のLiveData
    private val searchCond = MutableLiveData<LogViewState>()

    // MainActivityから検索条件を設定してやる関数
    fun setSelectCond(searchCd: LogViewState) {
        searchCond.value = searchCd
    }

    // 検索する関数
    fun selectData(): LiveData<List<LogEntity>> = searchCond.switchMap { searchCd ->
        if (searchCd == null) {
            MutableLiveData()
        } else {
            val priority = mutableListOf<Int>()
            if (searchCd.priorityVerbose) {
                priority.add(Log.VERBOSE)
            }
            if (searchCd.priorityDebug) {
                priority.add(Log.DEBUG)
            }
            if (searchCd.priorityInfo) {
                priority.add(Log.INFO)
            }
            if (searchCd.priorityWarn) {
                priority.add(Log.WARN)
            }
            if (searchCd.priorityError) {
                priority.add(Log.ERROR)
            }
            if (searchCd.priorityAssert) {
                priority.add(Log.ASSERT)
            }


            // if (searchCd.time == null || searchCd.period == LogPeriod.DAY) {
            if (searchCd.period == LogPeriod.DAY) {
                val date = LocalDateTime.of(searchCd.date, LocalDateTime.MIN.toLocalTime())
                repository.log(date, priority.toIntArray()).asLiveData()
            } else {
                val to = LocalDateTime.of(searchCd.date, LocalDateTime.now().toLocalTime())

                val from = when (searchCd.period) {
                    LogPeriod.DAY -> to.minusDays(1)
                    LogPeriod.HALF_DAY -> to.minusHours(12)
                    LogPeriod.HOUR6 -> to.minusHours(6)
                    LogPeriod.HOUR3 -> to.minusHours(3)
                    LogPeriod.HOUR -> to.minusHours(1)
                }

                repository.log(from, to, priority.toIntArray()).asLiveData()
            }
        }
    }
}

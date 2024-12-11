package com.yuruneji.myapplication.common.enum

import android.hardware.camera2.CameraCharacteristics

/**
 * カメラタイプ
 * @author henriko
 * @version 1.0
 */
enum class LensFacing(val no: Int, val value: String) {
    FRONT(CameraCharacteristics.LENS_FACING_FRONT, "前カメラ"),
    BACK(CameraCharacteristics.LENS_FACING_BACK, "後カメラ");

    companion object {
        private val defItem = FRONT

        fun valueList() = enumValues<LensFacing>().map { it.value }

        fun toNo(value: String, defNo: Int = defItem.no): Int {
            for (item in enumValues<LensFacing>()) {
                if (item.value == value) {
                    return item.no
                }
            }
            return defNo
        }

        fun toValue(no: Int, defValue: String = defItem.value): String {
            for (item in enumValues<LensFacing>()) {
                if (item.no == no) {
                    return item.value
                }
            }
            return defValue
        }
    }
}

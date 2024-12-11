package com.yuruneji.myapplication.common.enum

/**
 * 顔検出サイズ
 * @author henriko
 * @version 1.0
 */
enum class MinFaceSize(val no: Int, val value: String, val size: Float) {
    FaceSize1(1, "0.45", 0.45f),
    FaceSize2(2, "0.35", 0.35f),
    FaceSize3(3, "0.25", 0.25f),
    FaceSize4(4, "0.15", 0.15f);

    companion object {
        private val defItem = FaceSize4

        fun valueList() = enumValues<MinFaceSize>().map { it.value }

        fun toValue(size: Float, defValue: String = defItem.value): String {
            for (item in enumValues<MinFaceSize>()) {
                if (item.size == size) {
                    return item.value
                }
            }
            return defValue
        }

        fun toSize(value: String, defSize: Float = defItem.size): Float {
            for (item in enumValues<MinFaceSize>()) {
                if (item.value == value) {
                    return item.size
                }
            }
            return defSize
        }
    }
}

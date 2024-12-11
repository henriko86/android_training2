package com.yuruneji.myapplication.common.enum

/**
 * APIタイプ
 * @author henriko
 * @version 1.0
 */
enum class ApiType(val no: Int, val value: String) {
    DEVELOP(1, "開発環境"),
    STAGING(2, "ステージング環境"),
    PRODUCTION(3, "本番環境");

    companion object {
        private val defItem = DEVELOP

        fun valueList() = enumValues<ApiType>().map { it.value }

        fun toNo(value: String, defNo: Int = defItem.no): Int {
            for (item in enumValues<ApiType>()) {
                if (item.value == value) {
                    return item.no
                }
            }
            return defNo
        }

        fun toValue(no: Int, defValue: String = defItem.value): String {
            for (item in enumValues<ApiType>()) {
                if (item.no == no) {
                    return item.value
                }
            }
            return defValue
        }
    }
}

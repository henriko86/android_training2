package com.yuruneji.myapplication.common.enum

/**
 * 認証方法
 * @author henriko
 * @version 1.0
 */
enum class AuthMethod(val no: Int, val value: String) {
    SINGLE(1, "単要素認証"),
    MULTI(2, "多要素認証");

    companion object {
        private val defItem = SINGLE

        fun valueList() = enumValues<AuthMethod>().map { it.value }

        fun toNo(value: String, defNo: Int = defItem.no): Int {
            for (item in enumValues<AuthMethod>()) {
                if (item.value == value) {
                    return item.no
                }
            }
            return defNo
        }

        fun toValue(no: Int, defValue: String = defItem.value): String {
            for (item in enumValues<AuthMethod>()) {
                if (item.no == no) {
                    return item.value
                }
            }
            return defValue
        }
    }
}

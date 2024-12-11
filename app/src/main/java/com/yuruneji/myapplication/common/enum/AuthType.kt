package com.yuruneji.myapplication.common.enum

/**
 * 認証タイプ
 * @author henriko
 * @version 1.0
 */
enum class AuthType(val no: Int, val value: String) {
    FACE(1, "顔認証"),
    CARD(2, "カード認証"),
    QR(3, "QRコード認証");

    companion object {
        private val defItem = FACE

        fun valueList() = enumValues<AuthType>().map { it.value }

        fun toNo(value: String, defNo: Int = defItem.no): Int {
            for (item in enumValues<AuthType>()) {
                if (item.value == value) {
                    return item.no
                }
            }
            return defNo
        }

        fun toValue(no: Int, defValue: String = defItem.value): String {
            for (item in enumValues<AuthType>()) {
                if (item.no == no) {
                    return item.value
                }
            }
            return defValue
        }
    }
}

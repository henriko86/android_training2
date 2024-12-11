package com.yuruneji.myapplication.common.enum

/**
 * 多要素認証タイプ
 * @author henriko
 * @version 1.0
 */
enum class MultiAuthType(val no: Int, val value: String) {
    CARD_FACE(1, "カード＆顔認証"),
    QR_FACE(2, "QRコード＆顔認証");

    companion object {
        private val defItem = CARD_FACE

        fun valueList() = enumValues<MultiAuthType>().map { it.value }

        fun toNo(value: String, defNo: Int = defItem.no): Int {
            for (item in enumValues<MultiAuthType>()) {
                if (item.value == value) {
                    return item.no
                }
            }
            return defNo
        }

        fun toValue(no: Int, defValue: String = defItem.value): String {
            for (item in enumValues<MultiAuthType>()) {
                if (item.no == no) {
                    return item.value
                }
            }
            return defValue
        }
    }
}

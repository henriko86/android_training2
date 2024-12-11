package com.yuruneji.myapplication.common

/**
 * 定数
 * @author henriko
 * @version 1.0
 */
object Constants {


    // /** URL */
    // const val BASE_URL = "http://192.168.11.2:8080"

    // /** カード認証　OR　顔認証 */
    // const val AUTH_TYPE_CARD_OR_FACE = 0

    // /** 顔認証 */
    // const val AUTH_TYPE_FACE = 1
    // /** カード認証 */
    // const val AUTH_TYPE_CARD = 2
    // /** カード認証　AND　顔認証 */
    // const val AUTH_TYPE_CARD_AND_FACE = 3
    // /** 前カメラ */
    // const val LENS_FACING_FRONT = CameraSelector.LENS_FACING_FRONT
    // /** 後ろカメラ */
    // const val LENS_FACING_BACK = CameraSelector.LENS_FACING_BACK

}

// /**
//  * カメラタイプ
//  */
// enum class LensFacing(val no: Int, val value: String) {
//     FRONT(CameraCharacteristics.LENS_FACING_FRONT, "前カメラ"),
//     BACK(CameraCharacteristics.LENS_FACING_BACK, "後カメラ");
//
//     companion object {
//         private val defItem = FRONT
//
//         fun valueList() = enumValues<LensFacing>().map { it.value }
//
//         fun toNo(value: String, defNo: Int = defItem.no): Int {
//             for (item in enumValues<LensFacing>()) {
//                 if (item.value == value) {
//                     return item.no
//                 }
//             }
//             return defNo
//         }
//
//         fun toValue(no: Int, defValue: String = defItem.value): String {
//             for (item in enumValues<LensFacing>()) {
//                 if (item.no == no) {
//                     return item.value
//                 }
//             }
//             return defValue
//         }
//     }
// }

// /**
//  * APIタイプ
//  */
// enum class ApiType(val no: Int, val value: String) {
//     DEVELOP(1, "開発環境"),
//     STAGING(2, "ステージング環境"),
//     PRODUCTION(3, "本番環境");
//
//     companion object {
//         private val defItem = DEVELOP
//
//         fun valueList() = enumValues<ApiType>().map { it.value }
//
//         fun toNo(value: String, defNo: Int = defItem.no): Int {
//             for (item in enumValues<ApiType>()) {
//                 if (item.value == value) {
//                     return item.no
//                 }
//             }
//             return defNo
//         }
//
//         fun toValue(no: Int, defValue: String = defItem.value): String {
//             for (item in enumValues<ApiType>()) {
//                 if (item.no == no) {
//                     return item.value
//                 }
//             }
//             return defValue
//         }
//     }
// }

// /**
//  * 認証方法
//  */
// enum class AuthMethod(val no: Int, val value: String) {
//     SINGLE(1, "単要素認証"),
//     MULTI(2, "多要素認証");
//
//     companion object {
//         private val defItem = SINGLE
//
//         fun valueList() = enumValues<AuthMethod>().map { it.value }
//
//         fun toNo(value: String, defNo: Int = defItem.no): Int {
//             for (item in enumValues<AuthMethod>()) {
//                 if (item.value == value) {
//                     return item.no
//                 }
//             }
//             return defNo
//         }
//
//         fun toValue(no: Int, defValue: String = defItem.value): String {
//             for (item in enumValues<AuthMethod>()) {
//                 if (item.no == no) {
//                     return item.value
//                 }
//             }
//             return defValue
//         }
//     }
// }

// /**
//  * 認証タイプ
//  */
// enum class AuthType(val no: Int, val value: String) {
//     FACE(1, "顔認証"),
//     CARD(2, "カード認証"),
//     QR(3, "QRコード認証");
//
//     companion object {
//         private val defItem = FACE
//
//         fun valueList() = enumValues<AuthType>().map { it.value }
//
//         fun toNo(value: String, defNo: Int = defItem.no): Int {
//             for (item in enumValues<AuthType>()) {
//                 if (item.value == value) {
//                     return item.no
//                 }
//             }
//             return defNo
//         }
//
//         fun toValue(no: Int, defValue: String = defItem.value): String {
//             for (item in enumValues<AuthType>()) {
//                 if (item.no == no) {
//                     return item.value
//                 }
//             }
//             return defValue
//         }
//     }
// }

// /**
//  * 多要素認証タイプ
//  */
// enum class MultiAuthType(val no: Int, val value: String) {
//     CARD_FACE(1, "カード＆顔認証"),
//     QR_FACE(2, "QRコード＆顔認証");
//
//     companion object {
//         private val defItem = CARD_FACE
//
//         fun valueList() = enumValues<MultiAuthType>().map { it.value }
//
//         fun toNo(value: String, defNo: Int = defItem.no): Int {
//             for (item in enumValues<MultiAuthType>()) {
//                 if (item.value == value) {
//                     return item.no
//                 }
//             }
//             return defNo
//         }
//
//         fun toValue(no: Int, defValue: String = defItem.value): String {
//             for (item in enumValues<MultiAuthType>()) {
//                 if (item.no == no) {
//                     return item.value
//                 }
//             }
//             return defValue
//         }
//     }
// }

// /**
//  * 顔検出サイズ
//  */
// enum class MinFaceSize(val no: Int, val value: String, val size: Float) {
//     FaceSize1(1, "0.45", 0.45f),
//     FaceSize2(2, "0.35", 0.35f),
//     FaceSize3(3, "0.25", 0.25f),
//     FaceSize4(4, "0.15", 0.15f);
//
//     companion object {
//         private val defItem = FaceSize4
//
//         fun valueList() = enumValues<MinFaceSize>().map { it.value }
//
//         fun toValue(size: Float, defValue: String = defItem.value): String {
//             for (item in enumValues<MinFaceSize>()) {
//                 if (item.size == size) {
//                     return item.value
//                 }
//             }
//             return defValue
//         }
//
//         fun toSize(value: String, defSize: Float = defItem.size): Float {
//             for (item in enumValues<MinFaceSize>()) {
//                 if (item.value == value) {
//                     return item.size
//                 }
//             }
//             return defSize
//         }
//     }
// }

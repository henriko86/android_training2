package com.yuruneji.myapplication.data.local.setting

import com.yuruneji.myapplication.common.enum.ApiType
import com.yuruneji.myapplication.common.enum.AuthMethod
import com.yuruneji.myapplication.common.enum.LensFacing
import com.yuruneji.myapplication.common.enum.MultiAuthType

/**
 * @author henriko
 * @version 1.0
 */
data class AppSettingModel(
    /** 使用カメラ */
    val lensFacing: LensFacing = LensFacing.FRONT,
    /** 画像幅 */
    val imageWidth: Int = 480,
    /** 画像高さ */
    val imageHeight: Int = 640,
    /** APIタイプ*/
    val apiType: ApiType = ApiType.DEVELOP,
    /** 認証方法 [単要素認証,多要素認証] */
    val authMethod: AuthMethod = AuthMethod.SINGLE,
    /** 多要素認証 [カード＆顔認証,QRコード＆顔認証] */
    val multiAuthType: MultiAuthType = MultiAuthType.QR_FACE,
    /** 顔認証 */
    val faceAuth: Boolean = false,
    /** カード認証 */
    val cardAuth: Boolean = false,
    /** QRコード認証 */
    val qrAuth: Boolean = false,
    /** 最小顔サイズ */
    val minFaceSize: Float = 0.15f,
)

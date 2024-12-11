package com.yuruneji.myapplication.domain.model

import android.graphics.Rect
import com.google.mlkit.vision.barcode.common.Barcode

/**
 * QRコード情報
 * @author henriko
 * @version 1.0
 */
data class QrItemModel(
    val rect: Rect,
    val barcode: Barcode
)

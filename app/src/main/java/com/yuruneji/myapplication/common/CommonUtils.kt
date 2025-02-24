package com.yuruneji.myapplication.common

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.graphics.ImageFormat
import android.graphics.Point
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Build.VERSION_CODES
import android.util.Size
import android.view.View
import android.view.WindowManager
import android.view.WindowMetrics
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * @author henriko
 * @version 1.0
 */
object CommonUtils {


    /**
     * カメラの解像度を取得
     * @param context Context
     * @param cameraID カメラID
     * @return カメラの解像度
     */
    fun getCameraOutputSizes(context: Context, cameraID: String): Array<Size>? {
        val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val sizeList = cameraManager.getCameraCharacteristics(cameraID)
            .get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)
            ?.getOutputSizes(ImageFormat.JPEG)
        return sizeList
    }

    /**
     * 後ろカメラIDを取得
     * @param context Context
     * @return 後ろカメラID
     */
    fun getBackCameraID(context: Context): String? {
        val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        cameraManager.cameraIdList.forEach {
            if (cameraManager.getCameraCharacteristics(it).get(CameraCharacteristics.LENS_FACING) == CameraCharacteristics.LENS_FACING_BACK) {
                return it
            }
        }
        return null
    }

    /**
     * 前カメラIDを取得
     * @param context Context
     * @return 前カメラID
     */
    fun getFrontCameraID(context: Context): String? {
        val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        cameraManager.cameraIdList.forEach {
            if (cameraManager.getCameraCharacteristics(it).get(CameraCharacteristics.LENS_FACING) == CameraCharacteristics.LENS_FACING_FRONT) {
                return it
            }
        }
        return null
    }

    /**
     * 画面の向きを取得
     * @param context Context
     * @return 画面の向き
     */
    fun getOrientation(context: Context): Int {
        val orientation = context.resources.configuration.orientation
        // when (orientation) {
        //     android.content.res.Configuration.ORIENTATION_PORTRAIT -> {
        //         println("縦")
        //     }
        //
        //     android.content.res.Configuration.ORIENTATION_LANDSCAPE -> {
        //         println("横")
        //     }
        // }
        return orientation
    }

    /**
     * 画面の回転を取得
     * @param context Context
     * @return 画面の回転
     */
    fun getRotation(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val rotation = windowManager.defaultDisplay.rotation
        // when (rotation) {
        //     //== 0度 ==//
        //     Surface.ROTATION_0 -> {}
        //     //== 90度 ==//
        //     Surface.ROTATION_90 -> {}
        //     //== 180度 ==//
        //     Surface.ROTATION_180 -> {}
        //     //== 270 ==//
        //     Surface.ROTATION_270 -> {}
        // }
        return rotation
    }

    /**
     * 画面サイズを取得
     * @param context
     * @return 画面サイズ
     */
    fun getWindowSize(context: Context): Size {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val size: Size
        if (Build.VERSION.SDK_INT <= VERSION_CODES.R) {
            val point = Point()
            windowManager.defaultDisplay.getRealSize(point)
            val width = point.x
            val height = point.y
            size = Size(width, height)
        } else {
            val wm: WindowMetrics = windowManager.currentWindowMetrics
            val width = wm.bounds.width()
            val height = wm.bounds.height()
            size = Size(width, height)
        }
        return size
    }

    fun getCameraImageSize(context: Context, width: Int, height: Int): Size {
        val orientation = getOrientation(context)
        val size = when (orientation) {
            Configuration.ORIENTATION_PORTRAIT -> { // 縦向きの場合
                Size(width, height)
            }

            Configuration.ORIENTATION_LANDSCAPE -> { // 横向きの場合
                Size(height, width)
            }

            else -> {
                Size(width, height)
            }
        }
        return size
    }

    // fun byteHexIntSum(list: List<Byte>): Int {
    //     var sum = 0
    //     for (b in list) {
    //         sum += byteHexIntValue(b)
    //     }
    //     return sum
    // }

    // fun byteHexIntValue(b: Byte): Int {
    //     val hex = String.format("%02x", b)
    //     val intValue = hex.toInt(16)
    //     return intValue
    // }

    // fun ascii2String(list: List<Byte>, def: String = ""): String {
    //     val data = ByteArray(list.size)
    //     if (list.isNotEmpty()) {
    //         for (i in list.indices) {
    //             data[i] = list[i]
    //         }
    //     }
    //     return ascii2String(data, def)
    // }

    // fun ascii2String(data: ByteArray, def: String = ""): String {
    //     try {
    //         return String(data, charset("US-ASCII"))
    //     } catch (e: UnsupportedEncodingException) {
    //         e.printStackTrace()
    //         return def
    //     }
    // }

    // fun string2Ascii(str: String): ByteArray {
    //     return str.toByteArray(Charsets.US_ASCII)
    // }

    // fun int2AsciiString(i: Int, separator: String = ""): String {
    //     return string2AsciiString(i.toString(), separator)
    // }

    // fun int2AsciiString(i: Int, size: Int, separator: String = ""): String {
    //     return string2AsciiString(String.format("%0${size}d", i), separator)
    // }

    // fun string2AsciiString(str: String, separator: String = ""): String {
    //     return str.toByteArray(Charsets.US_ASCII).joinToString(separator)
    // }

    /**
     *
     */
    fun textViewFaceOut(textView: TextView) {
        val alphaAnimation = AlphaAnimation(1f, 0f).also {
            it.duration = 1000
            it.fillAfter = false
            it.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    textView.visibility = View.VISIBLE
                }

                override fun onAnimationEnd(animation: Animation?) {
                    textView.visibility = View.INVISIBLE
                }

                override fun onAnimationRepeat(animation: Animation?) {
                    //
                }
            })
        }
        textView.startAnimation(alphaAnimation)
    }

    /**
     * フルスクリーン表示
     */
    fun fullscreen(activity: Activity, state: Boolean) {
        if (state) {
            activity.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            val flags = View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            activity.window?.decorView?.systemUiVisibility = flags
            (activity as? AppCompatActivity)?.supportActionBar?.hide()
        } else {
            activity.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            activity.window?.decorView?.systemUiVisibility = 0
            (activity as? AppCompatActivity)?.supportActionBar?.show()
        }
    }

    fun fullscreenToolbarFragment(activity: Activity, state: Boolean) {
        if (state) {
            // activity.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            val flags = View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    // View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            activity.window?.decorView?.systemUiVisibility = flags
            // (activity as? AppCompatActivity)?.supportActionBar?.hide()
        } else {
            activity.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            activity.window?.decorView?.systemUiVisibility = 0
            (activity as? AppCompatActivity)?.supportActionBar?.show()
        }
    }
}


inline fun <T : Any> guardLet(vararg elements: T?, closure: () -> Nothing): List<T> {
    return if (elements.all { it != null }) {
        elements.filterNotNull()
    } else {
        closure()
    }
}

inline fun <T : Any> ifLet(vararg elements: T?, closure: (List<T>) -> Unit) {
    if (elements.all { it != null }) {
        closure(elements.filterNotNull())
    }
}

// operator fun <E> List<E>.component6(): E {
//     return this[5]
// }
// example
// fun guardLetSample(var1 : Float?, var2 : Float?, var3 : Float?) {
//     // var1,var2,var3いずれかがnullなら早期リターンする
//     // 戻り値のvar1, var2, var3はアンラップされている
//     val (var1, var2, var3) = guardLet(var1, var2, var3){ return }
//
//     // 本来の処理
// }
//
// fun ifLetSample(var1 : Float?, var2 : Float?, var3 : Float?) {
//     ifLet(var1, var2, var3){ (var1, var2, var3) ->
//         // var1, var2, var3いずれもnullでないなら実行する処理
//         // var1, var2, var3はアンラップされている
//     }
// }

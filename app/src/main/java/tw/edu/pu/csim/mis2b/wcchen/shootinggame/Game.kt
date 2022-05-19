package tw.edu.pu.csim.mis2b.wcchen.shootinggame

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class Game(context: Context?, attrs: AttributeSet?) : SurfaceView(context, attrs), SurfaceHolder.Callback {
    var surfaceHolder: SurfaceHolder
    var BG:Bitmap
    var BGmoveX:Int = 0
    init {
        surfaceHolder = getHolder()
        BG = BitmapFactory.decodeResource(getResources(), R.drawable.background)
        surfaceHolder.addCallback(this)
    }


    override fun surfaceCreated(holder: SurfaceHolder) {
        var canvas: Canvas = surfaceHolder.lockCanvas()
            drawSomething(canvas)
        surfaceHolder.unlockCanvasAndPost(canvas)
    }

    fun drawSomething(canvas: Canvas) {
        var SrcRect: Rect = Rect(0, 0, BG.width, BG.height)
        var w: Int = width
        var h: Int = height
        BGmoveX--
        var BGnewX: Int = w + BGmoveX
        if (BGnewX <= 0) {
            BGmoveX = 0
            canvas.drawBitmap(BG, BGmoveX.toFloat(), 0f, null)
        } else {
            var DestRect: Rect = Rect(BGmoveX, 0, BGmoveX + w, h)
            canvas.drawBitmap(BG, SrcRect, DestRect, null)
            DestRect = Rect(BGnewX, 0, BGnewX + w, h)
            canvas.drawBitmap(BG, SrcRect, DestRect, null)
        }
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.BLUE
        paint.textSize = 50f
        canvas.drawText("射擊遊戲(作者：陳韋辰)",50f,50f, paint)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {

    }

}
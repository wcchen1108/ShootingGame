package tw.edu.pu.csim.mis2b.wcchen.shootinggame

import android.content.Context
import android.graphics.*
import android.media.MediaPlayer
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

class Game(context: Context?, attrs: AttributeSet?) : SurfaceView(context, attrs), SurfaceHolder.Callback, GestureDetector.OnGestureListener {
    var surfaceHolder: SurfaceHolder
    var BG:Bitmap
    var BGmoveX:Int = 0
    var fly:Fly
    var gDetector: GestureDetector
    var mper: MediaPlayer

    init {
        surfaceHolder = getHolder()
        BG = BitmapFactory.decodeResource(getResources(), R.drawable.background)
        surfaceHolder.addCallback(this)
        fly = Fly(context!!)
        gDetector = GestureDetector(context, this)
        mper = MediaPlayer()
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
        var DestRect: Rect = Rect(0, 0, w, h)
        var BGnewX: Int = w + BGmoveX

        if (BGnewX <= 0) {
            BGmoveX = 0
            canvas.drawBitmap(BG, BGmoveX.toFloat(), 0f, null)
        } else {
            DestRect = Rect(BGmoveX, 0, BGmoveX+w, h)
            canvas.drawBitmap(BG, SrcRect, DestRect, null)
            DestRect = Rect(BGnewX, 0, BGnewX+w, h)
            canvas.drawBitmap(BG, SrcRect, DestRect, null)
        }
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.BLUE
        paint.textSize = 50f
        canvas.drawText("射擊遊戲(作者：陳韋辰)",50f,50f, paint)

        fly.draw(canvas)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gDetector.onTouchEvent(event)
        return true
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }

    override fun onShowPress(e: MotionEvent?) {
        if (e!!.x >= 0 && e!!.x <= fly.w && e!!.y >= fly.y && e!!.y <= fly.y + fly.w) {
            fly.fire = 1
            mper = MediaPlayer.create(context, R.raw.shoot)
            mper.start()
        }
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return true
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        fly.y = e2!!.y.toInt() - fly.h/2
        return true
    }

    override fun onLongPress(e: MotionEvent?) {

    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return true
    }
}
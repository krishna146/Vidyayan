package com.bcebhagalpur.welcomeslider.helper

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import com.google.android.material.bottomnavigation.BottomNavigationView


class CurvedBottomNavigationView: BottomNavigationView {

    private var path: Path?=null
    private var paint:Paint?=null

    //radius
    var radius=90

    //coordinates 1
    var firstCurveStartPoint=Point()
    var firstCurveEndPoint=Point()
    var firstCurveControlPoint1=Point()
    var firstCurveControlPoint2=Point()

    //coordinates 2
    var secondCurveStartPoint=Point()
    var secondCurveEndPoint=Point()
    var secondCurveControlPoint1=Point()
    var secondCurveControlPoint2=Point()

    var navigationBarWidth:Int=0
    var navigationBarHeight:Int=0

    constructor(context: Context):super(context){
        init()
    }

    constructor(context: Context,attrs:AttributeSet):super(context,attrs){
        init()
    }
    constructor(context: Context,attrs:AttributeSet,style:Int):super(context,attrs,style){
        init()
    }

    private fun init() {
       path=Path()
        paint= Paint()
        paint!!.style=Paint.Style.FILL_AND_STROKE
        paint!!.color=Color.WHITE
        setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        navigationBarHeight=height
        navigationBarWidth=width

        firstCurveStartPoint.set(navigationBarWidth/2-radius*2 - radius/3,0)

        firstCurveEndPoint.set(navigationBarWidth/2, radius+radius/4)
        secondCurveStartPoint=firstCurveEndPoint
        secondCurveEndPoint.set(navigationBarWidth/2+radius*2+radius/3,0)
        firstCurveControlPoint1.set(firstCurveStartPoint.x+radius+radius/4,firstCurveStartPoint.y)
        firstCurveControlPoint2.set(firstCurveEndPoint.x-radius*2+radius,firstCurveEndPoint.y)

        secondCurveControlPoint1.set(secondCurveStartPoint.x+radius*2-radius,secondCurveStartPoint.y)
        secondCurveControlPoint2.set(secondCurveEndPoint.x-(radius+radius/4),secondCurveEndPoint.y)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        path!!.reset()
        path!!.moveTo(0f,0f)
        path!!.lineTo(firstCurveStartPoint.x.toFloat(),firstCurveStartPoint.y.toFloat())

        path!!.cubicTo(firstCurveControlPoint1.x.toFloat(),firstCurveControlPoint1.y.toFloat(),firstCurveControlPoint2.x.toFloat()
            ,firstCurveControlPoint2.y.toFloat(),firstCurveEndPoint.x.toFloat(),firstCurveEndPoint.y.toFloat())

        path!!.cubicTo(secondCurveControlPoint1.x.toFloat(),secondCurveControlPoint1.y.toFloat(),secondCurveControlPoint2.x.toFloat()
            ,secondCurveControlPoint2.y.toFloat(),secondCurveEndPoint.x.toFloat(),secondCurveEndPoint.y.toFloat())

        path!!.lineTo(navigationBarWidth.toFloat(),0f)
        path!!.lineTo(navigationBarWidth.toFloat(),navigationBarHeight.toFloat())
        path!!.lineTo(0f,navigationBarHeight.toFloat())
        path!!.close()

        canvas!!.drawPath(path!!,paint!!)
    }

}
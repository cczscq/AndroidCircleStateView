package cn.spade.android.circlestateview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhaosc on 15/6/3.
 */
public class CircleStateView extends View {

  private static final String TAG = CircleStateView.class.getSimpleName();


  /**控件支持属性默认值**/
  //完成状态的颜色
  private int csvWholeForegroundColor = -1;
  //内圈的背景色
  private int csvInnerBackgroundColor = -1;
  //内圈文字
  private String csvInnerText = null;
  //内圈文字颜色
  private int csvInnerTextColor = -1;
  //内圈水波颜色
  private int csvInnerWaterColor = -1;
  //内圈水波进度，max=100
  private int csvInnerWaterProgress = 0;
  //线条的颜色
  private int csvStrokeColor = -1;
  //线条的宽度
  private int csvStrokeWidth = -1;

  private Paint wholeForegroundPaint;
  private Paint innerBackgroundPaint;
  private Paint innerTextPaint;
  private Paint innerWaterPaint;
  private Paint strokePaint;

  private RectF wholeForegroundRectF;
  private RectF innerBackgroundRectF;
  private RectF innerWaterRectF;
  private RectF strokeRectF;

  private int minWidth;
  private Typeface textTypeface = null;

  public CircleStateView(Context context, AttributeSet attrs) {
	super(context, attrs);
    init(attrs);
  }

  public CircleStateView(Context context, AttributeSet attrs, int defStyleAttr) {
	super(context, attrs, defStyleAttr);
    init(attrs);
  }


  /**
   * 设置完成状态颜色
   * @param csvWholeForegroundColor
   * @return
   */
  public CircleStateView setCsvWholeForegroundColor(int csvWholeForegroundColor){
    this.csvWholeForegroundColor = getResources().getColor(csvWholeForegroundColor);
    return this;
  }

  /**
   * 设置内圈的背景色
   * @param csvInnerBackgroundColor
   * @return
   */
  public CircleStateView setCsvInnerBackgroundColor(int csvInnerBackgroundColor){
    this.csvInnerBackgroundColor = getResources().getColor(csvInnerBackgroundColor);
    return this;
  }

  /**
   * 设置内圈文字颜色
   * @param csvInnerTextColor
   * @return
   */
  public CircleStateView setCsvInnerTextColor(int csvInnerTextColor){
    this.csvInnerTextColor = getResources().getColor(csvInnerTextColor);
    return this;
  }

  /**
   * 设置内圈水波颜色
   * @param csvInnerWaterColor
   * @return
   */
  public CircleStateView setCsvInnerWaterColor(int csvInnerWaterColor){
    this.csvInnerWaterColor = getResources().getColor(csvInnerWaterColor);
    return this;
  }

  /**
   * 设置线条的颜色
   * @param csvStrokeColor
   * @return
   */
  public CircleStateView setCsvStrokeColor(int csvStrokeColor){
    this.csvStrokeColor = getResources().getColor(csvStrokeColor);
    return this;
  }

  /**
   * 线条的宽度
   * @param csvStrokeWidth
   * @return
   */
  public CircleStateView setCsvStrokeWidth(int csvStrokeWidth){
    this.csvStrokeWidth = csvStrokeWidth;
    return this;
  }

  /**
   * 内圈水波进度
   * @param csvInnerWaterProgress
   * @return
   */
  public CircleStateView setCsvWaterPgrogress(int csvInnerWaterProgress){
    this.csvInnerWaterProgress = csvInnerWaterProgress;
    return this;
  }

  /**
   * 设置文本字体
   * @param textTypeface
   * @return
   */
  public CircleStateView setCsvTextTypeface(Typeface textTypeface) {
    this.textTypeface = textTypeface;
    return this;
  }

  /**
   * 设置文本内容
   * @param csvInnerText
   * @return
   */
  public CircleStateView setCsvInnerText(int csvInnerText){
    this.csvInnerText = getResources().getString(csvInnerText);
    return  this;
  }

  /***
   * 设置文本内容
   * @param csvInnerText
   * @return
   */
  public CircleStateView setCsvInnerText(String csvInnerText){
    this.csvInnerText = csvInnerText;
    return  this;
  }

  /**
   * 刷新UI
   */
  public void refresh() {
    invalidate();
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
  }

  @Override
  protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
    if (changed){
      minWidth = Math.min(getWidth(), getHeight());
      int rLeft = csvStrokeWidth / 2;
      int rTop = csvStrokeWidth / 2;
      int rRight = minWidth - csvStrokeWidth / 2;
      int rBottom = minWidth - csvStrokeWidth / 2;
      innerBackgroundRectF.set(rLeft, rTop, rRight, rBottom);
      strokeRectF.set(rLeft, rTop, rRight, rBottom);
      wholeForegroundRectF.set(0, 0, minWidth, minWidth);
    }
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);


    if (csvInnerWaterProgress == 100){
      drawCsvWholeForeground(canvas);
    } else {
      drawCsvStroke(canvas);
      drawCsvInnerBackground(canvas);
      drawCsvInnerWater(canvas);
    }
    drawCsvInnerText(canvas);


  }

  private void init(AttributeSet attrs){

    csvWholeForegroundColor = getResources().getColor(R.color.default_whole_foreground);
    csvInnerBackgroundColor = getResources().getColor(R.color.default_inner_background);
    csvInnerTextColor = getResources().getColor(R.color.default_inner_text);
    csvInnerWaterColor = getResources().getColor(R.color.default_inner_water);
    csvStrokeColor = getResources().getColor(R.color.default_stroke);

    csvStrokeWidth = 6;
    csvInnerWaterProgress = 0;

    TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CircleStateView);
    csvWholeForegroundColor = typedArray.getColor(R.styleable.CircleStateView_csvWholeForegroundColor, csvWholeForegroundColor);
    csvInnerBackgroundColor = typedArray.getColor(R.styleable.CircleStateView_csvInnerBackgroundColor, csvInnerBackgroundColor);
    csvInnerText = typedArray.getString(R.styleable.CircleStateView_csvInnerText);
    csvInnerTextColor = typedArray.getColor(R.styleable.CircleStateView_csvInnerTextColor, csvInnerTextColor);
    csvInnerWaterColor = typedArray.getColor(R.styleable.CircleStateView_csvInnerWaterColor, csvInnerWaterColor);
    csvInnerWaterProgress = typedArray.getInt(R.styleable.CircleStateView_csvInnerWaterProgress, csvInnerWaterProgress);
    csvStrokeColor = typedArray.getColor(R.styleable.CircleStateView_csvStrokeColor, csvStrokeColor);
    csvStrokeWidth = typedArray.getDimensionPixelSize(R.styleable.CircleStateView_csvStrokeWidth, csvStrokeWidth);
    typedArray.recycle();

    initOther();
  }

  private void initOther(){
    initWholeForegroundPaint();
    initInnerBackgroundPaint();
    initInnerTextPaint();
    initInnerWaterPaint();
    initStrokePaint();

    initRectF();
  }

  private void initWholeForegroundPaint(){
    wholeForegroundPaint = new Paint();
    wholeForegroundPaint.setAntiAlias(true);
    wholeForegroundPaint.setColor(csvWholeForegroundColor);
  }

  private void initInnerBackgroundPaint(){
    innerBackgroundPaint  = new Paint();
    innerBackgroundPaint.setAntiAlias(true);
    innerBackgroundPaint.setColor(csvInnerBackgroundColor);

  }

  private void initInnerTextPaint(){
    innerTextPaint = new Paint();
    innerTextPaint.setAntiAlias(true);
    innerTextPaint.setColor(csvInnerTextColor);
  }

  private void initInnerWaterPaint(){
    innerWaterPaint = new Paint();
    innerWaterPaint.setAntiAlias(true);
    innerWaterPaint.setColor(csvInnerWaterColor);
  }

  private void initStrokePaint(){
    strokePaint = new Paint();
    strokePaint.setAntiAlias(true);
    strokePaint.setDither(true);
    strokePaint.setStyle(Paint.Style.STROKE);
    strokePaint.setStrokeCap(Paint.Cap.ROUND);
    strokePaint.setStrokeWidth(csvStrokeWidth);
    strokePaint.setColor(csvStrokeColor);
  }

  private void initRectF(){
    wholeForegroundRectF = new RectF();
    innerBackgroundRectF = new RectF();
    innerWaterRectF = new RectF();
    strokeRectF = new RectF();
  }


  private void drawCsvWholeForeground(Canvas canvas){
    canvas.save();
    canvas.drawCircle(wholeForegroundRectF.centerX(), wholeForegroundRectF.centerY(), wholeForegroundRectF.height() / 2, wholeForegroundPaint);
    canvas.restore();
  }

  private void drawCsvInnerBackground(Canvas canvas){
    canvas.save();
    canvas.drawArc(innerBackgroundRectF, 0, 360, false, innerBackgroundPaint);
    canvas.restore();
  }

  private void drawCsvInnerText(Canvas canvas){
    int textSize = (int) ((minWidth - csvStrokeWidth * 2) / 4);
    innerTextPaint.setTextSize(textSize);
    Paint.FontMetrics fm = innerTextPaint.getFontMetrics();
    float textHeight = (float) Math.ceil(fm.descent - fm.top);
    float textWidth = innerTextPaint.measureText(csvInnerText);
    float x = (minWidth - textWidth) / 2;
    float y = (minWidth - textHeight) / 2 + textSize;
    canvas.save();
    canvas.drawText(csvInnerText, x, y, innerTextPaint);
    canvas.restore();
  }

  private void drawCsvInnerWater(Canvas canvas){
    computerWaterRectF();
    canvas.save();
    canvas.clipRect(innerWaterRectF);
    canvas.drawArc(innerBackgroundRectF, 0, 360, false, innerWaterPaint);
    canvas.restore();
  }

  private void drawCsvStroke(Canvas canvas){
    canvas.save();
    canvas.drawArc(strokeRectF, 0, 360, false, strokePaint);
    canvas.restore();
  }

  private void computerWaterRectF(){
    innerWaterRectF = wholeForegroundRectF;
    if (csvInnerWaterProgress != 100){
      innerWaterRectF.top += (1 - csvInnerWaterProgress / 100.0f) * wholeForegroundRectF.height();
    }
  }


  private int measureWidth(int measureSpec) {
    int result = 0;
    int specMode = MeasureSpec.getMode(measureSpec);
    int specSize = MeasureSpec.getSize(measureSpec);

    if (specMode == MeasureSpec.EXACTLY) {
      result = specSize;
    } else {
      result = getPaddingLeft() + getPaddingRight();
      if (specMode == MeasureSpec.AT_MOST) {
        result = Math.min(result, specSize);// 60,480
      }
    }
    return result;
  }

  private int measureHeight(int measureSpec) {
    int result = 0;
    int specMode = MeasureSpec.getMode(measureSpec);
    int specSize = MeasureSpec.getSize(measureSpec);

    if (specMode == MeasureSpec.EXACTLY) {
      result = specSize;
    } else {
      result = getPaddingTop() + getPaddingBottom();
      if (specMode == MeasureSpec.AT_MOST) {
        result = Math.min(result, specSize);
      }
    }
    return result;
  }
}

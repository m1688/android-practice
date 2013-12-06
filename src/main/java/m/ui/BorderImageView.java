package m.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

public class BorderImageView extends ImageView {
	private static final String NAME_SPACE = "http://m.1688.com/android";
	private static final String BORDER_COLOR_NAME = "BorderColor";
	private int borderColor;
	private Paint paint;
	public BorderImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		borderColor = Color.parseColor(attrs.getAttributeValue(NAME_SPACE, BORDER_COLOR_NAME));
		
		paint=new Paint();
		paint.setColor(borderColor);
		paint.setStyle(Paint.Style.STROKE);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		Rect clipBounds = canvas.getClipBounds();
		clipBounds.bottom--;
		clipBounds.right--;
		
		canvas.drawRect(clipBounds, paint);
	}
}

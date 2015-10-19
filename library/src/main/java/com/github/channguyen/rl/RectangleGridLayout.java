package com.github.channguyen.rl;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * A container that arranges views into a gird of rectangles of the same sized
 */
public class RectangleGridLayout extends ViewGroup {

  private static final String TAG = RectangleGridLayout.class.getSimpleName();

  /**
   * Default number of column if not specified
   */
  private static final int DEFAULT_COLUMN = 4;

  /**
   * Default width for a child rectangle
   */
  private static final int DEFAULT_WIDTH = 400;

  /**
   * Default height for a child rectangle
   */
  private static final int DEFAULT_HEIGHT = 200;

  /**
   * The number of columns for this layout, row will be computed based on column and the number of
   * child views. Set in {@link #setColumn(int)} or from layout xm
   */
  private int column;

  public RectangleGridLayout(Context context) {
    super(context);
  }

  public RectangleGridLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.RectangleGridLayout);
    try {
      setColumn(a.getInt(R.styleable.RectangleGridLayout_rgl___column, DEFAULT_COLUMN));
    } finally {
      a.recycle();
    }
  }

  /**
   * Set the number of columns for rectangle each side of the square.
   *
   * @param column The column of rectangle, must be at least 1
   */
  public void setColumn(int column) {
    if (column < 1) {
      throw new IllegalArgumentException("column must be positive");
    }
    if (this.column != column) {
      this.column = column;
      requestLayout();
    }
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    // Compute padding for width & height
    final int paddingWidth = getPaddingLeft() + getPaddingRight();
    final int paddingHeight = getPaddingTop() + getPaddingBottom();

    // Total number of child
    final int count = getChildCount();
    final int row = ((count % column) == 0) ? (count / column) : ((count / column) + 1);
    int w;
    int h;
    int mw = Integer.MIN_VALUE;
    int mh = Integer.MIN_VALUE;
    int childMarginHeight = 0;
    int childMarginWidth = 0;
    for (int i = 0; i < count; ++i) {
      final View child = getChildAt(i);
      if (child.getVisibility() == View.GONE) {
        continue;
      }
      measureChild(child, widthMeasureSpec, heightMeasureSpec);
      final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
      childMarginWidth += (lp.leftMargin + lp.rightMargin);
      childMarginHeight += (lp.topMargin + lp.bottomMargin);
      mw = Math.max(mw, child.getMeasuredWidth());
      mh = Math.max(mh, child.getMeasuredHeight());
    }

    // This is how big we want to be without padding
    w = (mw * column) + paddingWidth + childMarginWidth;
    h = (mh * row) + paddingHeight + childMarginHeight;

    // If child decide to give no width or height, use default
    if (mw == 0) {
      w = dpToPx(getContext(), DEFAULT_WIDTH) + childMarginWidth;
    }
    if (mh == 0) {
      h = dpToPx(getContext(), DEFAULT_HEIGHT) + childMarginHeight;
    }
    setMeasuredDimension(
      resolveSize(w, widthMeasureSpec),
      resolveSize(h, heightMeasureSpec)
    );
  }

  @Override
  protected LayoutParams generateDefaultLayoutParams() {
    return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
  }

  @Override
  protected LayoutParams generateLayoutParams(LayoutParams p) {
    return new MarginLayoutParams(p);
  }

  @Override
  public LayoutParams generateLayoutParams(AttributeSet attrs) {
    return new MarginLayoutParams(getContext(), attrs);
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    final int w = r - l;
    final int h = b - t;
    final int n = getChildCount();
    final int row = ((n % column) == 0) ? (n / column) : ((n / column) + 1);
    // Child width
    int cw = (int) ((float) w / column);
    // Child height
    int ch = (int) ((float) h / row);
    int x;
    int y;
    for (int i = 0; i < n; ++i) {
      final View c = getChildAt(i);
      if (c.getVisibility() == View.GONE) {
        continue;
      }
      x = i / column;
      y = i % column;
      final MarginLayoutParams lp = (MarginLayoutParams) c.getLayoutParams();
      c.layout(
        // left
        x + y * cw + lp.leftMargin,
        // top
        x * ch + lp.topMargin,
        // right
        x + (y + 1) * cw - lp.rightMargin,
        // bottom
        (x + 1) * ch - lp.bottomMargin
      );
    }
  }

  /**
   * Helper method to convert pixel to dp
   *
   * @param context
   * @param px
   * @return
   */
  static int pxToDp(final Context context, final float px) {
    return (int) (px / context.getResources().getDisplayMetrics().density);
  }

  /**
   * Helper method to convert dp to pixel
   *
   * @param context
   * @param dp
   * @return
   */
  static int dpToPx(final Context context, final float dp) {
    return (int) (dp * context.getResources().getDisplayMetrics().density);
  }
}

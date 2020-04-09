package com.backpacker.yflLibrary.view.calendar;

import android.view.ContextThemeWrapper;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.UtilsLibrary.R;

import static android.view.Gravity.CENTER_VERTICAL;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class DefaultDayViewAdapter implements DayViewAdapter {

    @Override
    public void makeCellView(CalendarCellView parent) {
        TextView textView = new TextView(new ContextThemeWrapper(parent.getContext(), R.style.CalendarCell_CalendarDate));
        textView.setDuplicateParentStateEnabled(true);

        parent.addView(textView, new FrameLayout.LayoutParams(MATCH_PARENT, 100, CENTER_VERTICAL));
        parent.setDayOfMonthTextView(textView);
    }
}

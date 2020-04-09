package com.backpacker.yflLibrary.view.calendar;

import java.util.Date;

public interface CalendarCellDecorator {
  void decorate(CalendarCellView cellView, Date date);
}

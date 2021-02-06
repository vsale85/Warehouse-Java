package model;

import java.awt.Color;
import java.awt.Font;
import java.time.DayOfWeek;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DatePickerSettings.DateArea;

public class DatePickerNew {
	
	public static DatePickerSettings newDateSet () {
	
	DatePickerSettings dateSettings = new DatePickerSettings();
	dateSettings.setVisibleDateTextField(true);
    dateSettings.setGapBeforeButtonPixels(0);
    // Set backgrounds:
	
    dateSettings.setColor(DateArea.CalendarBackgroundNormalDates, Color.DARK_GRAY);
    dateSettings.setColor(DateArea.BackgroundOverallCalendarPanel, Color.DARK_GRAY);
    dateSettings.setColor(DateArea.BackgroundMonthAndYearMenuLabels, Color.DARK_GRAY);
    dateSettings.setColor(DateArea.BackgroundTodayLabel, Color.DARK_GRAY);
    dateSettings.setColor(DateArea.BackgroundClearLabel, Color.DARK_GRAY);
    dateSettings.setColor(DateArea.BackgroundMonthAndYearNavigationButtons, Color.DARK_GRAY);
//    dateSettings.setColor(DateArea.BackgroundTopLeftLabelAboveWeekNumbers, Color.gray);
    dateSettings.setColor(DateArea.CalendarBackgroundSelectedDate, Color.lightGray);
    dateSettings.setColor(DateArea.CalendarBorderSelectedDate, Color.WHITE);
    dateSettings.setColorBackgroundWeekdayLabels(Color.gray, true);
    dateSettings.setColorBackgroundWeekNumberLabels(Color.gray, true);
 // Set fonts:
    Font randomFont = new Font("SansSerif", Font.ITALIC | Font.BOLD, 20);
    Font smallerFont = new Font("SansSerif", Font.BOLD, 18);
    dateSettings.setFontMonthAndYearMenuLabels(randomFont);
    dateSettings.setFontMonthAndYearNavigationButtons(randomFont);
    dateSettings.setFontTodayLabel(randomFont);
    dateSettings.setFontClearLabel(randomFont);
    dateSettings.setFontCalendarDateLabels(randomFont);
    dateSettings.setFontCalendarWeekdayLabels(smallerFont);
    dateSettings.setFontCalendarWeekNumberLabels(smallerFont);
    // Set text colors:
    dateSettings.setColor(DateArea.TextMonthAndYearMenuLabels, new Color(0,153,255));
    dateSettings.setColor(DateArea.TextMonthAndYearNavigationButtons,  new Color(0,153,255));
    dateSettings.setColor(DateArea.TextTodayLabel, new Color(0,153,255));
    dateSettings.setColor(DateArea.TextClearLabel,  new Color(0,153,255));
    dateSettings.setColor(DateArea.CalendarTextNormalDates,  new Color(0,153,255));
    dateSettings.setColor(DateArea.CalendarTextWeekdays, new Color(0,153,255));
    dateSettings.setFirstDayOfWeek(DayOfWeek.MONDAY);
    dateSettings.setColor(DateArea.TextFieldBackgroundValidDate, Color.DARK_GRAY);
    dateSettings.setColor(DateArea.TextFieldBackgroundInvalidDate, Color.LIGHT_GRAY);
  
    dateSettings.setFormatForDatesCommonEra("dd/MM/yyyy");
    dateSettings.setFormatForDatesBeforeCommonEra("dd/MM/uuuu");
    dateSettings.setVisibleClearButton(false);
    dateSettings.setAllowKeyboardEditing(true);
    dateSettings.setFontValidDate(new Font("SansSerif", Font.BOLD, 20));
    dateSettings.setFontInvalidDate(new Font("SansSerif", Font.BOLD, 18));
    dateSettings.setColor(DateArea.DatePickerTextValidDate, new Color(0,153,255));
    dateSettings.setColor(DateArea.DatePickerTextInvalidDate, new Color (255,0,0));
    dateSettings.setAllowEmptyDates(true);
   
    return dateSettings;
	}

}

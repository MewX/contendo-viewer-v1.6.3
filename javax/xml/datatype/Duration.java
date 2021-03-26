package javax.xml.datatype;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.namespace.QName;

public abstract class Duration {
  public QName getXMLSchemaType() {
    boolean bool1 = isSet(DatatypeConstants.YEARS);
    boolean bool2 = isSet(DatatypeConstants.MONTHS);
    boolean bool3 = isSet(DatatypeConstants.DAYS);
    boolean bool4 = isSet(DatatypeConstants.HOURS);
    boolean bool5 = isSet(DatatypeConstants.MINUTES);
    boolean bool6 = isSet(DatatypeConstants.SECONDS);
    if (bool1 && bool2 && bool3 && bool4 && bool5 && bool6)
      return DatatypeConstants.DURATION; 
    if (!bool1 && !bool2 && bool3 && bool4 && bool5 && bool6)
      return DatatypeConstants.DURATION_DAYTIME; 
    if (bool1 && bool2 && !bool3 && !bool4 && !bool5 && !bool6)
      return DatatypeConstants.DURATION_YEARMONTH; 
    throw new IllegalStateException("javax.xml.datatype.Duration#getXMLSchemaType(): this Duration does not match one of the XML Schema date/time datatypes: year set = " + bool1 + " month set = " + bool2 + " day set = " + bool3 + " hour set = " + bool4 + " minute set = " + bool5 + " second set = " + bool6);
  }
  
  public abstract int getSign();
  
  public int getYears() {
    return getFieldValueAsInt(DatatypeConstants.YEARS);
  }
  
  public int getMonths() {
    return getFieldValueAsInt(DatatypeConstants.MONTHS);
  }
  
  public int getDays() {
    return getFieldValueAsInt(DatatypeConstants.DAYS);
  }
  
  public int getHours() {
    return getFieldValueAsInt(DatatypeConstants.HOURS);
  }
  
  public int getMinutes() {
    return getFieldValueAsInt(DatatypeConstants.MINUTES);
  }
  
  public int getSeconds() {
    return getFieldValueAsInt(DatatypeConstants.SECONDS);
  }
  
  public long getTimeInMillis(Calendar paramCalendar) {
    Calendar calendar = (Calendar)paramCalendar.clone();
    addTo(calendar);
    return getCalendarTimeInMillis(calendar) - getCalendarTimeInMillis(paramCalendar);
  }
  
  public long getTimeInMillis(Date paramDate) {
    GregorianCalendar gregorianCalendar = new GregorianCalendar();
    gregorianCalendar.setTime(paramDate);
    addTo(gregorianCalendar);
    return getCalendarTimeInMillis(gregorianCalendar) - paramDate.getTime();
  }
  
  public abstract Number getField(DatatypeConstants.Field paramField);
  
  private int getFieldValueAsInt(DatatypeConstants.Field paramField) {
    Number number = getField(paramField);
    return (number != null) ? number.intValue() : 0;
  }
  
  public abstract boolean isSet(DatatypeConstants.Field paramField);
  
  public abstract Duration add(Duration paramDuration);
  
  public abstract void addTo(Calendar paramCalendar);
  
  public void addTo(Date paramDate) {
    if (paramDate == null)
      throw new NullPointerException("Cannot call " + getClass().getName() + "#addTo(Date date) with date == null."); 
    GregorianCalendar gregorianCalendar = new GregorianCalendar();
    gregorianCalendar.setTime(paramDate);
    addTo(gregorianCalendar);
    paramDate.setTime(getCalendarTimeInMillis(gregorianCalendar));
  }
  
  public Duration subtract(Duration paramDuration) {
    return add(paramDuration.negate());
  }
  
  public Duration multiply(int paramInt) {
    return multiply(BigDecimal.valueOf(paramInt));
  }
  
  public abstract Duration multiply(BigDecimal paramBigDecimal);
  
  public abstract Duration negate();
  
  public abstract Duration normalizeWith(Calendar paramCalendar);
  
  public abstract int compare(Duration paramDuration);
  
  public boolean isLongerThan(Duration paramDuration) {
    return (compare(paramDuration) == 1);
  }
  
  public boolean isShorterThan(Duration paramDuration) {
    return (compare(paramDuration) == -1);
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject == this) ? true : ((paramObject instanceof Duration) ? ((compare((Duration)paramObject) == 0)) : false);
  }
  
  public abstract int hashCode();
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    if (getSign() < 0)
      stringBuffer.append('-'); 
    stringBuffer.append('P');
    BigInteger bigInteger1 = (BigInteger)getField(DatatypeConstants.YEARS);
    if (bigInteger1 != null)
      stringBuffer.append(bigInteger1).append('Y'); 
    BigInteger bigInteger2 = (BigInteger)getField(DatatypeConstants.MONTHS);
    if (bigInteger2 != null)
      stringBuffer.append(bigInteger2).append('M'); 
    BigInteger bigInteger3 = (BigInteger)getField(DatatypeConstants.DAYS);
    if (bigInteger3 != null)
      stringBuffer.append(bigInteger3).append('D'); 
    BigInteger bigInteger4 = (BigInteger)getField(DatatypeConstants.HOURS);
    BigInteger bigInteger5 = (BigInteger)getField(DatatypeConstants.MINUTES);
    BigDecimal bigDecimal = (BigDecimal)getField(DatatypeConstants.SECONDS);
    if (bigInteger4 != null || bigInteger5 != null || bigDecimal != null) {
      stringBuffer.append('T');
      if (bigInteger4 != null)
        stringBuffer.append(bigInteger4).append('H'); 
      if (bigInteger5 != null)
        stringBuffer.append(bigInteger5).append('M'); 
      if (bigDecimal != null)
        stringBuffer.append(toString(bigDecimal)).append('S'); 
    } 
    return stringBuffer.toString();
  }
  
  private String toString(BigDecimal paramBigDecimal) {
    StringBuffer stringBuffer;
    String str = paramBigDecimal.unscaledValue().toString();
    int i = paramBigDecimal.scale();
    if (i == 0)
      return str; 
    int j = str.length() - i;
    if (j == 0)
      return "0." + str; 
    if (j > 0) {
      stringBuffer = new StringBuffer(str);
      stringBuffer.insert(j, '.');
    } else {
      stringBuffer = new StringBuffer(3 - j + str.length());
      stringBuffer.append("0.");
      for (byte b = 0; b < -j; b++)
        stringBuffer.append('0'); 
      stringBuffer.append(str);
    } 
    return stringBuffer.toString();
  }
  
  private static long getCalendarTimeInMillis(Calendar paramCalendar) {
    return paramCalendar.getTime().getTime();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/datatype/Duration.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
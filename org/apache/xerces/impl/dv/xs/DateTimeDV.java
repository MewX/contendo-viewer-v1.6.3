package org.apache.xerces.impl.dv.xs;

import java.math.BigInteger;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;

public class DateTimeDV extends AbstractDateTimeDV {
  public Object getActualValue(String paramString, ValidationContext paramValidationContext) throws InvalidDatatypeValueException {
    try {
      return parse(paramString);
    } catch (Exception exception) {
      throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { paramString, "dateTime" });
    } 
  }
  
  protected AbstractDateTimeDV.DateTimeData parse(String paramString) throws SchemaDateTimeException {
    AbstractDateTimeDV.DateTimeData dateTimeData = new AbstractDateTimeDV.DateTimeData(paramString, this);
    int i = paramString.length();
    int j = indexOf(paramString, 0, i, 'T');
    int k = getDate(paramString, 0, j, dateTimeData);
    getTime(paramString, j + 1, i, dateTimeData);
    if (k != j)
      throw new RuntimeException(paramString + " is an invalid dateTime dataype value. " + "Invalid character(s) seprating date and time values."); 
    validateDateTime(dateTimeData);
    saveUnnormalized(dateTimeData);
    if (dateTimeData.utc != 0 && dateTimeData.utc != 90)
      normalize(dateTimeData); 
    return dateTimeData;
  }
  
  protected XMLGregorianCalendar getXMLGregorianCalendar(AbstractDateTimeDV.DateTimeData paramDateTimeData) {
    return AbstractDateTimeDV.datatypeFactory.newXMLGregorianCalendar(BigInteger.valueOf(paramDateTimeData.unNormYear), paramDateTimeData.unNormMonth, paramDateTimeData.unNormDay, paramDateTimeData.unNormHour, paramDateTimeData.unNormMinute, (int)paramDateTimeData.unNormSecond, (paramDateTimeData.unNormSecond != 0.0D) ? getFractionalSecondsAsBigDecimal(paramDateTimeData) : null, paramDateTimeData.hasTimeZone() ? (paramDateTimeData.timezoneHr * 60 + paramDateTimeData.timezoneMin) : Integer.MIN_VALUE);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/dv/xs/DateTimeDV.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
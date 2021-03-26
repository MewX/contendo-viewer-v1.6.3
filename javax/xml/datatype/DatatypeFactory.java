package javax.xml.datatype;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.GregorianCalendar;

public abstract class DatatypeFactory {
  public static final String DATATYPEFACTORY_PROPERTY = "javax.xml.datatype.DatatypeFactory";
  
  public static final String DATATYPEFACTORY_IMPLEMENTATION_CLASS = new String("org.apache.xerces.jaxp.datatype.DatatypeFactoryImpl");
  
  public static DatatypeFactory newInstance() throws DatatypeConfigurationException {
    try {
      return (DatatypeFactory)FactoryFinder.a("javax.xml.datatype.DatatypeFactory", DATATYPEFACTORY_IMPLEMENTATION_CLASS);
    } catch (ConfigurationError configurationError) {
      throw new DatatypeConfigurationException(configurationError.getMessage(), configurationError.a());
    } 
  }
  
  public static DatatypeFactory newInstance(String paramString, ClassLoader paramClassLoader) throws DatatypeConfigurationException {
    if (paramString == null)
      throw new DatatypeConfigurationException("factoryClassName cannot be null."); 
    if (paramClassLoader == null)
      paramClassLoader = SecuritySupport.a(); 
    try {
      return (DatatypeFactory)FactoryFinder.a(paramString, paramClassLoader);
    } catch (ConfigurationError configurationError) {
      throw new DatatypeConfigurationException(configurationError.getMessage(), configurationError.a());
    } 
  }
  
  public abstract Duration newDuration(String paramString);
  
  public abstract Duration newDuration(long paramLong);
  
  public abstract Duration newDuration(boolean paramBoolean, BigInteger paramBigInteger1, BigInteger paramBigInteger2, BigInteger paramBigInteger3, BigInteger paramBigInteger4, BigInteger paramBigInteger5, BigDecimal paramBigDecimal);
  
  public Duration newDuration(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    BigInteger bigInteger1 = (paramInt1 != Integer.MIN_VALUE) ? BigInteger.valueOf(paramInt1) : null;
    BigInteger bigInteger2 = (paramInt2 != Integer.MIN_VALUE) ? BigInteger.valueOf(paramInt2) : null;
    BigInteger bigInteger3 = (paramInt3 != Integer.MIN_VALUE) ? BigInteger.valueOf(paramInt3) : null;
    BigInteger bigInteger4 = (paramInt4 != Integer.MIN_VALUE) ? BigInteger.valueOf(paramInt4) : null;
    BigInteger bigInteger5 = (paramInt5 != Integer.MIN_VALUE) ? BigInteger.valueOf(paramInt5) : null;
    BigDecimal bigDecimal = (paramInt6 != Integer.MIN_VALUE) ? BigDecimal.valueOf(paramInt6) : null;
    return newDuration(paramBoolean, bigInteger1, bigInteger2, bigInteger3, bigInteger4, bigInteger5, bigDecimal);
  }
  
  public Duration newDurationDayTime(String paramString) {
    if (paramString == null)
      throw new NullPointerException("The lexical representation cannot be null."); 
    int i = paramString.indexOf('T');
    int j = (i >= 0) ? i : paramString.length();
    for (byte b = 0; b < j; b++) {
      char c = paramString.charAt(b);
      if (c == 'Y' || c == 'M')
        throw new IllegalArgumentException("Invalid dayTimeDuration value: " + paramString); 
    } 
    return newDuration(paramString);
  }
  
  public Duration newDurationDayTime(long paramLong) {
    boolean bool2;
    long l1 = paramLong;
    if (l1 == 0L)
      return newDuration(true, -2147483648, -2147483648, 0, 0, 0, 0); 
    boolean bool1 = false;
    if (l1 < 0L) {
      bool2 = false;
      if (l1 == Long.MIN_VALUE) {
        l1++;
        bool1 = true;
      } 
      l1 *= -1L;
    } else {
      bool2 = true;
    } 
    long l2 = l1;
    int i = (int)(l2 % 60000L);
    if (bool1)
      i++; 
    if (i % 1000 == 0) {
      int j = i / 1000;
      l2 /= 60000L;
      int k = (int)(l2 % 60L);
      l2 /= 60L;
      int m = (int)(l2 % 24L);
      long l = l2 / 24L;
      return (l <= 2147483647L) ? newDuration(bool2, -2147483648, -2147483648, (int)l, m, k, j) : newDuration(bool2, (BigInteger)null, (BigInteger)null, BigInteger.valueOf(l), BigInteger.valueOf(m), BigInteger.valueOf(k), BigDecimal.valueOf(i, 3));
    } 
    BigDecimal bigDecimal = BigDecimal.valueOf(i, 3);
    l2 /= 60000L;
    BigInteger bigInteger1 = BigInteger.valueOf(l2 % 60L);
    l2 /= 60L;
    BigInteger bigInteger2 = BigInteger.valueOf(l2 % 24L);
    l2 /= 24L;
    BigInteger bigInteger3 = BigInteger.valueOf(l2);
    return newDuration(bool2, (BigInteger)null, (BigInteger)null, bigInteger3, bigInteger2, bigInteger1, bigDecimal);
  }
  
  public Duration newDurationDayTime(boolean paramBoolean, BigInteger paramBigInteger1, BigInteger paramBigInteger2, BigInteger paramBigInteger3, BigInteger paramBigInteger4) {
    return newDuration(paramBoolean, (BigInteger)null, (BigInteger)null, paramBigInteger1, paramBigInteger2, paramBigInteger3, (paramBigInteger4 != null) ? new BigDecimal(paramBigInteger4) : null);
  }
  
  public Duration newDurationDayTime(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return newDuration(paramBoolean, -2147483648, -2147483648, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public Duration newDurationYearMonth(String paramString) {
    if (paramString == null)
      throw new NullPointerException("The lexical representation cannot be null."); 
    int i = paramString.length();
    for (byte b = 0; b < i; b++) {
      char c = paramString.charAt(b);
      if (c == 'D' || c == 'T')
        throw new IllegalArgumentException("Invalid yearMonthDuration value: " + paramString); 
    } 
    return newDuration(paramString);
  }
  
  public Duration newDurationYearMonth(long paramLong) {
    return newDuration(paramLong);
  }
  
  public Duration newDurationYearMonth(boolean paramBoolean, BigInteger paramBigInteger1, BigInteger paramBigInteger2) {
    return newDuration(paramBoolean, paramBigInteger1, paramBigInteger2, (BigInteger)null, (BigInteger)null, (BigInteger)null, (BigDecimal)null);
  }
  
  public Duration newDurationYearMonth(boolean paramBoolean, int paramInt1, int paramInt2) {
    return newDuration(paramBoolean, paramInt1, paramInt2, -2147483648, -2147483648, -2147483648, -2147483648);
  }
  
  public abstract XMLGregorianCalendar newXMLGregorianCalendar();
  
  public abstract XMLGregorianCalendar newXMLGregorianCalendar(String paramString);
  
  public abstract XMLGregorianCalendar newXMLGregorianCalendar(GregorianCalendar paramGregorianCalendar);
  
  public abstract XMLGregorianCalendar newXMLGregorianCalendar(BigInteger paramBigInteger, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, BigDecimal paramBigDecimal, int paramInt6);
  
  public XMLGregorianCalendar newXMLGregorianCalendar(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8) {
    BigInteger bigInteger = (paramInt1 != Integer.MIN_VALUE) ? BigInteger.valueOf(paramInt1) : null;
    BigDecimal bigDecimal = null;
    if (paramInt7 != Integer.MIN_VALUE) {
      if (paramInt7 < 0 || paramInt7 > 1000)
        throw new IllegalArgumentException("javax.xml.datatype.DatatypeFactory#newXMLGregorianCalendar(int year, int month, int day, int hour, int minute, int second, int millisecond, int timezone)with invalid millisecond: " + paramInt7); 
      bigDecimal = BigDecimal.valueOf(paramInt7, 3);
    } 
    return newXMLGregorianCalendar(bigInteger, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, bigDecimal, paramInt8);
  }
  
  public XMLGregorianCalendar newXMLGregorianCalendarDate(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return newXMLGregorianCalendar(paramInt1, paramInt2, paramInt3, -2147483648, -2147483648, -2147483648, -2147483648, paramInt4);
  }
  
  public XMLGregorianCalendar newXMLGregorianCalendarTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    return newXMLGregorianCalendar(-2147483648, -2147483648, -2147483648, paramInt1, paramInt2, paramInt3, -2147483648, paramInt4);
  }
  
  public XMLGregorianCalendar newXMLGregorianCalendarTime(int paramInt1, int paramInt2, int paramInt3, BigDecimal paramBigDecimal, int paramInt4) {
    return newXMLGregorianCalendar((BigInteger)null, -2147483648, -2147483648, paramInt1, paramInt2, paramInt3, paramBigDecimal, paramInt4);
  }
  
  public XMLGregorianCalendar newXMLGregorianCalendarTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    BigDecimal bigDecimal = null;
    if (paramInt4 != Integer.MIN_VALUE) {
      if (paramInt4 < 0 || paramInt4 > 1000)
        throw new IllegalArgumentException("javax.xml.datatype.DatatypeFactory#newXMLGregorianCalendarTime(int hours, int minutes, int seconds, int milliseconds, int timezone)with invalid milliseconds: " + paramInt4); 
      bigDecimal = BigDecimal.valueOf(paramInt4, 3);
    } 
    return newXMLGregorianCalendarTime(paramInt1, paramInt2, paramInt3, bigDecimal, paramInt5);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/datatype/DatatypeFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
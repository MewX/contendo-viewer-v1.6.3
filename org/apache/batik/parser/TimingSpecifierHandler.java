package org.apache.batik.parser;

import java.util.Calendar;

public interface TimingSpecifierHandler {
  void offset(float paramFloat);
  
  void syncbase(float paramFloat, String paramString1, String paramString2);
  
  void eventbase(float paramFloat, String paramString1, String paramString2);
  
  void repeat(float paramFloat, String paramString);
  
  void repeat(float paramFloat, String paramString, int paramInt);
  
  void accesskey(float paramFloat, char paramChar);
  
  void accessKeySVG12(float paramFloat, String paramString);
  
  void mediaMarker(String paramString1, String paramString2);
  
  void wallclock(Calendar paramCalendar);
  
  void indefinite();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/TimingSpecifierHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package org.apache.xmlgraphics.util.i18n;

import java.util.Locale;
import java.util.MissingResourceException;

public interface Localizable {
  void setLocale(Locale paramLocale);
  
  Locale getLocale();
  
  String formatMessage(String paramString, Object[] paramArrayOfObject) throws MissingResourceException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/i18n/Localizable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
package org.apache.xmlgraphics.util.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public interface ExtendedLocalizable extends Localizable {
  void setLocaleGroup(LocaleGroup paramLocaleGroup);
  
  LocaleGroup getLocaleGroup();
  
  void setDefaultLocale(Locale paramLocale);
  
  Locale getDefaultLocale();
  
  ResourceBundle getResourceBundle();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/i18n/ExtendedLocalizable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
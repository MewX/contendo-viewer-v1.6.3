package org.apache.batik.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public interface ExtendedLocalizable extends Localizable {
  void setLocaleGroup(LocaleGroup paramLocaleGroup);
  
  LocaleGroup getLocaleGroup();
  
  void setDefaultLocale(Locale paramLocale);
  
  Locale getDefaultLocale();
  
  ResourceBundle getResourceBundle();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/i18n/ExtendedLocalizable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
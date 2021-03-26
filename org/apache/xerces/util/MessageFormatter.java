package org.apache.xerces.util;

import java.util.Locale;
import java.util.MissingResourceException;

public interface MessageFormatter {
  String formatMessage(Locale paramLocale, String paramString, Object[] paramArrayOfObject) throws MissingResourceException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/util/MessageFormatter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
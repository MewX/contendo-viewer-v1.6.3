package org.apache.xerces.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class SAXMessageFormatter {
  public static String formatMessage(Locale paramLocale, String paramString, Object[] paramArrayOfObject) throws MissingResourceException {
    String str;
    if (paramLocale == null)
      paramLocale = Locale.getDefault(); 
    ResourceBundle resourceBundle = ResourceBundle.getBundle("org.apache.xerces.impl.msg.SAXMessages", paramLocale);
    try {
      str = resourceBundle.getString(paramString);
      if (paramArrayOfObject != null)
        try {
          str = MessageFormat.format(str, paramArrayOfObject);
        } catch (Exception exception) {
          str = resourceBundle.getString("FormatFailed");
          str = str + " " + resourceBundle.getString(paramString);
        }  
    } catch (MissingResourceException missingResourceException) {
      str = resourceBundle.getString("BadMessageKey");
      throw new MissingResourceException(paramString, str, paramString);
    } 
    if (str == null) {
      str = paramString;
      if (paramArrayOfObject.length > 0) {
        StringBuffer stringBuffer = new StringBuffer(str);
        stringBuffer.append('?');
        for (byte b = 0; b < paramArrayOfObject.length; b++) {
          if (b > 0)
            stringBuffer.append('&'); 
          stringBuffer.append(String.valueOf(paramArrayOfObject[b]));
        } 
      } 
    } 
    return str;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/util/SAXMessageFormatter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
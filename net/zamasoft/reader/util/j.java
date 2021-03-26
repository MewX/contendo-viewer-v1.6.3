package net.zamasoft.reader.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class j extends ResourceBundle.Control {
  private static final List<String> a = Arrays.asList(new String[] { "xml" });
  
  public List<String> getFormats(String paramString) {
    return a;
  }
  
  public ResourceBundle newBundle(String paramString1, Locale paramLocale, String paramString2, ClassLoader paramClassLoader, boolean paramBoolean) throws IllegalAccessException, InstantiationException, IOException {
    if (paramString1 == null || paramLocale == null || paramString2 == null || paramClassLoader == null)
      throw new NullPointerException(); 
    i i = null;
    if (a.contains(paramString2)) {
      String str1 = toBundleName(paramString1, paramLocale);
      String str2 = toResourceName(str1, paramString2);
      InputStream inputStream = null;
      URL uRL = paramClassLoader.getResource(str2);
      if (uRL != null) {
        URLConnection uRLConnection = uRL.openConnection();
        if (uRLConnection != null) {
          if (paramBoolean)
            uRLConnection.setUseCaches(false); 
          inputStream = uRLConnection.getInputStream();
        } 
      } 
      if (inputStream != null) {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        try {
          i = new i(bufferedInputStream);
        } finally {
          bufferedInputStream.close();
        } 
      } 
    } 
    return i;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/util/j.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */
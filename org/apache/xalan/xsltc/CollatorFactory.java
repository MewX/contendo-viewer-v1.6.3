package org.apache.xalan.xsltc;

import java.text.Collator;
import java.util.Locale;

public interface CollatorFactory {
  Collator getCollator(String paramString1, String paramString2);
  
  Collator getCollator(Locale paramLocale);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/CollatorFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
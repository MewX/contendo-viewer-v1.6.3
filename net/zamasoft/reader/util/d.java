package net.zamasoft.reader.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang3.SystemUtils;

public class d<K, V> extends LinkedHashMap<K, V> {
  private static final long a = -8724355980674678458L;
  
  private final double b;
  
  public d(double paramDouble) {
    this.b = paramDouble;
  }
  
  private void a() {
    while (!isEmpty() && c.a(this.b)) {
      Iterator<Map.Entry<K, V>> iterator = entrySet().iterator();
      while (iterator.hasNext() && c.a(this.b)) {
        iterator.next();
        iterator.remove();
        if (SystemUtils.IS_OS_WINDOWS)
          System.gc(); 
      } 
    } 
  }
  
  public V put(K paramK, V paramV) {
    a();
    return super.put(paramK, paramV);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/util/d.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */
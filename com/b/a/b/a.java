package com.b.a.b;

import com.b.a.g;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import net.zamasoft.reader.b;

public class a implements Serializable {
  private static final long d = -1370564015154108825L;
  
  final b a;
  
  final String b;
  
  final String c;
  
  public static HashMap<String, Object> a(Collection<b> paramCollection) {
    HashMap<Object, Object> hashMap = new HashMap<>();
    for (b b1 : paramCollection) {
      a a1 = new a(b1);
      Object object = hashMap.get(a1.b);
      if (object == null) {
        hashMap.put(a1.b, a1);
        continue;
      } 
      if (object instanceof List) {
        ((List<a>)object).add(a1);
        continue;
      } 
      ArrayList<Object> arrayList = new ArrayList();
      arrayList.add(object);
      arrayList.add(a1);
      hashMap.put(a1.b, arrayList);
    } 
    return (HashMap)hashMap;
  }
  
  public static String a(HashMap<String, Object> paramHashMap, String paramString) {
    if (paramHashMap != null) {
      Object object = paramHashMap.get(paramString);
      String str = null;
      if (object != null) {
        Date date = new Date();
        if (object instanceof List) {
          for (a a1 : object) {
            if (a1.a(date)) {
              str = a1.c;
              break;
            } 
          } 
          if (str == null)
            throw new SecurityException(b.a.getString("keyExpired")); 
        } else if (object instanceof a) {
          a a1 = (a)object;
          if (a1.a(date)) {
            str = a1.c;
          } else {
            throw new SecurityException(b.a.getString("keyExpired"));
          } 
        } 
        if (str == null)
          throw new SecurityException(b.a.getString("noPermission")); 
        return str;
      } 
    } 
    throw new SecurityException(b.a.getString("noPermission"));
  }
  
  private boolean a(Date paramDate) {
    return !(this.a.e() != null && paramDate.compareTo(this.a.e()) > 0);
  }
  
  private a(b paramb) {
    this.a = paramb;
    this.c = g.a(this.a.a, this.a.b).intern();
    this.b = g.a(this.c).intern();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/b/a/b/a.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */
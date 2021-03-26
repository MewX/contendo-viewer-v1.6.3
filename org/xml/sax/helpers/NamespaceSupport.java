package org.xml.sax.helpers;

import java.util.EmptyStackException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class NamespaceSupport {
  public static final String XMLNS = "http://www.w3.org/XML/1998/namespace";
  
  public static final String NSDECL = "http://www.w3.org/xmlns/2000/";
  
  private static final Enumeration EMPTY_ENUMERATION = (new Vector()).elements();
  
  private Context[] contexts;
  
  private Context currentContext;
  
  private int contextPos;
  
  private boolean namespaceDeclUris;
  
  public NamespaceSupport() {
    reset();
  }
  
  public void reset() {
    this.contexts = new Context[32];
    this.namespaceDeclUris = false;
    this.contextPos = 0;
    this.contexts[this.contextPos] = this.currentContext = new Context(this);
    this.currentContext.a("xml", "http://www.w3.org/XML/1998/namespace");
  }
  
  public void pushContext() {
    int i = this.contexts.length;
    this.contextPos++;
    if (this.contextPos >= i) {
      Context[] arrayOfContext = new Context[i * 2];
      System.arraycopy(this.contexts, 0, arrayOfContext, 0, i);
      i *= 2;
      this.contexts = arrayOfContext;
    } 
    this.currentContext = this.contexts[this.contextPos];
    if (this.currentContext == null)
      this.contexts[this.contextPos] = this.currentContext = new Context(this); 
    if (this.contextPos > 0)
      this.currentContext.a(this.contexts[this.contextPos - 1]); 
  }
  
  public void popContext() {
    this.contexts[this.contextPos].a();
    this.contextPos--;
    if (this.contextPos < 0)
      throw new EmptyStackException(); 
    this.currentContext = this.contexts[this.contextPos];
  }
  
  public boolean declarePrefix(String paramString1, String paramString2) {
    if (paramString1.equals("xml") || paramString1.equals("xmlns"))
      return false; 
    this.currentContext.a(paramString1, paramString2);
    return true;
  }
  
  public String[] processName(String paramString, String[] paramArrayOfString, boolean paramBoolean) {
    String[] arrayOfString = this.currentContext.a(paramString, paramBoolean);
    if (arrayOfString == null)
      return null; 
    paramArrayOfString[0] = arrayOfString[0];
    paramArrayOfString[1] = arrayOfString[1];
    paramArrayOfString[2] = arrayOfString[2];
    return paramArrayOfString;
  }
  
  public String getURI(String paramString) {
    return this.currentContext.a(paramString);
  }
  
  public Enumeration getPrefixes() {
    return this.currentContext.c();
  }
  
  public String getPrefix(String paramString) {
    return this.currentContext.b(paramString);
  }
  
  public Enumeration getPrefixes(String paramString) {
    Vector vector = new Vector();
    Enumeration enumeration = getPrefixes();
    while (enumeration.hasMoreElements()) {
      String str = enumeration.nextElement();
      if (paramString.equals(getURI(str)))
        vector.addElement(str); 
    } 
    return vector.elements();
  }
  
  public Enumeration getDeclaredPrefixes() {
    return this.currentContext.b();
  }
  
  public void setNamespaceDeclUris(boolean paramBoolean) {
    if (this.contextPos != 0)
      throw new IllegalStateException(); 
    if (paramBoolean == this.namespaceDeclUris)
      return; 
    this.namespaceDeclUris = paramBoolean;
    if (paramBoolean) {
      this.currentContext.a("xmlns", "http://www.w3.org/xmlns/2000/");
    } else {
      this.contexts[this.contextPos] = this.currentContext = new Context(this);
      this.currentContext.a("xml", "http://www.w3.org/XML/1998/namespace");
    } 
  }
  
  public boolean isNamespaceDeclUris() {
    return this.namespaceDeclUris;
  }
  
  final class Context {
    Hashtable a;
    
    Hashtable b;
    
    Hashtable c;
    
    Hashtable d;
    
    String e;
    
    private Vector f;
    
    private boolean g;
    
    private Context h;
    
    private final NamespaceSupport i;
    
    Context(NamespaceSupport this$0) {
      this.i = this$0;
      this.e = null;
      this.f = null;
      this.g = false;
      this.h = null;
      d();
    }
    
    void a(Context param1Context) {
      this.h = param1Context;
      this.f = null;
      this.a = param1Context.a;
      this.b = param1Context.b;
      this.c = param1Context.c;
      this.d = param1Context.d;
      this.e = param1Context.e;
      this.g = false;
    }
    
    void a() {
      this.h = null;
      this.a = null;
      this.b = null;
      this.c = null;
      this.d = null;
      this.e = null;
    }
    
    void a(String param1String1, String param1String2) {
      if (!this.g)
        d(); 
      if (this.f == null)
        this.f = new Vector(); 
      param1String1 = param1String1.intern();
      param1String2 = param1String2.intern();
      if ("".equals(param1String1)) {
        if ("".equals(param1String2)) {
          this.e = null;
        } else {
          this.e = param1String2;
        } 
      } else {
        this.a.put(param1String1, param1String2);
        this.b.put(param1String2, param1String1);
      } 
      this.f.addElement(param1String1);
    }
    
    String[] a(String param1String, boolean param1Boolean) {
      Hashtable hashtable;
      if (param1Boolean) {
        hashtable = this.d;
      } else {
        hashtable = this.c;
      } 
      String[] arrayOfString = (String[])hashtable.get(param1String);
      if (arrayOfString != null)
        return arrayOfString; 
      arrayOfString = new String[3];
      arrayOfString[2] = param1String.intern();
      int i = param1String.indexOf(':');
      if (i == -1) {
        if (param1Boolean) {
          if (param1String == "xmlns" && this.i.namespaceDeclUris) {
            arrayOfString[0] = "http://www.w3.org/xmlns/2000/";
          } else {
            arrayOfString[0] = "";
          } 
        } else if (this.e == null) {
          arrayOfString[0] = "";
        } else {
          arrayOfString[0] = this.e;
        } 
        arrayOfString[1] = arrayOfString[2];
      } else {
        String str3;
        String str1 = param1String.substring(0, i);
        String str2 = param1String.substring(i + 1);
        if ("".equals(str1)) {
          str3 = this.e;
        } else {
          str3 = (String)this.a.get(str1);
        } 
        if (str3 == null || (!param1Boolean && "xmlns".equals(str1)))
          return null; 
        arrayOfString[0] = str3;
        arrayOfString[1] = str2.intern();
      } 
      hashtable.put(arrayOfString[2], arrayOfString);
      return arrayOfString;
    }
    
    String a(String param1String) {
      return "".equals(param1String) ? this.e : ((this.a == null) ? null : (String)this.a.get(param1String));
    }
    
    String b(String param1String) {
      return (this.b == null) ? null : (String)this.b.get(param1String);
    }
    
    Enumeration b() {
      return (this.f == null) ? NamespaceSupport.EMPTY_ENUMERATION : this.f.elements();
    }
    
    Enumeration c() {
      return (this.a == null) ? NamespaceSupport.EMPTY_ENUMERATION : this.a.keys();
    }
    
    private void d() {
      if (this.a != null) {
        this.a = (Hashtable)this.a.clone();
      } else {
        this.a = new Hashtable();
      } 
      if (this.b != null) {
        this.b = (Hashtable)this.b.clone();
      } else {
        this.b = new Hashtable();
      } 
      this.c = new Hashtable();
      this.d = new Hashtable();
      this.g = true;
    }
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/xml/sax/helpers/NamespaceSupport.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
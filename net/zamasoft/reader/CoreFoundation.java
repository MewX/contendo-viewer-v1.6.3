package net.zamasoft.reader;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.PointerType;

public interface CoreFoundation extends Library {
  public static final CoreFoundation INSTANCE = (CoreFoundation)Native.loadLibrary("CoreFoundation", CoreFoundation.class);
  
  c CFStringCreateWithCharacters(Object paramObject, char[] paramArrayOfchar, NativeLong paramNativeLong);
  
  int CFArrayGetCount(a parama);
  
  public static class b extends PointerType {}
  
  public static class a extends PointerType {}
  
  public static class d extends PointerType {}
  
  public static class c extends PointerType {}
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/CoreFoundation.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */
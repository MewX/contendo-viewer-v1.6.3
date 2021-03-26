package net.zamasoft.reader;

import com.sun.jna.Native;
import com.sun.jna.PointerType;
import com.sun.jna.win32.StdCallLibrary;

public interface XUser32 extends StdCallLibrary {
  public static final XUser32 INSTANCE = (XUser32)Native.load("user32", XUser32.class);
  
  public static final int ORIENTATION_PREFERENCE_NONE = 0;
  
  public static final int ORIENTATION_PREFERENCE_LANDSCAPE = 1;
  
  public static final int ORIENTATION_PREFERENCE_PORTRAIT = 2;
  
  public static final int ORIENTATION_PREFERENCE_LANDSCAPE_FLIPPED = 4;
  
  public static final int ORIENTATION_PREFERENCE_PORTRAIT_FLIPPED = 8;
  
  public static final int AR_ENABLED = 0;
  
  public static final int AR_DISABLED = 1;
  
  public static final int AR_SUPPRESSED = 2;
  
  public static final int AR_REMOTESESSION = 4;
  
  public static final int AR_MULTIMON = 8;
  
  public static final int AR_NOSENSOR = 16;
  
  public static final int AR_NOT_SUPPORTED = 32;
  
  public static final int AR_DOCKED = 64;
  
  public static final int AR_LAPTOP = 128;
  
  void SetDisplayAutoRotationPreferences(int paramInt);
  
  boolean GetAutoRotationState(PointerType paramPointerType);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/XUser32.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */
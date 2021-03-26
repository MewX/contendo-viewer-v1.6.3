package net.zamasoft.reader;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface XKernel32 extends Library {
  public static final XKernel32 INSTANCE = (XKernel32)Native.load("kernel32", XKernel32.class);
  
  public static final int ES_DISPLAY_REQUIRED = 2;
  
  public static final int ES_SYSTEM_REQUIRED = 1;
  
  public static final int ES_CONTINUOUS = -2147483648;
  
  int SetThreadExecutionState(int paramInt);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/XKernel32.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */
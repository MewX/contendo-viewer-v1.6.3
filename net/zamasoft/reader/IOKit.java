package net.zamasoft.reader;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.PointerType;

public interface IOKit extends Library {
  public static final IOKit INSTANCE = (IOKit)Native.load("IOKit", IOKit.class);
  
  int IOPMAssertionCreateWithName(CoreFoundation.c paramc1, long paramLong, CoreFoundation.c paramc2, PointerType paramPointerType);
  
  int IOPMAssertionRelease(int paramInt);
  
  CoreFoundation.d IOPSCopyPowerSourcesInfo();
  
  CoreFoundation.a IOPSCopyPowerSourcesList(CoreFoundation.d paramd);
  
  CoreFoundation.b IOPSCopyExternalPowerAdapterDetails();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/IOKit.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */
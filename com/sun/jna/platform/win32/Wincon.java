package com.sun.jna.platform.win32;

import com.sun.jna.ptr.IntByReference;

public interface Wincon {
  public static final int ATTACH_PARENT_PROCESS = -1;
  
  public static final int CTRL_C_EVENT = 0;
  
  public static final int CTRL_BREAK_EVENT = 1;
  
  public static final int STD_INPUT_HANDLE = -10;
  
  public static final int STD_OUTPUT_HANDLE = -11;
  
  public static final int STD_ERROR_HANDLE = -12;
  
  public static final int CONSOLE_FULLSCREEN = 1;
  
  public static final int CONSOLE_FULLSCREEN_HARDWARE = 2;
  
  public static final int ENABLE_PROCESSED_INPUT = 1;
  
  public static final int ENABLE_LINE_INPUT = 2;
  
  public static final int ENABLE_ECHO_INPUT = 4;
  
  public static final int ENABLE_WINDOW_INPUT = 8;
  
  public static final int ENABLE_MOUSE_INPUT = 16;
  
  public static final int ENABLE_INSERT_MODE = 32;
  
  public static final int ENABLE_QUICK_EDIT_MODE = 64;
  
  public static final int ENABLE_EXTENDED_FLAGS = 128;
  
  public static final int ENABLE_PROCESSED_OUTPUT = 1;
  
  public static final int ENABLE_WRAP_AT_EOL_OUTPUT = 2;
  
  public static final int MAX_CONSOLE_TITLE_LENGTH = 65536;
  
  boolean AllocConsole();
  
  boolean FreeConsole();
  
  boolean AttachConsole(int paramInt);
  
  boolean FlushConsoleInputBuffer(WinNT.HANDLE paramHANDLE);
  
  boolean GenerateConsoleCtrlEvent(int paramInt1, int paramInt2);
  
  int GetConsoleCP();
  
  boolean SetConsoleCP(int paramInt);
  
  int GetConsoleOutputCP();
  
  boolean SetConsoleOutputCP(int paramInt);
  
  WinDef.HWND GetConsoleWindow();
  
  boolean GetNumberOfConsoleInputEvents(WinNT.HANDLE paramHANDLE, IntByReference paramIntByReference);
  
  boolean GetNumberOfConsoleMouseButtons(IntByReference paramIntByReference);
  
  WinNT.HANDLE GetStdHandle(int paramInt);
  
  boolean SetStdHandle(int paramInt, WinNT.HANDLE paramHANDLE);
  
  boolean GetConsoleDisplayMode(IntByReference paramIntByReference);
  
  boolean GetConsoleMode(WinNT.HANDLE paramHANDLE, IntByReference paramIntByReference);
  
  boolean SetConsoleMode(WinNT.HANDLE paramHANDLE, int paramInt);
  
  int GetConsoleTitle(char[] paramArrayOfchar, int paramInt);
  
  int GetConsoleOriginalTitle(char[] paramArrayOfchar, int paramInt);
  
  boolean SetConsoleTitle(String paramString);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/sun/jna/platform/win32/Wincon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
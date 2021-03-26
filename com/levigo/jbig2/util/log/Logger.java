package com.levigo.jbig2.util.log;

public interface Logger {
  void debug(String paramString);
  
  void debug(String paramString, Throwable paramThrowable);
  
  void info(String paramString);
  
  void info(String paramString, Throwable paramThrowable);
  
  void warn(String paramString);
  
  void warn(String paramString, Throwable paramThrowable);
  
  void fatal(String paramString);
  
  void fatal(String paramString, Throwable paramThrowable);
  
  void error(String paramString);
  
  void error(String paramString, Throwable paramThrowable);
  
  boolean isDebugEnabled();
  
  boolean isInfoEnabled();
  
  boolean isWarnEnabled();
  
  boolean isFatalEnabled();
  
  boolean isErrorEnabled();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/util/log/Logger.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
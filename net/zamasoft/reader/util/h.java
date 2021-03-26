package net.zamasoft.reader.util;

import java.io.File;
import java.io.IOException;
import org.apache.commons.lang3.SystemUtils;

class h extends g {
  public Process a(String paramString) throws IOException {
    if (SystemUtils.IS_OS_WINDOWS_7)
      throw new IOException(); 
    File file = new File("runtime/win/speech.ps1");
    Runtime runtime = Runtime.getRuntime();
    return runtime.exec(new String[] { "powershell", "-executionpolicy", "bypass", "-File", file.getAbsolutePath(), String.valueOf(this.b), String.valueOf(this.a), paramString });
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/util/h.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */
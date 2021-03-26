package net.zamasoft.reader.util;

import java.io.IOException;

class e extends g {
  public Process a(String paramString) throws IOException {
    Runtime runtime = Runtime.getRuntime();
    String str = String.valueOf((this.b / 10) / 10.0D);
    return runtime.exec(new String[] { "say", "-r", String.valueOf((int)(this.a * 175.0D)), "[[volm " + str + "]] " + paramString });
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/util/e.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */
package org.apache.xalan.xsltc.runtime.output;

interface OutputBuffer {
  String close();
  
  OutputBuffer append(char paramChar);
  
  OutputBuffer append(String paramString);
  
  OutputBuffer append(char[] paramArrayOfchar, int paramInt1, int paramInt2);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/runtime/output/OutputBuffer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
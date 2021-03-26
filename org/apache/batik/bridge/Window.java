package org.apache.batik.bridge;

import org.apache.batik.script.Interpreter;
import org.apache.batik.w3c.dom.Window;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public interface Window extends Window {
  Object setInterval(String paramString, long paramLong);
  
  Object setInterval(Runnable paramRunnable, long paramLong);
  
  void clearInterval(Object paramObject);
  
  Object setTimeout(String paramString, long paramLong);
  
  Object setTimeout(Runnable paramRunnable, long paramLong);
  
  void clearTimeout(Object paramObject);
  
  Node parseXML(String paramString, Document paramDocument);
  
  String printNode(Node paramNode);
  
  void getURL(String paramString, URLResponseHandler paramURLResponseHandler);
  
  void getURL(String paramString1, URLResponseHandler paramURLResponseHandler, String paramString2);
  
  void postURL(String paramString1, String paramString2, URLResponseHandler paramURLResponseHandler);
  
  void postURL(String paramString1, String paramString2, URLResponseHandler paramURLResponseHandler, String paramString3);
  
  void postURL(String paramString1, String paramString2, URLResponseHandler paramURLResponseHandler, String paramString3, String paramString4);
  
  void alert(String paramString);
  
  boolean confirm(String paramString);
  
  String prompt(String paramString);
  
  String prompt(String paramString1, String paramString2);
  
  BridgeContext getBridgeContext();
  
  Interpreter getInterpreter();
  
  public static interface URLResponseHandler {
    void getURLDone(boolean param1Boolean, String param1String1, String param1String2);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/Window.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
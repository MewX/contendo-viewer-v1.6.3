package org.apache.xpath;

import java.util.Vector;
import javax.xml.transform.TransformerException;
import org.apache.xpath.functions.FuncExtFunction;

public interface ExtensionsProvider {
  boolean functionAvailable(String paramString1, String paramString2) throws TransformerException;
  
  boolean elementAvailable(String paramString1, String paramString2) throws TransformerException;
  
  Object extFunction(String paramString1, String paramString2, Vector paramVector, Object paramObject) throws TransformerException;
  
  Object extFunction(FuncExtFunction paramFuncExtFunction, Vector paramVector) throws TransformerException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/ExtensionsProvider.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
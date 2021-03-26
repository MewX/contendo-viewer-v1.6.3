package org.apache.batik.util;

public interface ParsedURLProtocolHandler {
  String getProtocolHandled();
  
  ParsedURLData parseURL(String paramString);
  
  ParsedURLData parseURL(ParsedURL paramParsedURL, String paramString);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/ParsedURLProtocolHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
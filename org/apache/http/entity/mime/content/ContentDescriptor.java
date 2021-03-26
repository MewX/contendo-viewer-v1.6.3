package org.apache.http.entity.mime.content;

public interface ContentDescriptor {
  String getMimeType();
  
  String getMediaType();
  
  String getSubType();
  
  String getCharset();
  
  String getTransferEncoding();
  
  long getContentLength();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/entity/mime/content/ContentDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package org.apache.xmlgraphics.image.loader.cache;

public interface ExpirationPolicy {
  boolean isExpired(TimeStampProvider paramTimeStampProvider, long paramLong);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/cache/ExpirationPolicy.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
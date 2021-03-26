/*    */ package org.apache.xmlgraphics.util.io;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface ASCII85Constants
/*    */ {
/*    */   public static final int ZERO = 122;
/* 32 */   public static final byte[] ZERO_ARRAY = new byte[] { 122 };
/*    */   
/*    */   public static final int START = 33;
/*    */   
/*    */   public static final int END = 117;
/*    */   
/*    */   public static final int EOL = 10;
/*    */   
/* 40 */   public static final byte[] EOD = new byte[] { 126, 62 };
/*    */ 
/*    */   
/* 43 */   public static final long[] POW85 = new long[] { 52200625L, 614125L, 7225L, 85L, 1L };
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/io/ASCII85Constants.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.apache.batik.ext.awt.color;
/*    */ 
/*    */ import org.apache.batik.util.SoftReferenceCache;
/*    */ import org.apache.xmlgraphics.java2d.color.ICCColorSpaceWithIntent;
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
/*    */ public class NamedProfileCache
/*    */   extends SoftReferenceCache
/*    */ {
/* 35 */   static NamedProfileCache theCache = new NamedProfileCache();
/*    */   public static NamedProfileCache getDefaultCache() {
/* 37 */     return theCache;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public NamedProfileCache() {
/* 43 */     super(true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public synchronized boolean isPresent(String profileName) {
/* 55 */     return isPresentImpl(profileName);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public synchronized boolean isDone(String profileName) {
/* 65 */     return isDoneImpl(profileName);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public synchronized ICCColorSpaceWithIntent request(String profileName) {
/* 75 */     return (ICCColorSpaceWithIntent)requestImpl(profileName);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public synchronized void clear(String profileName) {
/* 84 */     clearImpl(profileName);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public synchronized void put(String profileName, ICCColorSpaceWithIntent bi) {
/* 95 */     putImpl(profileName, bi);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/color/NamedProfileCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
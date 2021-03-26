/*    */ package org.apache.batik.parser;
/*    */ 
/*    */ import java.util.Calendar;
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
/*    */ public class DefaultTimingSpecifierHandler
/*    */   implements TimingSpecifierHandler
/*    */ {
/* 34 */   public static final TimingSpecifierHandler INSTANCE = new DefaultTimingSpecifierHandler();
/*    */   
/*    */   public void offset(float offset) {}
/*    */   
/*    */   public void syncbase(float offset, String syncbaseID, String timeSymbol) {}
/*    */   
/*    */   public void eventbase(float offset, String eventbaseID, String eventType) {}
/*    */   
/*    */   public void repeat(float offset, String syncbaseID) {}
/*    */   
/*    */   public void repeat(float offset, String syncbaseID, int repeatIteration) {}
/*    */   
/*    */   public void accesskey(float offset, char key) {}
/*    */   
/*    */   public void accessKeySVG12(float offset, String keyName) {}
/*    */   
/*    */   public void mediaMarker(String syncbaseID, String markerName) {}
/*    */   
/*    */   public void wallclock(Calendar time) {}
/*    */   
/*    */   public void indefinite() {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/DefaultTimingSpecifierHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
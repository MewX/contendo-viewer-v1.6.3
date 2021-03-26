/*    */ package jp.cssj.homare.impl.objects.barcode.a;
/*    */ 
/*    */ import org.apache.avalon.framework.configuration.Configuration;
/*    */ import org.apache.avalon.framework.configuration.ConfigurationException;
/*    */ import org.krysalis.barcode4j.ChecksumMode;
/*    */ import org.krysalis.barcode4j.HumanReadablePlacement;
/*    */ import org.krysalis.barcode4j.impl.upcean.UPCEAN;
/*    */ import org.krysalis.barcode4j.tools.Length;
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
/*    */ public class a
/*    */   extends UPCEAN
/*    */ {
/*    */   public void a(Configuration cfg) throws ConfigurationException {
/* 26 */     Configuration c = cfg.getChild("module-width", false);
/* 27 */     if (c != null) {
/* 28 */       Length h = new Length(c.getValue(), "mm");
/* 29 */       getBean().setModuleWidth(h.getValueAsMillimeter());
/*    */     } 
/*    */ 
/*    */     
/* 33 */     c = cfg.getChild("height", false);
/* 34 */     if (c != null) {
/* 35 */       Length h = new Length(c.getValue(), "mm");
/* 36 */       getBean().setHeight(h.getValueAsMillimeter());
/*    */     } 
/*    */ 
/*    */     
/* 40 */     getBean().doQuietZone(cfg.getChild("quiet-zone").getAttributeAsBoolean("enabled", true));
/* 41 */     String qzs = cfg.getChild("quiet-zone").getValue(null);
/* 42 */     if (qzs != null) {
/* 43 */       Length qz = new Length(qzs, "mw");
/* 44 */       if (qz.getUnit().equalsIgnoreCase("mw")) {
/* 45 */         getBean().setQuietZone(qz.getValue() * getBean().getModuleWidth());
/*    */       } else {
/* 47 */         getBean().setQuietZone(qz.getValueAsMillimeter());
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 52 */     String qzvs = cfg.getChild("vertical-quiet-zone").getValue(null);
/* 53 */     if (qzvs != null) {
/* 54 */       Length qz = new Length(qzvs, "in");
/* 55 */       if (qz.getUnit().equalsIgnoreCase("mw")) {
/* 56 */         getBean().setVerticalQuietZone(qz.getValue() * getBean().getModuleWidth());
/*    */       } else {
/* 58 */         getBean().setVerticalQuietZone(qz.getValueAsMillimeter());
/*    */       } 
/*    */     } 
/*    */     
/* 62 */     Configuration hr = cfg.getChild("human-readable", false);
/* 63 */     if (hr != null && (hr.getChildren()).length > 0) {
/*    */       
/* 65 */       String v = hr.getChild("placement").getValue(null);
/* 66 */       if (v != null) {
/* 67 */         getBean().setMsgPosition(HumanReadablePlacement.byName(v));
/*    */       }
/*    */       
/* 70 */       c = hr.getChild("font-size", false);
/* 71 */       if (c != null) {
/* 72 */         Length fs = new Length(c.getValue());
/* 73 */         getBean().setFontSize(fs.getValueAsMillimeter());
/*    */       } 
/*    */       
/* 76 */       getBean().setFontName(hr.getChild("font-name").getValue("OCRB"));
/*    */       
/* 78 */       getBean().setPattern(hr.getChild("pattern").getValue(""));
/*    */     } 
/*    */ 
/*    */     
/* 82 */     getUPCEANBean().setChecksumMode(
/* 83 */         ChecksumMode.byName(cfg.getChild("checksum").getValue(ChecksumMode.CP_AUTO.getName())));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/objects/barcode/a/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package jp.cssj.homare.impl.objects.barcode.c;
/*    */ 
/*    */ import org.apache.avalon.framework.configuration.Configuration;
/*    */ import org.apache.avalon.framework.configuration.ConfigurationException;
/*    */ import org.krysalis.barcode4j.BarcodeDimension;
/*    */ import org.krysalis.barcode4j.impl.ConfigurableBarcodeGenerator;
/*    */ import org.krysalis.barcode4j.tools.Length;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class a
/*    */   extends ConfigurableBarcodeGenerator
/*    */ {
/*    */   public a() {
/* 17 */     this.bean = new b();
/* 18 */     this.bean.setModuleWidth(0.25D);
/*    */   }
/*    */   
/*    */   public void a(Configuration config) throws ConfigurationException {
/* 22 */     b bean = (b)this.bean;
/* 23 */     String mws = config.getChild("module-width").getValue(null);
/* 24 */     if (mws != null) {
/* 25 */       Length mw = new Length(mws, "mm");
/* 26 */       bean.setModuleWidth(mw.getValueAsMillimeter());
/*    */     } 
/*    */ 
/*    */     
/* 30 */     bean.doQuietZone(config.getChild("quiet-zone").getAttributeAsBoolean("enabled", true));
/* 31 */     String qzs = config.getChild("quiet-zone").getValue(null);
/* 32 */     if (qzs != null) {
/* 33 */       Length qz = new Length(qzs, "mw");
/* 34 */       if (qz.getUnit().equalsIgnoreCase("mw")) {
/* 35 */         bean.setQuietZone(qz.getValue() * bean.getModuleWidth());
/*    */       } else {
/* 37 */         bean.setQuietZone(qz.getValueAsMillimeter());
/*    */       } 
/*    */     } else {
/* 40 */       bean.setQuietZone(bean.getModuleWidth());
/*    */     } 
/*    */     
/* 43 */     Configuration version = config.getChild("version", false);
/* 44 */     Configuration encMode = config.getChild("encmode", false);
/* 45 */     Configuration ecc = config.getChild("ecc", false);
/* 46 */     Configuration charset = config.getChild("charset", false);
/* 47 */     if (version != null) {
/* 48 */       bean.a.setQrcodeVersion(Integer.parseInt(version.getValue()));
/*    */     }
/* 50 */     if (encMode != null) {
/* 51 */       bean.a.setQrcodeEncodeMode(encMode.getValue().charAt(0));
/*    */     }
/* 53 */     if (ecc != null) {
/* 54 */       bean.a.setQrcodeErrorCorrect(ecc.getValue().charAt(0));
/*    */     }
/* 56 */     if (charset != null) {
/* 57 */       bean.b = charset.getValue();
/*    */     }
/*    */   }
/*    */   
/*    */   public BarcodeDimension a(String msg) {
/* 62 */     b bean = (b)this.bean;
/* 63 */     boolean[][] code = bean.a(msg);
/* 64 */     double width = (code[0]).length;
/* 65 */     double height = code.length;
/* 66 */     width *= bean.getModuleWidth();
/* 67 */     height *= bean.getModuleWidth();
/* 68 */     if (bean.hasQuietZone()) {
/* 69 */       width += bean.getQuietZone() * 2.0D;
/* 70 */       height += bean.getQuietZone() * 2.0D;
/*    */     } 
/* 72 */     return new BarcodeDimension(width, height);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/objects/barcode/c/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
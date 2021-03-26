/*    */ package jp.cssj.homare.impl.objects.barcode.b;
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
/* 18 */     this.bean.setModuleWidth(0.6D);
/*    */   }
/*    */   
/*    */   public void a(Configuration cfg) throws ConfigurationException {
/* 22 */     b bean = (b)this.bean;
/* 23 */     String mws = cfg.getChild("module-width").getValue(null);
/* 24 */     if (mws != null) {
/* 25 */       Length mw = new Length(mws, "mm");
/* 26 */       bean.setModuleWidth(mw.getValueAsMillimeter());
/*    */     } 
/*    */   }
/*    */   
/*    */   public BarcodeDimension a(String msg) {
/* 31 */     return new BarcodeDimension(this.bean.getModuleWidth() * 133.0D, this.bean.getModuleWidth() * 6.0D);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/objects/barcode/b/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
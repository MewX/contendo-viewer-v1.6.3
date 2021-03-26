/*    */ package jp.cssj.homare.impl.objects.barcode.a;
/*    */ 
/*    */ import org.krysalis.barcode4j.ClassicBarcodeLogicHandler;
/*    */ import org.krysalis.barcode4j.HumanReadablePlacement;
/*    */ import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
/*    */ import org.krysalis.barcode4j.impl.upcean.EAN13LogicImpl;
/*    */ import org.krysalis.barcode4j.impl.upcean.UPCEANBean;
/*    */ import org.krysalis.barcode4j.impl.upcean.UPCEANLogicImpl;
/*    */ import org.krysalis.barcode4j.output.Canvas;
/*    */ import org.krysalis.barcode4j.output.CanvasProvider;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */   extends UPCEANBean
/*    */ {
/*    */   public b() {
/* 19 */     setHeight(14.0D);
/* 20 */     setModuleWidth(0.33D);
/* 21 */     setQuietZone(5.0D);
/* 22 */     setFontSize(3.7D);
/* 23 */     setMsgPosition(HumanReadablePlacement.HRP_BOTTOM);
/*    */   }
/*    */   
/*    */   public UPCEANLogicImpl a() {
/* 27 */     return (UPCEANLogicImpl)new EAN13LogicImpl(getChecksumMode());
/*    */   }
/*    */   
/*    */   public void a(CanvasProvider canvas, String msg) {
/* 31 */     if (msg == null || msg.length() == 0) {
/* 32 */       throw new NullPointerException("Parameter msg must not be empty");
/*    */     }
/*    */     
/* 35 */     ClassicBarcodeLogicHandler handler = new c((AbstractBarcodeBean)this, new Canvas(canvas));
/*    */     
/* 37 */     UPCEANLogicImpl impl = a();
/* 38 */     impl.generateBarcodeLogic(handler, msg);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/objects/barcode/a/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
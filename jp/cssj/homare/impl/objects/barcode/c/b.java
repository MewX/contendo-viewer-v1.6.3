/*    */ package jp.cssj.homare.impl.objects.barcode.c;
/*    */ 
/*    */ import com.swetake.util.Qrcode;
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
/*    */ import org.krysalis.barcode4j.output.CanvasProvider;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */   extends AbstractBarcodeBean
/*    */ {
/* 17 */   protected final Qrcode a = new Qrcode();
/*    */   
/* 19 */   protected String b = "MS932";
/*    */   
/*    */   public boolean[][] a(String msg) {
/*    */     try {
/* 23 */       byte[] data = msg.getBytes(this.b);
/* 24 */       return this.a.calQrcode(data);
/* 25 */     } catch (UnsupportedEncodingException e) {
/* 26 */       throw new RuntimeException(e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void a(CanvasProvider canvas, String msg) {
/* 31 */     boolean[][] code = a(msg);
/* 32 */     int width = (code[0]).length;
/* 33 */     int height = code.length;
/* 34 */     double quietZone = this.quietZone;
/* 35 */     if (!this.doQuietZone) {
/* 36 */       quietZone = 0.0D;
/*    */     }
/* 38 */     for (int x = 0; x < width; x++) {
/* 39 */       for (int y = 0; y < height; y++) {
/* 40 */         if (code[x][y]) {
/* 41 */           canvas.deviceFillRect(x * this.moduleWidth + quietZone, y * this.moduleWidth + quietZone, this.moduleWidth, this.moduleWidth);
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public double a(int arg0) {
/* 50 */     return 0.0D;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/objects/barcode/c/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
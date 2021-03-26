/*     */ package com.levigo.jbig2;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.Insets;
/*     */ import java.awt.MediaTracker;
/*     */ import java.awt.Point;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.IndexColorModel;
/*     */ import java.awt.image.MultiPixelPackedSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JScrollPane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TestImage
/*     */   extends JFrame
/*     */ {
/*     */   private static final long serialVersionUID = 7353175320371957550L;
/*     */   
/*     */   public static void main(String[] paramArrayOfString) {
/*  49 */     char c1 = 'ú';
/*  50 */     char c2 = 'ú';
/*     */ 
/*     */     
/*  53 */     int i = (c1 + 7) / 8;
/*     */ 
/*     */     
/*  56 */     byte[] arrayOfByte = new byte[c2 * i];
/*     */ 
/*     */     
/*  59 */     for (byte b = 0; b < arrayOfByte.length; b++) {
/*  60 */       arrayOfByte[b] = (byte)b;
/*     */     }
/*  62 */     new TestImage(arrayOfByte, c1, c2, i);
/*     */   }
/*     */   
/*     */   static class ImageComponent extends JComponent {
/*     */     private static final long serialVersionUID = -5921296548288376287L;
/*     */     Image myImage;
/*  68 */     int imgWidth = -1;
/*  69 */     int imgHeight = -1;
/*  70 */     Dimension prefSize = null;
/*  71 */     private int scale = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected ImageComponent() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ImageComponent(Image param1Image) {
/*  85 */       setImage(param1Image);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Dimension getPreferredSize() {
/*  94 */       if (this.prefSize != null) {
/*  95 */         return this.prefSize;
/*     */       }
/*  97 */       return super.getPreferredSize();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Dimension getMinimumSize() {
/* 106 */       if (this.prefSize != null) {
/* 107 */         return this.prefSize;
/*     */       }
/* 109 */       return super.getMinimumSize();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setImage(Image param1Image) {
/* 118 */       if (this.myImage != null) {
/* 119 */         this.myImage.flush();
/*     */       }
/*     */       
/* 122 */       this.myImage = param1Image;
/*     */       
/* 124 */       if (this.myImage != null) {
/* 125 */         MediaTracker mediaTracker = new MediaTracker(this);
/*     */         
/* 127 */         mediaTracker.addImage(this.myImage, 0);
/*     */         
/*     */         try {
/* 130 */           mediaTracker.waitForAll();
/* 131 */         } catch (Exception exception) {}
/*     */ 
/*     */         
/* 134 */         this.imgWidth = this.myImage.getWidth(this);
/* 135 */         this.imgHeight = this.myImage.getHeight(this);
/*     */         
/* 137 */         setSize(this.imgWidth * this.scale, this.imgHeight * this.scale);
/* 138 */         this.prefSize = getSize();
/* 139 */         invalidate();
/* 140 */         validate();
/* 141 */         repaint();
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Insets getInsets() {
/* 151 */       return new Insets(1, 1, 1, 1);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void paintComponent(Graphics param1Graphics) {
/* 160 */       Graphics2D graphics2D = (Graphics2D)param1Graphics;
/* 161 */       if (this.myImage != null) {
/* 162 */         graphics2D.scale(this.scale, this.scale);
/* 163 */         graphics2D.drawImage(this.myImage, 1, 1, this.imgWidth, this.imgHeight, this);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void setScale(int param1Int) {
/* 168 */       this.scale = param1Int;
/*     */       
/* 170 */       setSize(this.imgWidth * param1Int, this.imgHeight * param1Int);
/* 171 */       this.prefSize = getSize();
/*     */       
/* 173 */       revalidate();
/* 174 */       repaint();
/*     */     }
/*     */     
/*     */     public int getScale() {
/* 178 */       return this.scale;
/*     */     }
/*     */   }
/*     */   
/*     */   public TestImage(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3) {
/* 183 */     super("Demobild");
/*     */ 
/*     */     
/* 186 */     IndexColorModel indexColorModel = new IndexColorModel(1, 2, new byte[] { -1, 0 }, new byte[] { -1, 0 }, new byte[] { -1, 0 });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 194 */     DataBufferByte dataBufferByte = new DataBufferByte(paramArrayOfbyte, paramArrayOfbyte.length);
/* 195 */     MultiPixelPackedSampleModel multiPixelPackedSampleModel = new MultiPixelPackedSampleModel(0, paramInt1, paramInt2, 1, paramInt3, 0);
/* 196 */     WritableRaster writableRaster = Raster.createWritableRaster(multiPixelPackedSampleModel, dataBufferByte, new Point(0, 0));
/*     */     
/* 198 */     BufferedImage bufferedImage = new BufferedImage(indexColorModel, writableRaster, false, null);
/*     */     
/* 200 */     ImageComponent imageComponent = new ImageComponent(bufferedImage);
/*     */ 
/*     */     
/* 203 */     JScrollPane jScrollPane = new JScrollPane(imageComponent);
/*     */     
/* 205 */     setContentPane(jScrollPane);
/*     */     
/* 207 */     pack();
/* 208 */     setSize(new Dimension(1600, 900));
/* 209 */     setVisible(true);
/*     */     
/*     */     try {
/* 212 */       System.in.read();
/* 213 */     } catch (IOException iOException) {
/*     */       
/* 215 */       iOException.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public TestImage(BufferedImage paramBufferedImage) {
/* 220 */     super("Demobild");
/*     */     
/* 222 */     setDefaultCloseOperation(3);
/*     */     
/* 224 */     ImageComponent imageComponent = new ImageComponent(paramBufferedImage);
/* 225 */     imageComponent.setScale(1);
/*     */     
/* 227 */     JScrollPane jScrollPane = new JScrollPane(imageComponent);
/*     */     
/* 229 */     setContentPane(jScrollPane);
/*     */     
/* 231 */     pack();
/* 232 */     setSize(new Dimension(1600, 900));
/* 233 */     setVisible(true);
/*     */     
/*     */     try {
/* 236 */       System.in.read();
/* 237 */     } catch (IOException iOException) {
/*     */       
/* 239 */       iOException.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/TestImage.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
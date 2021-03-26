/*     */ package c.a.d.b;
/*     */ 
/*     */ import c.a.d.a;
/*     */ import com.github.jaiimageio.jpeg2000.impl.Box;
/*     */ import com.github.jaiimageio.jpeg2000.impl.J2KMetadata;
/*     */ import com.github.jaiimageio.jpeg2000.impl.J2KMetadataFormat;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import javax.imageio.metadata.IIOMetadataNode;
/*     */ import javax.imageio.stream.ImageOutputStream;
/*     */ import org.w3c.dom.NodeList;
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
/*     */ public class a
/*     */   implements a
/*     */ {
/*     */   private File F;
/*     */   private ImageOutputStream G;
/*     */   private int H;
/*     */   private int I;
/*     */   private int J;
/*     */   private int[] K;
/*     */   private boolean L;
/*     */   private int M;
/*     */   private static final int N = 15;
/*     */   private static final int O = 20;
/*     */   private static final int P = 22;
/*     */   private static final int Q = 8;
/*     */   private ColorModel R;
/*     */   private SampleModel S;
/*     */   private J2KMetadata T;
/*     */   private boolean U = false;
/*     */   private int V;
/*     */   J2KMetadataFormat E;
/*     */   
/*     */   public a(File file, ImageOutputStream stream, int height, int width, int nc, int[] bpc, int clength, ColorModel colorModel, SampleModel sampleModel, J2KMetadata metadata) {
/* 152 */     this.H = height;
/* 153 */     this.I = width;
/* 154 */     this.J = nc;
/* 155 */     this.K = bpc;
/* 156 */     this.F = file;
/* 157 */     this.G = stream;
/* 158 */     this.M = clength;
/* 159 */     this.R = colorModel;
/* 160 */     this.S = sampleModel;
/* 161 */     this.T = metadata;
/*     */     
/* 163 */     if (colorModel instanceof java.awt.image.IndexColorModel) {
/* 164 */       this.U = true;
/*     */     }
/* 166 */     this.L = false;
/* 167 */     int fixbpc = bpc[0];
/* 168 */     for (int i = nc - 1; i > 0; i--) {
/* 169 */       if (bpc[i] != fixbpc) {
/* 170 */         this.L = true;
/*     */       }
/*     */     } 
/*     */   }
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
/*     */   public int a() throws IOException {
/* 185 */     a(this.T);
/*     */ 
/*     */     
/* 188 */     b();
/*     */     
/* 190 */     return 15 + this.V;
/*     */   }
/*     */   
/*     */   private void a(J2KMetadata metadata) throws IOException {
/* 194 */     if (metadata == null) {
/*     */       return;
/*     */     }
/*     */     
/* 198 */     IIOMetadataNode root = (IIOMetadataNode)metadata.getAsTree("com_sun_media_imageio_plugins_jpeg2000_image_1.0");
/* 199 */     if (root == null)
/*     */       return; 
/* 201 */     this.E = (J2KMetadataFormat)metadata.getMetadataFormat("com_sun_media_imageio_plugins_jpeg2000_image_1.0");
/* 202 */     a(root);
/*     */   }
/*     */   
/*     */   private void a(IIOMetadataNode node) throws IOException {
/* 206 */     NodeList list = node.getChildNodes();
/*     */     
/* 208 */     String name = node.getNodeName();
/* 209 */     if (name.startsWith("JPEG2000")) {
/* 210 */       this.G.writeInt(c(node));
/* 211 */       this.G.writeInt(Box.getTypeInt(Box.getTypeByName(name)));
/* 212 */       this.V += 8;
/*     */     } 
/*     */     
/* 215 */     for (int i = 0; i < list.getLength(); i++) {
/* 216 */       IIOMetadataNode child = (IIOMetadataNode)list.item(i);
/*     */       
/* 218 */       name = child.getNodeName();
/* 219 */       if (name.startsWith("JPEG2000") && this.E.isLeaf(name)) {
/* 220 */         b(child);
/*     */       } else {
/* 222 */         a(child);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private void b(IIOMetadataNode node) throws IOException {
/* 227 */     int type = Box.getTypeInt((String)Box.getAttribute(node, "Type"));
/* 228 */     int length = (new Integer((String)Box.getAttribute(node, "Length"))).intValue();
/* 229 */     Box box = Box.createBox(type, node);
/* 230 */     this.V += length;
/* 231 */     this.G.writeInt(length);
/* 232 */     this.G.writeInt(type);
/* 233 */     byte[] data = box.getContent();
/* 234 */     this.G.write(data, 0, data.length);
/*     */   }
/*     */   
/*     */   private int c(IIOMetadataNode root) {
/* 238 */     NodeList list = root.getChildNodes();
/* 239 */     int length = 0;
/* 240 */     for (int i = 0; i < list.getLength(); i++) {
/* 241 */       IIOMetadataNode node = (IIOMetadataNode)list.item(i);
/* 242 */       String name = node.getNodeName();
/*     */       
/* 244 */       if (this.E.isLeaf(name)) {
/* 245 */         length += (new Integer((String)Box.getAttribute(node, "Length"))).intValue();
/*     */       } else {
/* 247 */         length += c(node);
/*     */       } 
/*     */     } 
/*     */     
/* 251 */     return length + (root.getNodeName().startsWith("JPEG2000") ? 8 : 0);
/*     */   }
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
/*     */   public void b() throws IOException {
/* 264 */     if (this.T != null) {
/*     */ 
/*     */ 
/*     */       
/* 268 */       this.G.writeInt(this.M + 8);
/*     */ 
/*     */       
/* 271 */       this.G.writeInt(1785737827);
/*     */     } 
/*     */     
/* 274 */     c.a.f.a fi = new c.a.f.a(this.F, "rw+");
/*     */     
/* 276 */     int remainder = this.M;
/* 277 */     byte[] codestream = new byte[1024];
/*     */     
/* 279 */     while (remainder > 0) {
/* 280 */       int len = (remainder > 1024) ? 1024 : remainder;
/* 281 */       fi.readFully(codestream, 0, len);
/*     */ 
/*     */       
/* 284 */       this.G.write(codestream, 0, len);
/* 285 */       remainder -= len;
/*     */     } 
/*     */ 
/*     */     
/* 289 */     fi.close();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/d/b/a.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
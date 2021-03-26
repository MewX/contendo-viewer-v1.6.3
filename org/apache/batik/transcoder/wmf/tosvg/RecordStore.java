/*     */ package org.apache.batik.transcoder.wmf.tosvg;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.util.Vector;
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
/*     */ public class RecordStore
/*     */ {
/*     */   private transient URL url;
/*     */   protected transient int numRecords;
/*     */   protected transient int numObjects;
/*     */   public transient int lastObjectIdx;
/*     */   protected transient int vpX;
/*     */   protected transient int vpY;
/*     */   protected transient int vpW;
/*     */   protected transient int vpH;
/*     */   protected transient Vector records;
/*     */   protected transient Vector objectVector;
/*     */   protected transient boolean bReading;
/*     */   
/*     */   public RecordStore() {
/* 281 */     this.bReading = false;
/*     */     reset();
/*     */   }
/*     */   
/*     */   public void reset() {
/*     */     this.numRecords = 0;
/*     */     this.vpX = 0;
/*     */     this.vpY = 0;
/*     */     this.vpW = 1000;
/*     */     this.vpH = 1000;
/*     */     this.numObjects = 0;
/*     */     this.records = new Vector(20, 20);
/*     */     this.objectVector = new Vector();
/*     */   }
/*     */   
/*     */   synchronized void setReading(boolean state) {
/*     */     this.bReading = state;
/*     */   }
/*     */   
/*     */   synchronized boolean isReading() {
/*     */     return this.bReading;
/*     */   }
/*     */   
/*     */   public boolean read(DataInputStream is) throws IOException {
/*     */     setReading(true);
/*     */     reset();
/*     */     int functionId = 0;
/*     */     this.numRecords = 0;
/*     */     this.numObjects = is.readShort();
/*     */     this.objectVector.ensureCapacity(this.numObjects);
/*     */     for (int i = 0; i < this.numObjects; i++)
/*     */       this.objectVector.add(new GdiObject(i, false)); 
/*     */     while (functionId != -1) {
/*     */       MetaRecord mr;
/*     */       short len;
/*     */       byte[] b;
/*     */       int k;
/*     */       String str;
/*     */       functionId = is.readShort();
/*     */       if (functionId == -1)
/*     */         break; 
/*     */       switch (functionId) {
/*     */         case 763:
/*     */         case 1313:
/*     */         case 1583:
/*     */         case 2610:
/*     */           len = is.readShort();
/*     */           b = new byte[len];
/*     */           for (k = 0; k < len; k++)
/*     */             b[k] = is.readByte(); 
/*     */           str = new String(b);
/*     */           mr = new MetaRecord.StringRecord(str);
/*     */           break;
/*     */         default:
/*     */           mr = new MetaRecord();
/*     */           break;
/*     */       } 
/*     */       int numPts = is.readShort();
/*     */       mr.numPoints = numPts;
/*     */       mr.functionId = functionId;
/*     */       for (int j = 0; j < numPts; j++)
/*     */         mr.AddElement(Integer.valueOf(is.readShort())); 
/*     */       this.records.add(mr);
/*     */       this.numRecords++;
/*     */     } 
/*     */     setReading(false);
/*     */     return true;
/*     */   }
/*     */   
/*     */   public void addObject(int type, Object obj) {
/*     */     for (int i = 0; i < this.numObjects; i++) {
/*     */       GdiObject gdi = this.objectVector.get(i);
/*     */       if (!gdi.used) {
/*     */         gdi.Setup(type, obj);
/*     */         this.lastObjectIdx = i;
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addObjectAt(int type, Object obj, int idx) {
/*     */     if (idx == 0 || idx > this.numObjects) {
/*     */       addObject(type, obj);
/*     */       return;
/*     */     } 
/*     */     this.lastObjectIdx = idx;
/*     */     for (int i = 0; i < this.numObjects; i++) {
/*     */       GdiObject gdi = this.objectVector.get(i);
/*     */       if (i == idx) {
/*     */         gdi.Setup(type, obj);
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public URL getUrl() {
/*     */     return this.url;
/*     */   }
/*     */   
/*     */   public void setUrl(URL newUrl) {
/*     */     this.url = newUrl;
/*     */   }
/*     */   
/*     */   public GdiObject getObject(int idx) {
/*     */     return this.objectVector.get(idx);
/*     */   }
/*     */   
/*     */   public MetaRecord getRecord(int idx) {
/*     */     return this.records.get(idx);
/*     */   }
/*     */   
/*     */   public int getNumRecords() {
/*     */     return this.numRecords;
/*     */   }
/*     */   
/*     */   public int getNumObjects() {
/*     */     return this.numObjects;
/*     */   }
/*     */   
/*     */   public int getVpX() {
/*     */     return this.vpX;
/*     */   }
/*     */   
/*     */   public int getVpY() {
/*     */     return this.vpY;
/*     */   }
/*     */   
/*     */   public int getVpW() {
/*     */     return this.vpW;
/*     */   }
/*     */   
/*     */   public int getVpH() {
/*     */     return this.vpH;
/*     */   }
/*     */   
/*     */   public void setVpX(int newValue) {
/*     */     this.vpX = newValue;
/*     */   }
/*     */   
/*     */   public void setVpY(int newValue) {
/*     */     this.vpY = newValue;
/*     */   }
/*     */   
/*     */   public void setVpW(int newValue) {
/*     */     this.vpW = newValue;
/*     */   }
/*     */   
/*     */   public void setVpH(int newValue) {
/*     */     this.vpH = newValue;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/wmf/tosvg/RecordStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
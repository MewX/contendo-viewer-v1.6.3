/*     */ package org.apache.commons.io.input;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ObservableInputStream
/*     */   extends ProxyInputStream
/*     */ {
/*     */   public static abstract class Observer
/*     */   {
/*     */     void data(int pByte) throws IOException {}
/*     */     
/*     */     void data(byte[] pBuffer, int pOffset, int pLength) throws IOException {}
/*     */     
/*     */     void finished() throws IOException {}
/*     */     
/*     */     void closed() throws IOException {}
/*     */     
/*     */     void error(IOException pException) throws IOException {
/*  77 */       throw pException;
/*     */     } }
/*     */   
/*  80 */   private final List<Observer> observers = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ObservableInputStream(InputStream pProxy) {
/*  87 */     super(pProxy);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(Observer pObserver) {
/*  95 */     this.observers.add(pObserver);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(Observer pObserver) {
/* 103 */     this.observers.remove(pObserver);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeAllObservers() {
/* 110 */     this.observers.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/* 115 */     int result = 0;
/* 116 */     IOException ioe = null;
/*     */     try {
/* 118 */       result = super.read();
/* 119 */     } catch (IOException pException) {
/* 120 */       ioe = pException;
/*     */     } 
/* 122 */     if (ioe != null) {
/* 123 */       noteError(ioe);
/* 124 */     } else if (result == -1) {
/* 125 */       noteFinished();
/*     */     } else {
/* 127 */       noteDataByte(result);
/*     */     } 
/* 129 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public int read(byte[] pBuffer) throws IOException {
/* 134 */     int result = 0;
/* 135 */     IOException ioe = null;
/*     */     try {
/* 137 */       result = super.read(pBuffer);
/* 138 */     } catch (IOException pException) {
/* 139 */       ioe = pException;
/*     */     } 
/* 141 */     if (ioe != null) {
/* 142 */       noteError(ioe);
/* 143 */     } else if (result == -1) {
/* 144 */       noteFinished();
/* 145 */     } else if (result > 0) {
/* 146 */       noteDataBytes(pBuffer, 0, result);
/*     */     } 
/* 148 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public int read(byte[] pBuffer, int pOffset, int pLength) throws IOException {
/* 153 */     int result = 0;
/* 154 */     IOException ioe = null;
/*     */     try {
/* 156 */       result = super.read(pBuffer, pOffset, pLength);
/* 157 */     } catch (IOException pException) {
/* 158 */       ioe = pException;
/*     */     } 
/* 160 */     if (ioe != null) {
/* 161 */       noteError(ioe);
/* 162 */     } else if (result == -1) {
/* 163 */       noteFinished();
/* 164 */     } else if (result > 0) {
/* 165 */       noteDataBytes(pBuffer, pOffset, result);
/*     */     } 
/* 167 */     return result;
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
/*     */   protected void noteDataBytes(byte[] pBuffer, int pOffset, int pLength) throws IOException {
/* 179 */     for (Observer observer : getObservers()) {
/* 180 */       observer.data(pBuffer, pOffset, pLength);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void noteFinished() throws IOException {
/* 189 */     for (Observer observer : getObservers()) {
/* 190 */       observer.finished();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void noteDataByte(int pDataByte) throws IOException {
/* 201 */     for (Observer observer : getObservers()) {
/* 202 */       observer.data(pDataByte);
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
/*     */   protected void noteError(IOException pException) throws IOException {
/* 214 */     for (Observer observer : getObservers()) {
/* 215 */       observer.error(pException);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void noteClosed() throws IOException {
/* 224 */     for (Observer observer : getObservers()) {
/* 225 */       observer.closed();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List<Observer> getObservers() {
/* 233 */     return this.observers;
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 238 */     IOException ioe = null;
/*     */     try {
/* 240 */       super.close();
/* 241 */     } catch (IOException e) {
/* 242 */       ioe = e;
/*     */     } 
/* 244 */     if (ioe == null) {
/* 245 */       noteClosed();
/*     */     } else {
/* 247 */       noteError(ioe);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void consume() throws IOException {
/*     */     int res;
/* 257 */     byte[] buffer = new byte[8192];
/*     */     do {
/* 259 */       res = read(buffer);
/* 260 */     } while (res != -1);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/input/ObservableInputStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */
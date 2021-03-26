/*     */ package org.apache.pdfbox.multipdf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSObject;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDFCloneUtility
/*     */ {
/*     */   private final PDDocument destination;
/*  47 */   private final Map<Object, COSBase> clonedVersion = new HashMap<Object, COSBase>();
/*  48 */   private final Set<COSBase> clonedValues = new HashSet<COSBase>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFCloneUtility(PDDocument dest) {
/*  56 */     this.destination = dest;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDDocument getDestination() {
/*  65 */     return this.destination;
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
/*     */   public COSBase cloneForNewDocument(Object base) throws IOException {
/*  77 */     if (base == null)
/*     */     {
/*  79 */       return null;
/*     */     }
/*  81 */     COSBase retval = this.clonedVersion.get(base);
/*  82 */     if (retval != null)
/*     */     {
/*     */       
/*  85 */       return retval;
/*     */     }
/*  87 */     if (base instanceof COSBase && this.clonedValues.contains(base))
/*     */     {
/*     */       
/*  90 */       return (COSBase)base;
/*     */     }
/*  92 */     if (base instanceof List) {
/*     */       
/*  94 */       COSArray array = new COSArray();
/*  95 */       List<?> list = (List)base;
/*  96 */       for (Object obj : list)
/*     */       {
/*  98 */         array.add(cloneForNewDocument(obj));
/*     */       }
/* 100 */       COSArray cOSArray1 = array;
/*     */     }
/* 102 */     else if (base instanceof COSObjectable && !(base instanceof COSBase)) {
/*     */       
/* 104 */       retval = cloneForNewDocument(((COSObjectable)base).getCOSObject());
/*     */     }
/* 106 */     else if (base instanceof COSObject) {
/*     */       
/* 108 */       COSObject object = (COSObject)base;
/* 109 */       retval = cloneForNewDocument(object.getObject());
/*     */     }
/* 111 */     else if (base instanceof COSArray) {
/*     */       
/* 113 */       COSArray newArray = new COSArray();
/* 114 */       COSArray array = (COSArray)base;
/* 115 */       for (int i = 0; i < array.size(); i++)
/*     */       {
/* 117 */         newArray.add(cloneForNewDocument(array.get(i)));
/*     */       }
/* 119 */       COSArray cOSArray1 = newArray;
/*     */     }
/* 121 */     else if (base instanceof COSStream) {
/*     */       
/* 123 */       COSStream originalStream = (COSStream)base;
/* 124 */       COSStream stream = this.destination.getDocument().createCOSStream();
/* 125 */       OutputStream output = stream.createRawOutputStream();
/* 126 */       InputStream input = originalStream.createRawInputStream();
/* 127 */       IOUtils.copy(input, output);
/* 128 */       input.close();
/* 129 */       output.close();
/* 130 */       this.clonedVersion.put(base, stream);
/* 131 */       for (Map.Entry<COSName, COSBase> entry : (Iterable<Map.Entry<COSName, COSBase>>)originalStream.entrySet())
/*     */       {
/* 133 */         stream.setItem(entry.getKey(), cloneForNewDocument(entry.getValue()));
/*     */       }
/* 135 */       COSStream cOSStream1 = stream;
/*     */     }
/* 137 */     else if (base instanceof COSDictionary) {
/*     */       
/* 139 */       COSDictionary dic = (COSDictionary)base;
/* 140 */       COSDictionary cOSDictionary1 = new COSDictionary();
/* 141 */       this.clonedVersion.put(base, cOSDictionary1);
/* 142 */       for (Map.Entry<COSName, COSBase> entry : (Iterable<Map.Entry<COSName, COSBase>>)dic.entrySet())
/*     */       {
/* 144 */         cOSDictionary1.setItem(entry
/* 145 */             .getKey(), 
/* 146 */             cloneForNewDocument(entry.getValue()));
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 151 */       retval = (COSBase)base;
/*     */     } 
/* 153 */     this.clonedVersion.put(base, retval);
/* 154 */     this.clonedValues.add(retval);
/* 155 */     return retval;
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
/*     */   public void cloneMerge(COSObjectable base, COSObjectable target) throws IOException {
/* 168 */     if (base == null) {
/*     */       return;
/*     */     }
/*     */     
/* 172 */     COSBase cOSBase = this.clonedVersion.get(base);
/* 173 */     if (cOSBase != null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 179 */     if (!(base instanceof COSBase)) {
/*     */       
/* 181 */       cloneMerge((COSObjectable)base.getCOSObject(), (COSObjectable)target.getCOSObject());
/*     */     }
/* 183 */     else if (base instanceof COSObject) {
/*     */       
/* 185 */       if (target instanceof COSObject)
/*     */       {
/* 187 */         cloneMerge((COSObjectable)((COSObject)base).getObject(), (COSObjectable)((COSObject)target).getObject());
/*     */       }
/* 189 */       else if (target instanceof COSDictionary || target instanceof COSArray)
/*     */       {
/* 191 */         cloneMerge((COSObjectable)((COSObject)base).getObject(), target);
/*     */       }
/*     */     
/* 194 */     } else if (base instanceof COSArray) {
/*     */       
/* 196 */       COSArray array = (COSArray)base;
/* 197 */       for (int i = 0; i < array.size(); i++)
/*     */       {
/* 199 */         ((COSArray)target).add(cloneForNewDocument(array.get(i))); } 
/*     */     } else {
/*     */       COSStream cOSStream;
/* 202 */       if (base instanceof COSStream) {
/*     */ 
/*     */         
/* 205 */         COSStream originalStream = (COSStream)base;
/* 206 */         COSStream stream = this.destination.getDocument().createCOSStream();
/* 207 */         OutputStream output = stream.createOutputStream(originalStream.getFilters());
/* 208 */         IOUtils.copy((InputStream)originalStream.createInputStream(), output);
/* 209 */         output.close();
/* 210 */         this.clonedVersion.put(base, stream);
/* 211 */         for (Map.Entry<COSName, COSBase> entry : (Iterable<Map.Entry<COSName, COSBase>>)originalStream.entrySet())
/*     */         {
/* 213 */           stream.setItem(entry
/* 214 */               .getKey(), 
/* 215 */               cloneForNewDocument(entry.getValue()));
/*     */         }
/* 217 */         cOSStream = stream;
/*     */       }
/* 219 */       else if (base instanceof COSDictionary) {
/*     */         
/* 221 */         COSDictionary dic = (COSDictionary)base;
/* 222 */         this.clonedVersion.put(base, cOSStream);
/* 223 */         for (Map.Entry<COSName, COSBase> entry : (Iterable<Map.Entry<COSName, COSBase>>)dic.entrySet())
/*     */         {
/* 225 */           COSName key = entry.getKey();
/* 226 */           COSBase value = entry.getValue();
/* 227 */           if (((COSDictionary)target).getItem(key) != null) {
/*     */             
/* 229 */             cloneMerge((COSObjectable)value, (COSObjectable)((COSDictionary)target).getItem(key));
/*     */             
/*     */             continue;
/*     */           } 
/* 233 */           ((COSDictionary)target).setItem(key, cloneForNewDocument(value));
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 239 */         cOSBase = (COSBase)base;
/*     */       } 
/* 241 */     }  this.clonedVersion.put(base, cOSBase);
/* 242 */     this.clonedValues.add(cOSBase);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/multipdf/PDFCloneUtility.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package org.apache.xmlgraphics.java2d.color.profile;
/*     */ 
/*     */ import java.awt.color.ICC_Profile;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.IOException;
/*     */ import org.apache.xmlgraphics.java2d.color.CIELabColorSpace;
/*     */ import org.apache.xmlgraphics.java2d.color.ColorSpaces;
/*     */ import org.apache.xmlgraphics.java2d.color.NamedColorSpace;
/*     */ import org.apache.xmlgraphics.java2d.color.RenderingIntent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NamedColorProfileParser
/*     */ {
/*     */   private static final int MLUC = 1835824483;
/*     */   private static final int NCL2 = 1852009522;
/*     */   
/*     */   public static boolean isNamedColorProfile(ICC_Profile profile) {
/*  49 */     return (profile.getProfileClass() == 6);
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
/*     */   public NamedColorProfile parseProfile(ICC_Profile profile, String profileName, String profileURI) throws IOException {
/*  62 */     if (!isNamedColorProfile(profile)) {
/*  63 */       throw new IllegalArgumentException("Given profile is not a named color profile (NCP)");
/*     */     }
/*  65 */     String profileDescription = getProfileDescription(profile);
/*  66 */     String copyright = getCopyright(profile);
/*  67 */     RenderingIntent intent = getRenderingIntent(profile);
/*  68 */     NamedColorSpace[] ncs = readNamedColors(profile, profileName, profileURI);
/*  69 */     return new NamedColorProfile(profileDescription, copyright, ncs, intent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NamedColorProfile parseProfile(ICC_Profile profile) throws IOException {
/*  79 */     return parseProfile(profile, null, null);
/*     */   }
/*     */   
/*     */   private String getProfileDescription(ICC_Profile profile) throws IOException {
/*  83 */     byte[] tag = profile.getData(1684370275);
/*  84 */     return readSimpleString(tag);
/*     */   }
/*     */   
/*     */   private String getCopyright(ICC_Profile profile) throws IOException {
/*  88 */     byte[] tag = profile.getData(1668313716);
/*  89 */     return readSimpleString(tag);
/*     */   }
/*     */   
/*     */   private RenderingIntent getRenderingIntent(ICC_Profile profile) throws IOException {
/*  93 */     byte[] hdr = profile.getData(1751474532);
/*  94 */     int value = hdr[64];
/*  95 */     return RenderingIntent.fromICCValue(value);
/*     */   }
/*     */ 
/*     */   
/*     */   private NamedColorSpace[] readNamedColors(ICC_Profile profile, String profileName, String profileURI) throws IOException {
/* 100 */     byte[] tag = profile.getData(1852009522);
/* 101 */     DataInput din = new DataInputStream(new ByteArrayInputStream(tag));
/* 102 */     int sig = din.readInt();
/* 103 */     if (sig != 1852009522) {
/* 104 */       throw new UnsupportedOperationException("Unsupported structure type: " + toSignatureString(sig) + ". Expected " + toSignatureString(1852009522));
/*     */     }
/*     */     
/* 107 */     din.skipBytes(8);
/* 108 */     int numColors = din.readInt();
/* 109 */     NamedColorSpace[] result = new NamedColorSpace[numColors];
/* 110 */     int numDeviceCoord = din.readInt();
/* 111 */     String prefix = readAscii(din, 32);
/* 112 */     String suffix = readAscii(din, 32);
/* 113 */     for (int i = 0; i < numColors; i++) {
/* 114 */       CIELabColorSpace labCS; String name = prefix + readAscii(din, 32) + suffix;
/* 115 */       int[] pcs = readUInt16Array(din, 3);
/* 116 */       float[] colorvalue = new float[3];
/* 117 */       for (int j = 0; j < pcs.length; j++) {
/* 118 */         colorvalue[j] = pcs[j] / 32768.0F;
/*     */       }
/*     */ 
/*     */       
/* 122 */       readUInt16Array(din, numDeviceCoord);
/*     */       
/* 124 */       switch (profile.getPCSType()) {
/*     */         case 0:
/* 126 */           result[i] = new NamedColorSpace(name, colorvalue, profileName, profileURI);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 1:
/* 131 */           labCS = ColorSpaces.getCIELabColorSpaceD50();
/* 132 */           result[i] = new NamedColorSpace(name, labCS.toColor(colorvalue, 1.0F), profileName, profileURI);
/*     */           break;
/*     */         
/*     */         default:
/* 136 */           throw new UnsupportedOperationException("PCS type is not supported: " + profile.getPCSType());
/*     */       } 
/*     */     
/*     */     } 
/* 140 */     return result;
/*     */   }
/*     */   
/*     */   private int[] readUInt16Array(DataInput din, int count) throws IOException {
/* 144 */     if (count == 0) {
/* 145 */       return new int[0];
/*     */     }
/* 147 */     int[] result = new int[count];
/* 148 */     for (int i = 0; i < count; i++) {
/* 149 */       int v = din.readUnsignedShort();
/* 150 */       result[i] = v;
/*     */     } 
/* 152 */     return result;
/*     */   }
/*     */   
/*     */   private String readAscii(DataInput in, int maxLength) throws IOException {
/* 156 */     byte[] data = new byte[maxLength];
/* 157 */     in.readFully(data);
/* 158 */     String result = new String(data, "US-ASCII");
/* 159 */     int idx = result.indexOf(false);
/* 160 */     if (idx >= 0) {
/* 161 */       result = result.substring(0, idx);
/*     */     }
/* 163 */     return result;
/*     */   }
/*     */   
/*     */   private String readSimpleString(byte[] tag) throws IOException {
/* 167 */     DataInput din = new DataInputStream(new ByteArrayInputStream(tag));
/* 168 */     int sig = din.readInt();
/* 169 */     if (sig == 1835824483) {
/* 170 */       return readMLUC(din);
/*     */     }
/* 172 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private String readMLUC(DataInput din) throws IOException {
/* 177 */     din.skipBytes(16);
/* 178 */     int firstLength = din.readInt();
/* 179 */     int firstOffset = din.readInt();
/* 180 */     int offset = 28;
/* 181 */     din.skipBytes(firstOffset - offset);
/* 182 */     byte[] utf16 = new byte[firstLength];
/* 183 */     din.readFully(utf16);
/* 184 */     return new String(utf16, "UTF-16BE");
/*     */   }
/*     */   
/*     */   private String toSignatureString(int sig) {
/* 188 */     StringBuffer sb = new StringBuffer();
/* 189 */     sb.append((char)(sig >> 24 & 0xFF));
/* 190 */     sb.append((char)(sig >> 16 & 0xFF));
/* 191 */     sb.append((char)(sig >> 8 & 0xFF));
/* 192 */     sb.append((char)(sig & 0xFF));
/* 193 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/color/profile/NamedColorProfileParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
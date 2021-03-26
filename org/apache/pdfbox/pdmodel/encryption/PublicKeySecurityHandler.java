/*     */ package org.apache.pdfbox.pdmodel.encryption;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.math.BigInteger;
/*     */ import java.security.AlgorithmParameterGenerator;
/*     */ import java.security.AlgorithmParameters;
/*     */ import java.security.GeneralSecurityException;
/*     */ import java.security.InvalidKeyException;
/*     */ import java.security.KeyStoreException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.PrivateKey;
/*     */ import java.security.SecureRandom;
/*     */ import java.security.cert.CertificateEncodingException;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import javax.crypto.BadPaddingException;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.IllegalBlockSizeException;
/*     */ import javax.crypto.KeyGenerator;
/*     */ import javax.crypto.NoSuchPaddingException;
/*     */ import javax.crypto.SecretKey;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSString;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.bouncycastle.asn1.ASN1Encodable;
/*     */ import org.bouncycastle.asn1.ASN1InputStream;
/*     */ import org.bouncycastle.asn1.ASN1ObjectIdentifier;
/*     */ import org.bouncycastle.asn1.ASN1OctetString;
/*     */ import org.bouncycastle.asn1.ASN1Primitive;
/*     */ import org.bouncycastle.asn1.ASN1Set;
/*     */ import org.bouncycastle.asn1.DEROctetString;
/*     */ import org.bouncycastle.asn1.DEROutputStream;
/*     */ import org.bouncycastle.asn1.DERSet;
/*     */ import org.bouncycastle.asn1.cms.ContentInfo;
/*     */ import org.bouncycastle.asn1.cms.EncryptedContentInfo;
/*     */ import org.bouncycastle.asn1.cms.EnvelopedData;
/*     */ import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
/*     */ import org.bouncycastle.asn1.cms.KeyTransRecipientInfo;
/*     */ import org.bouncycastle.asn1.cms.RecipientIdentifier;
/*     */ import org.bouncycastle.asn1.cms.RecipientInfo;
/*     */ import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
/*     */ import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/*     */ import org.bouncycastle.asn1.x509.TBSCertificate;
/*     */ import org.bouncycastle.cert.X509CertificateHolder;
/*     */ import org.bouncycastle.cms.CMSEnvelopedData;
/*     */ import org.bouncycastle.cms.CMSException;
/*     */ import org.bouncycastle.cms.KeyTransRecipientId;
/*     */ import org.bouncycastle.cms.Recipient;
/*     */ import org.bouncycastle.cms.RecipientId;
/*     */ import org.bouncycastle.cms.RecipientInformation;
/*     */ import org.bouncycastle.cms.jcajce.JceKeyTransEnvelopedRecipient;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PublicKeySecurityHandler
/*     */   extends SecurityHandler
/*     */ {
/*     */   public static final String FILTER = "Adobe.PubSec";
/*     */   private static final String SUBFILTER4 = "adbe.pkcs7.s4";
/*     */   private static final String SUBFILTER5 = "adbe.pkcs7.s5";
/*  86 */   private PublicKeyProtectionPolicy policy = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PublicKeySecurityHandler() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PublicKeySecurityHandler(PublicKeyProtectionPolicy p) {
/* 102 */     this.policy = p;
/* 103 */     this.keyLength = this.policy.getEncryptionKeyLength();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void prepareForDecryption(PDEncryption encryption, COSArray documentIDArray, DecryptionMaterial decryptionMaterial) throws IOException {
/* 125 */     if (!(decryptionMaterial instanceof PublicKeyDecryptionMaterial))
/*     */     {
/* 127 */       throw new IOException("Provided decryption material is not compatible with the document");
/*     */     }
/*     */ 
/*     */     
/* 131 */     setDecryptMetadata(encryption.isEncryptMetaData());
/* 132 */     if (encryption.getLength() != 0)
/*     */     {
/* 134 */       this.keyLength = encryption.getLength();
/*     */     }
/*     */     
/* 137 */     PublicKeyDecryptionMaterial material = (PublicKeyDecryptionMaterial)decryptionMaterial;
/*     */     
/*     */     try {
/*     */       byte[] mdResult;
/* 141 */       boolean foundRecipient = false;
/*     */       
/* 143 */       X509Certificate certificate = material.getCertificate();
/* 144 */       X509CertificateHolder materialCert = null;
/* 145 */       if (certificate != null)
/*     */       {
/* 147 */         materialCert = new X509CertificateHolder(certificate.getEncoded());
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 152 */       byte[] envelopedData = null;
/*     */ 
/*     */       
/* 155 */       COSArray array = (COSArray)encryption.getCOSObject().getItem(COSName.RECIPIENTS);
/* 156 */       if (array == null) {
/*     */         
/* 158 */         PDCryptFilterDictionary defaultCryptFilterDictionary = encryption.getDefaultCryptFilterDictionary();
/* 159 */         array = (COSArray)defaultCryptFilterDictionary.getCOSObject().getItem(COSName.RECIPIENTS);
/*     */       } 
/* 161 */       byte[][] recipientFieldsBytes = new byte[array.size()][];
/*     */ 
/*     */       
/* 164 */       int recipientFieldsLength = 0;
/* 165 */       StringBuilder extraInfo = new StringBuilder();
/* 166 */       for (int i = 0; i < array.size(); i++) {
/*     */         
/* 168 */         COSString recipientFieldString = (COSString)array.getObject(i);
/* 169 */         byte[] recipientBytes = recipientFieldString.getBytes();
/* 170 */         CMSEnvelopedData data = new CMSEnvelopedData(recipientBytes);
/*     */         
/* 172 */         Collection<RecipientInformation> recipCertificatesIt = data.getRecipientInfos().getRecipients();
/* 173 */         int j = 0;
/* 174 */         for (RecipientInformation ri : recipCertificatesIt) {
/*     */ 
/*     */ 
/*     */           
/* 178 */           RecipientId rid = ri.getRID();
/* 179 */           if (!foundRecipient && rid.match(materialCert)) {
/*     */             
/* 181 */             foundRecipient = true;
/* 182 */             PrivateKey privateKey = (PrivateKey)material.getPrivateKey();
/*     */ 
/*     */             
/* 185 */             envelopedData = ri.getContent((Recipient)new JceKeyTransEnvelopedRecipient(privateKey));
/*     */             break;
/*     */           } 
/* 188 */           j++;
/* 189 */           if (certificate != null) {
/*     */             
/* 191 */             extraInfo.append('\n');
/* 192 */             extraInfo.append(j);
/* 193 */             extraInfo.append(": ");
/* 194 */             if (rid instanceof KeyTransRecipientId)
/*     */             {
/* 196 */               appendCertInfo(extraInfo, (KeyTransRecipientId)rid, certificate, materialCert);
/*     */             }
/*     */           } 
/*     */         } 
/* 200 */         recipientFieldsBytes[i] = recipientBytes;
/* 201 */         recipientFieldsLength += recipientBytes.length;
/*     */       } 
/* 203 */       if (!foundRecipient || envelopedData == null)
/*     */       {
/* 205 */         throw new IOException("The certificate matches none of " + array.size() + " recipient entries" + extraInfo
/* 206 */             .toString());
/*     */       }
/* 208 */       if (envelopedData.length != 24)
/*     */       {
/* 210 */         throw new IOException("The enveloped data does not contain 24 bytes");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 216 */       byte[] accessBytes = new byte[4];
/* 217 */       System.arraycopy(envelopedData, 20, accessBytes, 0, 4);
/*     */       
/* 219 */       AccessPermission currentAccessPermission = new AccessPermission(accessBytes);
/* 220 */       currentAccessPermission.setReadOnly();
/* 221 */       setCurrentAccessPermission(currentAccessPermission);
/*     */ 
/*     */       
/* 224 */       byte[] sha1Input = new byte[recipientFieldsLength + 20];
/*     */ 
/*     */       
/* 227 */       System.arraycopy(envelopedData, 0, sha1Input, 0, 20);
/*     */ 
/*     */       
/* 230 */       int sha1InputOffset = 20;
/* 231 */       for (byte[] recipientFieldsByte : recipientFieldsBytes) {
/*     */         
/* 233 */         System.arraycopy(recipientFieldsByte, 0, sha1Input, sha1InputOffset, recipientFieldsByte.length);
/*     */         
/* 235 */         sha1InputOffset += recipientFieldsByte.length;
/*     */       } 
/*     */ 
/*     */       
/* 239 */       if (encryption.getVersion() == 4 || encryption.getVersion() == 5) {
/*     */         
/* 241 */         mdResult = MessageDigests.getSHA256().digest(sha1Input);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 246 */         PDCryptFilterDictionary defaultCryptFilterDictionary = encryption.getDefaultCryptFilterDictionary();
/* 247 */         if (defaultCryptFilterDictionary != null)
/*     */         {
/* 249 */           COSName cryptFilterMethod = defaultCryptFilterDictionary.getCryptFilterMethod();
/* 250 */           setAES((COSName.AESV2.equals(cryptFilterMethod) || COSName.AESV3
/* 251 */               .equals(cryptFilterMethod)));
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 256 */         mdResult = MessageDigests.getSHA1().digest(sha1Input);
/*     */       } 
/*     */ 
/*     */       
/* 260 */       this.encryptionKey = new byte[this.keyLength / 8];
/* 261 */       System.arraycopy(mdResult, 0, this.encryptionKey, 0, this.keyLength / 8);
/*     */     }
/* 263 */     catch (CMSException e) {
/*     */       
/* 265 */       throw new IOException(e);
/*     */     }
/* 267 */     catch (KeyStoreException e) {
/*     */       
/* 269 */       throw new IOException(e);
/*     */     }
/* 271 */     catch (CertificateEncodingException e) {
/*     */       
/* 273 */       throw new IOException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void appendCertInfo(StringBuilder extraInfo, KeyTransRecipientId ktRid, X509Certificate certificate, X509CertificateHolder materialCert) {
/* 280 */     BigInteger ridSerialNumber = ktRid.getSerialNumber();
/* 281 */     if (ridSerialNumber != null) {
/*     */       
/* 283 */       String certSerial = "unknown";
/* 284 */       BigInteger certSerialNumber = certificate.getSerialNumber();
/* 285 */       if (certSerialNumber != null)
/*     */       {
/* 287 */         certSerial = certSerialNumber.toString(16);
/*     */       }
/* 289 */       extraInfo.append("serial-#: rid ");
/* 290 */       extraInfo.append(ridSerialNumber.toString(16));
/* 291 */       extraInfo.append(" vs. cert ");
/* 292 */       extraInfo.append(certSerial);
/* 293 */       extraInfo.append(" issuer: rid '");
/* 294 */       extraInfo.append(ktRid.getIssuer());
/* 295 */       extraInfo.append("' vs. cert '");
/* 296 */       extraInfo.append((materialCert == null) ? "null" : materialCert.getIssuer());
/* 297 */       extraInfo.append("' ");
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
/*     */   public void prepareDocumentForEncryption(PDDocument doc) throws IOException {
/*     */     try {
/*     */       KeyGenerator key;
/*     */       byte[] mdResult;
/* 313 */       PDEncryption dictionary = doc.getEncryption();
/* 314 */       if (dictionary == null)
/*     */       {
/* 316 */         dictionary = new PDEncryption();
/*     */       }
/*     */       
/* 319 */       dictionary.setFilter("Adobe.PubSec");
/* 320 */       dictionary.setLength(this.keyLength);
/* 321 */       int version = computeVersionNumber();
/* 322 */       dictionary.setVersion(version);
/*     */ 
/*     */       
/* 325 */       dictionary.removeV45filters();
/*     */ 
/*     */       
/* 328 */       byte[] seed = new byte[20];
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 333 */         key = KeyGenerator.getInstance("AES");
/*     */       }
/* 335 */       catch (NoSuchAlgorithmException e) {
/*     */ 
/*     */         
/* 338 */         throw new RuntimeException(e);
/*     */       } 
/*     */       
/* 341 */       key.init(192, new SecureRandom());
/* 342 */       SecretKey sk = key.generateKey();
/*     */ 
/*     */       
/* 345 */       System.arraycopy(sk.getEncoded(), 0, seed, 0, 20);
/*     */       
/* 347 */       byte[][] recipientsFields = computeRecipientsField(seed);
/*     */       
/* 349 */       int shaInputLength = seed.length;
/*     */       
/* 351 */       for (byte[] field : recipientsFields)
/*     */       {
/* 353 */         shaInputLength += field.length;
/*     */       }
/*     */       
/* 356 */       byte[] shaInput = new byte[shaInputLength];
/*     */       
/* 358 */       System.arraycopy(seed, 0, shaInput, 0, 20);
/*     */       
/* 360 */       int shaInputOffset = 20;
/*     */       
/* 362 */       for (byte[] recipientsField : recipientsFields) {
/*     */         
/* 364 */         System.arraycopy(recipientsField, 0, shaInput, shaInputOffset, recipientsField.length);
/* 365 */         shaInputOffset += recipientsField.length;
/*     */       } 
/*     */ 
/*     */       
/* 369 */       if (version == 4 || version == 5) {
/*     */         
/* 371 */         dictionary.setSubFilter("adbe.pkcs7.s5");
/* 372 */         mdResult = MessageDigests.getSHA256().digest(shaInput);
/* 373 */         COSName aesVName = (version == 5) ? COSName.AESV3 : COSName.AESV2;
/* 374 */         prepareEncryptionDictAES(dictionary, aesVName, recipientsFields);
/*     */       }
/*     */       else {
/*     */         
/* 378 */         dictionary.setSubFilter("adbe.pkcs7.s4");
/* 379 */         mdResult = MessageDigests.getSHA1().digest(shaInput);
/* 380 */         dictionary.setRecipients(recipientsFields);
/*     */       } 
/*     */       
/* 383 */       this.encryptionKey = new byte[this.keyLength / 8];
/* 384 */       System.arraycopy(mdResult, 0, this.encryptionKey, 0, this.keyLength / 8);
/*     */       
/* 386 */       doc.setEncryptionDictionary(dictionary);
/* 387 */       doc.getDocument().setEncryptionDictionary(dictionary.getCOSObject());
/*     */     }
/* 389 */     catch (GeneralSecurityException e) {
/*     */       
/* 391 */       throw new IOException(e);
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
/*     */   private int computeVersionNumber() {
/* 405 */     switch (this.keyLength) {
/*     */       
/*     */       case 40:
/* 408 */         return 1;
/*     */       case 128:
/* 410 */         return 2;
/*     */       
/*     */       case 256:
/* 413 */         return 5;
/*     */     } 
/* 415 */     throw new IllegalArgumentException("key length must be 40, 128 or 256");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void prepareEncryptionDictAES(PDEncryption encryptionDictionary, COSName aesVName, byte[][] recipients) {
/* 421 */     PDCryptFilterDictionary cryptFilterDictionary = new PDCryptFilterDictionary();
/* 422 */     cryptFilterDictionary.setCryptFilterMethod(aesVName);
/* 423 */     cryptFilterDictionary.setLength(this.keyLength);
/* 424 */     COSArray array = new COSArray();
/* 425 */     for (byte[] recipient : recipients)
/*     */     {
/* 427 */       array.add((COSBase)new COSString(recipient));
/*     */     }
/* 429 */     cryptFilterDictionary.getCOSObject().setItem(COSName.RECIPIENTS, (COSBase)array);
/* 430 */     array.setDirect(true);
/* 431 */     encryptionDictionary.setDefaultCryptFilterDictionary(cryptFilterDictionary);
/* 432 */     encryptionDictionary.setStreamFilterName(COSName.DEFAULT_CRYPT_FILTER);
/* 433 */     encryptionDictionary.setStringFilterName(COSName.DEFAULT_CRYPT_FILTER);
/* 434 */     cryptFilterDictionary.getCOSObject().setDirect(true);
/* 435 */     setAES(true);
/*     */   }
/*     */ 
/*     */   
/*     */   private byte[][] computeRecipientsField(byte[] seed) throws GeneralSecurityException, IOException {
/* 440 */     byte[][] recipientsField = new byte[this.policy.getNumberOfRecipients()][];
/* 441 */     Iterator<PublicKeyRecipient> it = this.policy.getRecipientsIterator();
/* 442 */     int i = 0;
/*     */     
/* 444 */     while (it.hasNext()) {
/*     */       
/* 446 */       PublicKeyRecipient recipient = it.next();
/* 447 */       X509Certificate certificate = recipient.getX509();
/* 448 */       int permission = recipient.getPermission().getPermissionBytesForPublicKey();
/*     */       
/* 450 */       byte[] pkcs7input = new byte[24];
/* 451 */       byte one = (byte)permission;
/* 452 */       byte two = (byte)(permission >>> 8);
/* 453 */       byte three = (byte)(permission >>> 16);
/* 454 */       byte four = (byte)(permission >>> 24);
/*     */       
/* 456 */       System.arraycopy(seed, 0, pkcs7input, 0, 20);
/*     */       
/* 458 */       pkcs7input[20] = four;
/* 459 */       pkcs7input[21] = three;
/* 460 */       pkcs7input[22] = two;
/* 461 */       pkcs7input[23] = one;
/*     */       
/* 463 */       ASN1Primitive obj = createDERForRecipient(pkcs7input, certificate);
/*     */       
/* 465 */       ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*     */       
/* 467 */       DEROutputStream k = new DEROutputStream(baos);
/*     */       
/* 469 */       k.writeObject((ASN1Encodable)obj);
/*     */       
/* 471 */       recipientsField[i] = baos.toByteArray();
/*     */       
/* 473 */       i++;
/*     */     } 
/* 475 */     return recipientsField;
/*     */   }
/*     */   private ASN1Primitive createDERForRecipient(byte[] in, X509Certificate cert) throws IOException, GeneralSecurityException {
/*     */     AlgorithmParameterGenerator apg;
/*     */     KeyGenerator keygen;
/*     */     Cipher cipher;
/* 481 */     String algorithm = PKCSObjectIdentifiers.RC2_CBC.getId();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 487 */       apg = AlgorithmParameterGenerator.getInstance(algorithm, SecurityProvider.getProvider());
/* 488 */       keygen = KeyGenerator.getInstance(algorithm, SecurityProvider.getProvider());
/* 489 */       cipher = Cipher.getInstance(algorithm, SecurityProvider.getProvider());
/*     */     }
/* 491 */     catch (NoSuchAlgorithmException e) {
/*     */ 
/*     */       
/* 494 */       throw new IOException("Could not find a suitable javax.crypto provider for algorithm " + algorithm + "; possible reason: using an unsigned .jar file", e);
/*     */     
/*     */     }
/* 497 */     catch (NoSuchPaddingException e) {
/*     */ 
/*     */       
/* 500 */       throw new RuntimeException("Could not find a suitable javax.crypto provider", e);
/*     */     } 
/*     */     
/* 503 */     AlgorithmParameters parameters = apg.generateParameters();
/*     */     
/* 505 */     ASN1InputStream input = new ASN1InputStream(parameters.getEncoded("ASN.1"));
/* 506 */     ASN1Primitive object = input.readObject();
/* 507 */     input.close();
/*     */     
/* 509 */     keygen.init(128);
/* 510 */     SecretKey secretkey = keygen.generateKey();
/*     */     
/* 512 */     cipher.init(1, secretkey, parameters);
/* 513 */     byte[] bytes = cipher.doFinal(in);
/*     */     
/* 515 */     KeyTransRecipientInfo recipientInfo = computeRecipientInfo(cert, secretkey.getEncoded());
/* 516 */     DERSet set = new DERSet((ASN1Encodable)new RecipientInfo(recipientInfo));
/*     */     
/* 518 */     AlgorithmIdentifier algorithmId = new AlgorithmIdentifier(new ASN1ObjectIdentifier(algorithm), (ASN1Encodable)object);
/* 519 */     EncryptedContentInfo encryptedInfo = new EncryptedContentInfo(PKCSObjectIdentifiers.data, algorithmId, (ASN1OctetString)new DEROctetString(bytes));
/*     */     
/* 521 */     EnvelopedData enveloped = new EnvelopedData(null, (ASN1Set)set, encryptedInfo, (ASN1Set)null);
/*     */     
/* 523 */     ContentInfo contentInfo = new ContentInfo(PKCSObjectIdentifiers.envelopedData, (ASN1Encodable)enveloped);
/* 524 */     return contentInfo.toASN1Primitive();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private KeyTransRecipientInfo computeRecipientInfo(X509Certificate x509certificate, byte[] abyte0) throws IOException, CertificateEncodingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
/*     */     Cipher cipher;
/* 531 */     ASN1InputStream input = new ASN1InputStream(x509certificate.getTBSCertificate());
/* 532 */     TBSCertificate certificate = TBSCertificate.getInstance(input.readObject());
/* 533 */     input.close();
/*     */     
/* 535 */     AlgorithmIdentifier algorithmId = certificate.getSubjectPublicKeyInfo().getAlgorithm();
/*     */ 
/*     */ 
/*     */     
/* 539 */     IssuerAndSerialNumber serial = new IssuerAndSerialNumber(certificate.getIssuer(), certificate.getSerialNumber().getValue());
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 544 */       cipher = Cipher.getInstance(algorithmId.getAlgorithm().getId(), 
/* 545 */           SecurityProvider.getProvider());
/*     */     }
/* 547 */     catch (NoSuchAlgorithmException e) {
/*     */ 
/*     */       
/* 550 */       throw new RuntimeException("Could not find a suitable javax.crypto provider", e);
/*     */     }
/* 552 */     catch (NoSuchPaddingException e) {
/*     */ 
/*     */       
/* 555 */       throw new RuntimeException("Could not find a suitable javax.crypto provider", e);
/*     */     } 
/*     */     
/* 558 */     cipher.init(1, x509certificate.getPublicKey());
/*     */     
/* 560 */     DEROctetString octets = new DEROctetString(cipher.doFinal(abyte0));
/* 561 */     RecipientIdentifier recipientId = new RecipientIdentifier(serial);
/* 562 */     return new KeyTransRecipientInfo(recipientId, algorithmId, (ASN1OctetString)octets);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasProtectionPolicy() {
/* 571 */     return (this.policy != null);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/encryption/PublicKeySecurityHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
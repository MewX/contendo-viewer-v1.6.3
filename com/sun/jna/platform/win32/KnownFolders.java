/*     */ package com.sun.jna.platform.win32;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KnownFolders
/*     */ {
/*  41 */   public static final Guid.GUID FOLDERID_NetworkFolder = Guid.GUID.fromString("{D20BEEC4-5CA8-4905-AE3B-BF251EA09B53}");
/*     */ 
/*     */   
/*  44 */   public static final Guid.GUID FOLDERID_ComputerFolder = Guid.GUID.fromString("{0AC0837C-BBF8-452A-850D-79D08E667CA7}");
/*     */ 
/*     */   
/*  47 */   public static final Guid.GUID FOLDERID_InternetFolder = Guid.GUID.fromString("{4D9F7874-4E0C-4904-967B-40B0D20C3E4B}");
/*     */ 
/*     */   
/*  50 */   public static final Guid.GUID FOLDERID_ControlPanelFolder = Guid.GUID.fromString("{82A74AEB-AEB4-465C-A014-D097EE346D63}");
/*     */ 
/*     */   
/*  53 */   public static final Guid.GUID FOLDERID_PrintersFolder = Guid.GUID.fromString("{76FC4E2D-D6AD-4519-A663-37BD56068185}");
/*     */ 
/*     */   
/*  56 */   public static final Guid.GUID FOLDERID_SyncManagerFolder = Guid.GUID.fromString("{43668BF8-C14E-49B2-97C9-747784D784B7}");
/*     */ 
/*     */   
/*  59 */   public static final Guid.GUID FOLDERID_SyncSetupFolder = Guid.GUID.fromString("{0f214138-b1d3-4a90-bba9-27cbc0c5389a}");
/*     */ 
/*     */   
/*  62 */   public static final Guid.GUID FOLDERID_ConflictFolder = Guid.GUID.fromString("{4bfefb45-347d-4006-a5be-ac0cb0567192}");
/*     */ 
/*     */   
/*  65 */   public static final Guid.GUID FOLDERID_SyncResultsFolder = Guid.GUID.fromString("{289a9a43-be44-4057-a41b-587a76d7e7f9}");
/*     */ 
/*     */   
/*  68 */   public static final Guid.GUID FOLDERID_RecycleBinFolder = Guid.GUID.fromString("{B7534046-3ECB-4C18-BE4E-64CD4CB7D6AC}");
/*     */ 
/*     */   
/*  71 */   public static final Guid.GUID FOLDERID_ConnectionsFolder = Guid.GUID.fromString("{6F0CD92B-2E97-45D1-88FF-B0D186B8DEDD}");
/*     */ 
/*     */   
/*  74 */   public static final Guid.GUID FOLDERID_Fonts = Guid.GUID.fromString("{FD228CB7-AE11-4AE3-864C-16F3910AB8FE}");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   public static final Guid.GUID FOLDERID_Desktop = Guid.GUID.fromString("{B4BFCC3A-DB2C-424C-B029-7FE99A87C641}");
/*     */ 
/*     */   
/*  88 */   public static final Guid.GUID FOLDERID_Startup = Guid.GUID.fromString("{B97D20BB-F46A-4C97-BA10-5E3608430854}");
/*     */ 
/*     */   
/*  91 */   public static final Guid.GUID FOLDERID_Programs = Guid.GUID.fromString("{A77F5D77-2E2B-44C3-A6A2-ABA601054A51}");
/*     */ 
/*     */   
/*  94 */   public static final Guid.GUID FOLDERID_StartMenu = Guid.GUID.fromString("{625B53C3-AB48-4EC1-BA1F-A1EF4146FC19}");
/*     */ 
/*     */   
/*  97 */   public static final Guid.GUID FOLDERID_Recent = Guid.GUID.fromString("{AE50C081-EBD2-438A-8655-8A092E34987A}");
/*     */ 
/*     */   
/* 100 */   public static final Guid.GUID FOLDERID_SendTo = Guid.GUID.fromString("{8983036C-27C0-404B-8F08-102D10DCFD74}");
/*     */ 
/*     */   
/* 103 */   public static final Guid.GUID FOLDERID_Documents = Guid.GUID.fromString("{FDD39AD0-238F-46AF-ADB4-6C85480369C7}");
/*     */ 
/*     */   
/* 106 */   public static final Guid.GUID FOLDERID_Favorites = Guid.GUID.fromString("{1777F761-68AD-4D8A-87BD-30B759FA33DD}");
/*     */ 
/*     */   
/* 109 */   public static final Guid.GUID FOLDERID_NetHood = Guid.GUID.fromString("{C5ABBF53-E17F-4121-8900-86626FC2C973}");
/*     */ 
/*     */   
/* 112 */   public static final Guid.GUID FOLDERID_PrintHood = Guid.GUID.fromString("{9274BD8D-CFD1-41C3-B35E-B13F55A758F4}");
/*     */ 
/*     */   
/* 115 */   public static final Guid.GUID FOLDERID_Templates = Guid.GUID.fromString("{A63293E8-664E-48DB-A079-DF759E0509F7}");
/*     */ 
/*     */   
/* 118 */   public static final Guid.GUID FOLDERID_CommonStartup = Guid.GUID.fromString("{82A5EA35-D9CD-47C5-9629-E15D2F714E6E}");
/*     */ 
/*     */   
/* 121 */   public static final Guid.GUID FOLDERID_CommonPrograms = Guid.GUID.fromString("{0139D44E-6AFE-49F2-8690-3DAFCAE6FFB8}");
/*     */ 
/*     */   
/* 124 */   public static final Guid.GUID FOLDERID_CommonStartMenu = Guid.GUID.fromString("{A4115719-D62E-491D-AA7C-E74B8BE3B067}");
/*     */ 
/*     */   
/* 127 */   public static final Guid.GUID FOLDERID_PublicDesktop = Guid.GUID.fromString("{C4AA340D-F20F-4863-AFEF-F87EF2E6BA25}");
/*     */ 
/*     */   
/* 130 */   public static final Guid.GUID FOLDERID_ProgramData = Guid.GUID.fromString("{62AB5D82-FDC1-4DC3-A9DD-070D1D495D97}");
/*     */ 
/*     */   
/* 133 */   public static final Guid.GUID FOLDERID_CommonTemplates = Guid.GUID.fromString("{B94237E7-57AC-4347-9151-B08C6C32D1F7}");
/*     */ 
/*     */   
/* 136 */   public static final Guid.GUID FOLDERID_PublicDocuments = Guid.GUID.fromString("{ED4824AF-DCE4-45A8-81E2-FC7965083634}");
/*     */ 
/*     */   
/* 139 */   public static final Guid.GUID FOLDERID_RoamingAppData = Guid.GUID.fromString("{3EB685DB-65F9-4CF6-A03A-E3EF65729F3D}");
/*     */ 
/*     */   
/* 142 */   public static final Guid.GUID FOLDERID_LocalAppData = Guid.GUID.fromString("{F1B32785-6FBA-4FCF-9D55-7B8E7F157091}");
/*     */ 
/*     */   
/* 145 */   public static final Guid.GUID FOLDERID_LocalAppDataLow = Guid.GUID.fromString("{A520A1A4-1780-4FF6-BD18-167343C5AF16}");
/*     */ 
/*     */   
/* 148 */   public static final Guid.GUID FOLDERID_InternetCache = Guid.GUID.fromString("{352481E8-33BE-4251-BA85-6007CAEDCF9D}");
/*     */ 
/*     */   
/* 151 */   public static final Guid.GUID FOLDERID_Cookies = Guid.GUID.fromString("{2B0F765D-C0E9-4171-908E-08A611B84FF6}");
/*     */ 
/*     */   
/* 154 */   public static final Guid.GUID FOLDERID_History = Guid.GUID.fromString("{D9DC8A3B-B784-432E-A781-5A1130A75963}");
/*     */ 
/*     */   
/* 157 */   public static final Guid.GUID FOLDERID_System = Guid.GUID.fromString("{1AC14E77-02E7-4E5D-B744-2EB1AE5198B7}");
/*     */ 
/*     */   
/* 160 */   public static final Guid.GUID FOLDERID_SystemX86 = Guid.GUID.fromString("{D65231B0-B2F1-4857-A4CE-A8E7C6EA7D27}");
/*     */ 
/*     */   
/* 163 */   public static final Guid.GUID FOLDERID_Windows = Guid.GUID.fromString("{F38BF404-1D43-42F2-9305-67DE0B28FC23}");
/*     */ 
/*     */   
/* 166 */   public static final Guid.GUID FOLDERID_Profile = Guid.GUID.fromString("{5E6C858F-0E22-4760-9AFE-EA3317B67173}");
/*     */ 
/*     */   
/* 169 */   public static final Guid.GUID FOLDERID_Pictures = Guid.GUID.fromString("{33E28130-4E1E-4676-835A-98395C3BC3BB}");
/*     */ 
/*     */   
/* 172 */   public static final Guid.GUID FOLDERID_ProgramFilesX86 = Guid.GUID.fromString("{7C5A40EF-A0FB-4BFC-874A-C0F2E0B9FA8E}");
/*     */ 
/*     */   
/* 175 */   public static final Guid.GUID FOLDERID_ProgramFilesCommonX86 = Guid.GUID.fromString("{DE974D24-D9C6-4D3E-BF91-F4455120B917}");
/*     */ 
/*     */   
/* 178 */   public static final Guid.GUID FOLDERID_ProgramFilesX64 = Guid.GUID.fromString("{6d809377-6af0-444b-8957-a3773f02200e}");
/*     */ 
/*     */   
/* 181 */   public static final Guid.GUID FOLDERID_ProgramFilesCommonX64 = Guid.GUID.fromString("{6365d5a7-0f0d-45e5-87f6-0da56b6a4f7d}");
/*     */ 
/*     */   
/* 184 */   public static final Guid.GUID FOLDERID_ProgramFiles = Guid.GUID.fromString("{905e63b6-c1bf-494e-b29c-65b732d3d21a}");
/*     */ 
/*     */   
/* 187 */   public static final Guid.GUID FOLDERID_ProgramFilesCommon = Guid.GUID.fromString("{F7F1ED05-9F6D-47A2-AAAE-29D317C6F066}");
/*     */ 
/*     */   
/* 190 */   public static final Guid.GUID FOLDERID_UserProgramFiles = Guid.GUID.fromString("{5cd7aee2-2219-4a67-b85d-6c9ce15660cb}");
/*     */ 
/*     */   
/* 193 */   public static final Guid.GUID FOLDERID_UserProgramFilesCommon = Guid.GUID.fromString("{bcbd3057-ca5c-4622-b42d-bc56db0ae516}");
/*     */ 
/*     */   
/* 196 */   public static final Guid.GUID FOLDERID_AdminTools = Guid.GUID.fromString("{724EF170-A42D-4FEF-9F26-B60E846FBA4F}");
/*     */ 
/*     */   
/* 199 */   public static final Guid.GUID FOLDERID_CommonAdminTools = Guid.GUID.fromString("{D0384E7D-BAC3-4797-8F14-CBA229B392B5}");
/*     */ 
/*     */   
/* 202 */   public static final Guid.GUID FOLDERID_Music = Guid.GUID.fromString("{4BD8D571-6D19-48D3-BE97-422220080E43}");
/*     */ 
/*     */   
/* 205 */   public static final Guid.GUID FOLDERID_Videos = Guid.GUID.fromString("{18989B1D-99B5-455B-841C-AB7C74E4DDFC}");
/*     */ 
/*     */   
/* 208 */   public static final Guid.GUID FOLDERID_Ringtones = Guid.GUID.fromString("{C870044B-F49E-4126-A9C3-B52A1FF411E8}");
/*     */ 
/*     */   
/* 211 */   public static final Guid.GUID FOLDERID_PublicPictures = Guid.GUID.fromString("{B6EBFB86-6907-413C-9AF7-4FC2ABF07CC5}");
/*     */ 
/*     */   
/* 214 */   public static final Guid.GUID FOLDERID_PublicMusic = Guid.GUID.fromString("{3214FAB5-9757-4298-BB61-92A9DEAA44FF}");
/*     */ 
/*     */   
/* 217 */   public static final Guid.GUID FOLDERID_PublicVideos = Guid.GUID.fromString("{2400183A-6185-49FB-A2D8-4A392A602BA3}");
/*     */ 
/*     */   
/* 220 */   public static final Guid.GUID FOLDERID_PublicRingtones = Guid.GUID.fromString("{E555AB60-153B-4D17-9F04-A5FE99FC15EC}");
/*     */ 
/*     */   
/* 223 */   public static final Guid.GUID FOLDERID_ResourceDir = Guid.GUID.fromString("{8AD10C31-2ADB-4296-A8F7-E4701232C972}");
/*     */ 
/*     */   
/* 226 */   public static final Guid.GUID FOLDERID_LocalizedResourcesDir = Guid.GUID.fromString("{2A00375E-224C-49DE-B8D1-440DF7EF3DDC}");
/*     */ 
/*     */   
/* 229 */   public static final Guid.GUID FOLDERID_CommonOEMLinks = Guid.GUID.fromString("{C1BAE2D0-10DF-4334-BEDD-7AA20B227A9D}");
/*     */ 
/*     */   
/* 232 */   public static final Guid.GUID FOLDERID_CDBurning = Guid.GUID.fromString("{9E52AB10-F80D-49DF-ACB8-4330F5687855}");
/*     */ 
/*     */   
/* 235 */   public static final Guid.GUID FOLDERID_UserProfiles = Guid.GUID.fromString("{0762D272-C50A-4BB0-A382-697DCD729B80}");
/*     */ 
/*     */   
/* 238 */   public static final Guid.GUID FOLDERID_Playlists = Guid.GUID.fromString("{DE92C1C7-837F-4F69-A3BB-86E631204A23}");
/*     */ 
/*     */   
/* 241 */   public static final Guid.GUID FOLDERID_SamplePlaylists = Guid.GUID.fromString("{15CA69B3-30EE-49C1-ACE1-6B5EC372AFB5}");
/*     */ 
/*     */   
/* 244 */   public static final Guid.GUID FOLDERID_SampleMusic = Guid.GUID.fromString("{B250C668-F57D-4EE1-A63C-290EE7D1AA1F}");
/*     */ 
/*     */   
/* 247 */   public static final Guid.GUID FOLDERID_SamplePictures = Guid.GUID.fromString("{C4900540-2379-4C75-844B-64E6FAF8716B}");
/*     */ 
/*     */   
/* 250 */   public static final Guid.GUID FOLDERID_SampleVideos = Guid.GUID.fromString("{859EAD94-2E85-48AD-A71A-0969CB56A6CD}");
/*     */ 
/*     */   
/* 253 */   public static final Guid.GUID FOLDERID_PhotoAlbums = Guid.GUID.fromString("{69D2CF90-FC33-4FB7-9A0C-EBB0F0FCB43C}");
/*     */ 
/*     */   
/* 256 */   public static final Guid.GUID FOLDERID_Public = Guid.GUID.fromString("{DFDF76A2-C82A-4D63-906A-5644AC457385}");
/*     */ 
/*     */   
/* 259 */   public static final Guid.GUID FOLDERID_ChangeRemovePrograms = Guid.GUID.fromString("{df7266ac-9274-4867-8d55-3bd661de872d}");
/*     */ 
/*     */   
/* 262 */   public static final Guid.GUID FOLDERID_AppUpdates = Guid.GUID.fromString("{a305ce99-f527-492b-8b1a-7e76fa98d6e4}");
/*     */ 
/*     */   
/* 265 */   public static final Guid.GUID FOLDERID_AddNewPrograms = Guid.GUID.fromString("{de61d971-5ebc-4f02-a3a9-6c82895e5c04}");
/*     */ 
/*     */   
/* 268 */   public static final Guid.GUID FOLDERID_Downloads = Guid.GUID.fromString("{374de290-123f-4565-9164-39c4925e467b}");
/*     */ 
/*     */   
/* 271 */   public static final Guid.GUID FOLDERID_PublicDownloads = Guid.GUID.fromString("{3d644c9b-1fb8-4f30-9b45-f670235f79c0}");
/*     */ 
/*     */   
/* 274 */   public static final Guid.GUID FOLDERID_SavedSearches = Guid.GUID.fromString("{7d1d3a04-debb-4115-95cf-2f29da2920da}");
/*     */ 
/*     */   
/* 277 */   public static final Guid.GUID FOLDERID_QuickLaunch = Guid.GUID.fromString("{52a4f021-7b75-48a9-9f6b-4b87a210bc8f}");
/*     */ 
/*     */   
/* 280 */   public static final Guid.GUID FOLDERID_Contacts = Guid.GUID.fromString("{56784854-c6cb-462b-8169-88e350acb882}");
/*     */ 
/*     */   
/* 283 */   public static final Guid.GUID FOLDERID_SidebarParts = Guid.GUID.fromString("{a75d362e-50fc-4fb7-ac2c-a8beaa314493}");
/*     */ 
/*     */   
/* 286 */   public static final Guid.GUID FOLDERID_SidebarDefaultParts = Guid.GUID.fromString("{7b396e54-9ec5-4300-be0a-2482ebae1a26}");
/*     */ 
/*     */   
/* 289 */   public static final Guid.GUID FOLDERID_PublicGameTasks = Guid.GUID.fromString("{debf2536-e1a8-4c59-b6a2-414586476aea}");
/*     */ 
/*     */   
/* 292 */   public static final Guid.GUID FOLDERID_GameTasks = Guid.GUID.fromString("{054fae61-4dd8-4787-80b6-090220c4b700}");
/*     */ 
/*     */   
/* 295 */   public static final Guid.GUID FOLDERID_SavedGames = Guid.GUID.fromString("{4c5c32ff-bb9d-43b0-b5b4-2d72e54eaaa4}");
/*     */ 
/*     */   
/* 298 */   public static final Guid.GUID FOLDERID_Games = Guid.GUID.fromString("{cac52c1a-b53d-4edc-92d7-6b2e8ac19434}");
/*     */ 
/*     */   
/* 301 */   public static final Guid.GUID FOLDERID_SEARCH_MAPI = Guid.GUID.fromString("{98ec0e18-2098-4d44-8644-66979315a281}");
/*     */ 
/*     */   
/* 304 */   public static final Guid.GUID FOLDERID_SEARCH_CSC = Guid.GUID.fromString("{ee32e446-31ca-4aba-814f-a5ebd2fd6d5e}");
/*     */ 
/*     */   
/* 307 */   public static final Guid.GUID FOLDERID_Links = Guid.GUID.fromString("{bfb9d5e0-c6a9-404c-b2b2-ae6db6af4968}");
/*     */ 
/*     */   
/* 310 */   public static final Guid.GUID FOLDERID_UsersFiles = Guid.GUID.fromString("{f3ce0f7c-4901-4acc-8648-d5d44b04ef8f}");
/*     */ 
/*     */   
/* 313 */   public static final Guid.GUID FOLDERID_UsersLibraries = Guid.GUID.fromString("{a302545d-deff-464b-abe8-61c8648d939b}");
/*     */ 
/*     */   
/* 316 */   public static final Guid.GUID FOLDERID_SearchHome = Guid.GUID.fromString("{190337d1-b8ca-4121-a639-6d472d16972a}");
/*     */ 
/*     */   
/* 319 */   public static final Guid.GUID FOLDERID_OriginalImages = Guid.GUID.fromString("{2C36C0AA-5812-4b87-bfd0-4cd0dfb19b39}");
/*     */ 
/*     */   
/* 322 */   public static final Guid.GUID FOLDERID_DocumentsLibrary = Guid.GUID.fromString("{7b0db17d-9cd2-4a93-9733-46cc89022e7c}");
/*     */ 
/*     */   
/* 325 */   public static final Guid.GUID FOLDERID_MusicLibrary = Guid.GUID.fromString("{2112ab0a-c86a-4ffe-a368-0de96e47012e}");
/*     */ 
/*     */   
/* 328 */   public static final Guid.GUID FOLDERID_PicturesLibrary = Guid.GUID.fromString("{a990ae9f-a03b-4e80-94bc-9912d7504104}");
/*     */ 
/*     */   
/* 331 */   public static final Guid.GUID FOLDERID_VideosLibrary = Guid.GUID.fromString("{491e922f-5643-4af4-a7eb-4e7a138d8174}");
/*     */ 
/*     */   
/* 334 */   public static final Guid.GUID FOLDERID_RecordedTVLibrary = Guid.GUID.fromString("{1a6fdba2-f42d-4358-a798-b74d745926c5}");
/*     */ 
/*     */   
/* 337 */   public static final Guid.GUID FOLDERID_HomeGroup = Guid.GUID.fromString("{52528a6b-b9e3-4add-b60d-588c2dba842d}");
/*     */ 
/*     */   
/* 340 */   public static final Guid.GUID FOLDERID_DeviceMetadataStore = Guid.GUID.fromString("{5ce4a5e9-e4eb-479d-b89f-130c02886155}");
/*     */ 
/*     */   
/* 343 */   public static final Guid.GUID FOLDERID_Libraries = Guid.GUID.fromString("{1b3ea5dc-b587-4786-b4ef-bd1dc332aeae}");
/*     */ 
/*     */   
/* 346 */   public static final Guid.GUID FOLDERID_PublicLibraries = Guid.GUID.fromString("{48daf80b-e6cf-4f4e-b800-0e69d84ee384}");
/*     */ 
/*     */   
/* 349 */   public static final Guid.GUID FOLDERID_UserPinned = Guid.GUID.fromString("{9e3995ab-1f9c-4f13-b827-48b24b6c7174}");
/*     */ 
/*     */   
/* 352 */   public static final Guid.GUID FOLDERID_ImplicitAppShortcuts = Guid.GUID.fromString("{bcb5256f-79f6-4cee-b725-dc34e402fd46}");
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/sun/jna/platform/win32/KnownFolders.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
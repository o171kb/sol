[PLATFORM]
TARGET=[WINPC]
VENDER=[NT]

[MODIFY]
MODIFY=[Y]

[PASSWD]		
SAVER_PASSWD=[Y]	
10MIN=[Y]		

[SOFTWARE]		
SECURITY=[pm_main.exe@incops3.exe]
SPYWARE=[AszMon.exe@AszTray.exe]
SPYWARE_KEYWORD=[Spyzero]

[VIRUS]			
INSTALL=[Y]
UPDATE=[Y]
AUTO_UPDATE=[Y]
LOG=[Y]			

[SHARE_FOLDER]		
SHARED_DOCS=[Y]
FOLDER_SHARE=[Y]
RPC_SHARE=[Y]

[SECURITY]
NTFS=[Y]		
ID_PASSWD=[Y]		
GUEST=[Y]		
ADMIN=[Y]		
NO_SERVICE=[DHCP@MSFtpsvc@IISADMIN@MSSQLServer@Messenger@RasAuto@RasMan@RemoteRegistry@SMTPSvc@SNMP@SNMPTrap@Schedule@Tlntsvr@TermService@W3SVC]
EXECUTE_FILE=[cmd.exe@command.com]
READOLNY_FILE=[autoexec.bat]		

[OTHER]			
HKLM_RUN=[Y]
HKLM_RUNONCE=[Y]
HKLM_RUNONCEEX=[Y]
HKCU_RUN=[Y]
HKCU_RUNONCE=[Y]
HKCU_RUNONCEEX=[Y]		
	
[PATCH]
OS_PACK=[6]		
OS_PATCH=[7-828035@7-825119@7-824141@7-823182@7-824146@7-828028@7-830352@7-835732@7-828741@7-837001]	
IE_VERSION=[5.01@5.5@6.0]
IE_PACK=[4@2@1]
IE_PATCH=[5-832894:5-837009@3-832894:3-837009@2-832894:2-837009]
MSOTHER_VERSION=[2.5@2.6@2.7@2.8]
MSOTHER_PATCH=[823718:832483@823718:832483@823718:832483@832483]
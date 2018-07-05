#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.3.14.2
 Author:         myName

 Script Function:
	Template AutoIt script.

#ce ----------------------------------------------------------------------------

; Script Start - Add your code below here
WinWaitActive("Open")
Send(@ScriptDir & "\src\test\resources\Import Patient Sample file.xml")
Send("{ENTER}")
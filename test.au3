#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.3.14.2
 Author:         Jyoti Kalra

 Script Function:
	Template AutoIt script.

#ce ----------------------------------------------------------------------------

; Script Start - Add your code below here
; To pass user name and password

WinWaitActive("Open")
Send(@ScriptDir & "\sign.jpg")
Send("{ENTER}")
#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.3.14.2
 Author:         Jyoti Kalra

 Script Function:
	Template AutoIt script.

#ce ----------------------------------------------------------------------------

; Script Start - Add your code below here
; To pass user name and password

Local $hWnd = WinWait("[CLASS:Chrome_WidgetWin_1]","",10)
WinActivate("https://gpin.gmed.com/pin - Google Chrome","")
Send("gmed\amith")
Send("{TAB}")
Send("Neeloy@123456789012")
Send("{ENTER}")
WinClose("gPin","")
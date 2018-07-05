#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.3.14.2
 Author:         myName

 Script Function:
	Template AutoIt script.

#ce ----------------------------------------------------------------------------

; Just 10 passes for this example
 $i = 10
      ; Your code runs here to get the data

    ; Create the filename - note the use of StringFormat to force leading 0s so the files sort correctly
    $sFileName = "File" & Random( $i)
     $sFileName1 ="printData" & $sFileName

    WinWaitActive("Save Print Output As")
    Send(@ScriptDir & $sFileName1)
     Send("{ENTER}")

package com.dantsu.thermalprinter.async

import android.content.Context

class AsyncTcpEscPosPrint(context: Context?, onPrintFinished: OnPrintFinished?) : AsyncEscPosPrint(
    context!!, onPrintFinished,"TCP-Task"
) {
}
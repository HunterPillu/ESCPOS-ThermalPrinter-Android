package com.dantsu.thermalprinter.async

import android.content.Context

class AsyncUsbEscPosPrint(context: Context?, onPrintFinished: OnPrintFinished?) : AsyncEscPosPrint(
    context!!, onPrintFinished,"USB-Task"
) {
}
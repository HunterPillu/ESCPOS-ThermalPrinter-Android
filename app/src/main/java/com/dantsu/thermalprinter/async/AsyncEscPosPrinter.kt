package com.dantsu.thermalprinter.async

import com.dantsu.escposprinter.EscPosPrinterSize
import com.dantsu.escposprinter.connection.DeviceConnection

class AsyncEscPosPrinter(
    val printerConnection: DeviceConnection?,
    printerDpi: Int,
    printerWidthMM: Float,
    printerNbrCharactersPerLine: Int
) : EscPosPrinterSize(printerDpi, printerWidthMM, printerNbrCharactersPerLine) {
    private var textsToPrint = arrayOfNulls<String>(0)

    fun getTextsToPrint(): Array<String?> {
        return arrayOfNulls<String>(0)
    }

    fun setTextsToPrint(textsToPrint: Array<String?>): AsyncEscPosPrinter {
        this.textsToPrint = textsToPrint
        return this
    }

    fun addTextToPrint(textToPrint: String?): AsyncEscPosPrinter {
        val tmp = arrayOfNulls<String>(textsToPrint.size + 1)
        System.arraycopy(textsToPrint, 0, tmp, 0, textsToPrint.size)
        tmp[textsToPrint.size] = textToPrint
        textsToPrint = tmp
        return this
    }
}
package com.dantsu.thermalprinter.async

import com.dantsu.escposprinter.connection.bluetooth.BluetoothConnection
import com.dantsu.escposprinter.connection.bluetooth.BluetoothPrintersConnections
import com.dantsu.escposprinter.exceptions.EscPosConnectionException

enum class Status {
    PENDING,
    RUNNING,
    FINISHED
}

object Constant {
    const val FINISH_SUCCESS = 1
    const val FINISH_NO_PRINTER = 2
    const val FINISH_PRINTER_DISCONNECTED = 3
    const val FINISH_PARSER_ERROR = 4
    const val FINISH_ENCODING_ERROR = 5
    const val FINISH_BARCODE_ERROR = 6
    const val PROGRESS_CONNECTING = 1
    const val PROGRESS_CONNECTED = 2
    const val PROGRESS_PRINTING = 3
    const val PROGRESS_PRINTED = 4


    /**
     * Easy way to get the first bluetooth printer paired / connected.
     *
     * @return a EscPosPrinterCommands instance
     */
    fun selectFirstPaired(): BluetoothConnection? {
        val printers = BluetoothPrintersConnections()
        val bluetoothPrinters = printers.list
        if (bluetoothPrinters != null && bluetoothPrinters.size > 0) {
            for (printer in bluetoothPrinters) {
                try {
                    return printer!!.connect()
                } catch (e: EscPosConnectionException) {
                    e.printStackTrace()
                }
            }
        }
        return null
    }
}
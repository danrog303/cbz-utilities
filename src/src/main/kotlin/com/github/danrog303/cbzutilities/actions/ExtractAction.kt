package com.github.danrog303.cbzutilities.actions
import com.github.danrog303.cbzutilities.unpacking.CbzUnpacker

class ExtractAction(private val args: Map<String, Any>): Runnable {
    override fun run() {
        val unpacker = CbzUnpacker(args["input"].toString(), args["output"].toString())
        unpacker.unpack()
    }
}

package com.example.smarthome.communication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

object UdpClient {
    fun sendDataToServer(
        message: String,
        serverIp: String,
        serverPort: Int,
        responseListener: (String) -> Unit
    ) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val socket = DatagramSocket()
                val serverAddress = InetAddress.getByName(serverIp)

                val sendData = message.toByteArray()
                val sendPacket = DatagramPacket(sendData, sendData.size, serverAddress, serverPort)
                socket.send(sendPacket)

                val receiveData = ByteArray(1024)
                val receivePacket = DatagramPacket(receiveData, receiveData.size)
                socket.receive(receivePacket)

                val response = String(receivePacket.data, 0, receivePacket.length)

                withContext(Dispatchers.Main) {
                    responseListener(response)
                }

                socket.close()
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    // Handle and display the exception
                    responseListener(e.message ?: "Unknown error occurred")
                }
            }
        }
    }
}

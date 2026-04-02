import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.client.j2se.MatrixToImageWriter


fun saveQRCode(content: String, fileName: String) {
    val width = 300
    val height = 300

    val qrCodeWriter = QRCodeWriter()
    val bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height)

    val path: Path = FileSystems.getDefault().getPath(fileName)
    MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path)
}

fun generateQRCodeStream(content: String): ByteArrayOutputStream {
    val bitMatrix = QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, 300, 300)
    val outputStream = ByteArrayOutputStream()
    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream)
    return outputStream
}

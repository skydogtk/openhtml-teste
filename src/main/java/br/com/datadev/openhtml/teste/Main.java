package br.com.datadev.openhtml.teste;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/** 
 *
 * @author fabricio
 */
public class Main {

    public static void main(String[] args) {
        FileOutputStream os = null;
        try {
            String filePath = "arquivo.html";
            String html = readAllBytes(filePath);
            
            UUID uuid = UUID.randomUUID();
            String fileName = uuid.toString() + ".pdf";
            File file = new File(fileName);
            
            os = new FileOutputStream(file);
            
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(html, null);
            builder.useFastMode();
            builder.toStream(os);
            builder.run();
            
            os.flush();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                os.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private static String readAllBytes(String filePath) {
        String content = "";

        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}

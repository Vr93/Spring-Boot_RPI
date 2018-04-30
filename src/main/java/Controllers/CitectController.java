package Controllers;


import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

@Controller
public class CitectController {

    @RequestMapping(value="/citect",method = RequestMethod.GET)
    public String citect(){
        return "citect";
    }

    @RequestMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile1(
            @RequestParam(defaultValue = "Trend.dbf") String fileName) throws IOException {
       // MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
        System.out.println("fileName: " + fileName);
        File file = new ClassPathResource("Trend.dbf").getFile();
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
    }

    @RequestMapping(value = "/asd", method = RequestMethod.POST)
    public ResponseEntity<InputStreamResource> uploadingPost(@RequestParam("file") MultipartFile file) throws IOException {
        /*for(MultipartFile uploadedFile : uploadingFiles) {
            File file = new File(uploadingdir + uploadedFile.getOriginalFilename());
            uploadedFile.transferTo(file);
        }*/
        File inputFile = new File(file.getOriginalFilename());
        inputFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(inputFile);
        fos.write(file.getBytes());
        fos.close();








        //File citectFile = new ClassPathResource("Trend.dbf").getFile();
        InputStreamResource resource = new InputStreamResource(new FileInputStream(inputFile));
        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + inputFile.getName())
                // Content-Type
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                // Contet-Length
                .contentLength(inputFile.length()) //
                .body(resource);
    }
}

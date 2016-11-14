package uk.ac.ebi.phis.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.constraints.NotNull;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ilinca on 10/11/2016.
 */
@Controller
public class DataUploadController {


    @NotNull
    @Value("${uploadDir}")
    private String uploadPath;


    @RequestMapping(value="/upload", method= RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(@ModelAttribute("upload") @RequestParam("file") MultipartFile file,
                            RedirectAttributes redirectAttributes){

        String name = file.getOriginalFilename();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(uploadPath + "/" + name + "-uploaded")));
                stream.write(bytes);
                stream.close();
                System.out.println("You successfully uploaded " + name + " into " + uploadPath + "/" + name + "-uploaded !");
                return "You successfully uploaded " + name + " into " + name + "-uploaded !";
            } catch (Exception e) {
                System.out.println("You failed to upload " + name + " => " + e.getMessage());
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            System.out.println("You failed to upload " + name + " because the file was empty.");
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
    @RequestMapping(value="/upload", method= RequestMethod.GET)
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", "ABC");

        return "upload";
    }

}

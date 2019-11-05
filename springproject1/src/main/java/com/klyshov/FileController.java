package com.klyshov;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@SessionAttributes("uploadedFile")
public class FileController {

    @Autowired
    private FileValidator fileValidator; //автосвязывание с бином FileValidator

//    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView main(@ModelAttribute("uploadedFile") UploadedFile uploadedFile) {
        return new ModelAndView("index", "uploadedFile", uploadedFile);
    }

    @ModelAttribute("uploadedFile")
    public UploadedFile createUpladedFile() {
        return new UploadedFile();
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView uploadFile(@ModelAttribute("uploadedFile") UploadedFile uploadedFile, BindingResult result) {// имена параметров (тут - "uploadedFile") - из формы JSP.

        ModelAndView modelAndView = new ModelAndView();

        String fileName = null;

        MultipartFile file = uploadedFile.getFile();
        fileValidator.validate(uploadedFile, result);

        if (result.hasErrors()) {
            modelAndView.setViewName("index");
        } else {

            try {
                byte[] bytes = file.getBytes();

                fileName = file.getOriginalFilename();

                String rootPath = "C:\\Users\\16688641\\Documents\\git\\agoncal-sample-cdi\\springproject1\\target";
                File dir = new File(rootPath + File.separator + "loadFiles");

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File loadFile = new File(dir.getAbsolutePath() + File.separator + fileName);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(loadFile));
                stream.write(bytes);
                stream.flush();
                stream.close();

                //logger.info("uploaded: " + loadFile.getAbsolutePath());

                RedirectView redirectView = new RedirectView("index");
                redirectView.setStatusCode(HttpStatus.FOUND);
                modelAndView.setView(redirectView);
                modelAndView.addObject("uploadedFile", uploadedFile);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return modelAndView;
    }

    @RequestMapping(value = "/fileuploaded", method = RequestMethod.GET)
    public String fileUploaded() {
        return "fileuploaded";
    }

}
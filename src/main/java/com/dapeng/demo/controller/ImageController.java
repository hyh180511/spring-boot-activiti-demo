package com.dapeng.demo.controller;

import com.dapeng.demo.repository.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * @author
 * @since 2019-03-22
 */
@RestController
@RequestMapping("api/flow/img")
@Api(value = "Test", tags = {"跟踪高亮"})
public class ImageController {
    protected static Logger logger = LoggerFactory.getLogger(ImageController.class);
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/viewProcessImg", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "流程图跟踪及高亮显示", produces = "application/json")
    public void viewProcessImg(String processId, HttpServletResponse response) throws IOException {
        OutputStream os = null;
        try {
//            byte[] processImage = imageService.genProcessImage(processId);
//            OutputStream outputStream = response.getOutputStream();
//            InputStream in = new ByteArrayInputStream(processImage);
//            IOUtils.copy(in, outputStream);
//            System.out.println("ok");
            String directory = "F:" + File.separator + "temp" + File.separator;
            final String SUFFIX = ".png";
            File folder = new File(directory);
            File[] files = folder.listFiles();
            for (File file : files) {
                if (file.getName().equals(processId + SUFFIX)) {
                    file.delete();
                }
            }

//            byte[] processImage = imageService.genProcessImage(processId);
            byte[] processImage = imageService.getFlowImgByProcInstId(processId);
            File dest = new File(directory + processId + SUFFIX);
            os = new FileOutputStream(dest, true);
            os.write(processImage, 0, processImage.length);
            os.flush();


        } catch (Exception e) {
//            logger.error("viewProcessImg---- {}", ExceptionUtils.stackTraceText(e));
            logger.error("viewProcessImg---- {}", ExceptionUtils.getStackTrace(e));
//            logger.error("viewProcessImg---- {}", e);
        } finally {
            if (os != null) {
                os.close();
            }
        }

    }

}


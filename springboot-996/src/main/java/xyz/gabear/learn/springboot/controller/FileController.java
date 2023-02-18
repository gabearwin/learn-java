package xyz.gabear.learn.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.gabear.learn.springboot.domain.common.ResponseResult;
import xyz.gabear.learn.springboot.exception.BusinessException;
import xyz.gabear.learn.springboot.exception.ErrorCodeEnum;
import xyz.gabear.learn.springboot.service.FileService;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 类名称：FileController
 * ********************************
 * <p>
 * 类描述：文件服务Controller
 *
 * @author
 * @date 上午9:38
 */
@RestController
@RequestMapping("/api/files")
@Slf4j
public class FileController {

    @Resource(name = "localFileServiceImpl")
    private FileService fileService;

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    public ResponseResult<String> upload(@NotNull MultipartFile file) {

        // 文件上传
        try {

            fileService.upload(file.getInputStream(), file.getOriginalFilename());
        } catch (Exception e) {

            log.error("文件上传失败！", e);
            throw new BusinessException(ErrorCodeEnum.FILE_UPLOAD_FAILURE, e);
        }

        return ResponseResult.success(file.getOriginalFilename());
    }

}

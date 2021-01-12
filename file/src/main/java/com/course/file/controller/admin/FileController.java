package com.course.file.controller.admin;

import com.course.server.dto.FileDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.FileService;
import com.course.server.util.ValidatorUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/file")
public class FileController {

    public static final String BUSINESS_NAME = "file";

    @Resource
    private FileService fileService;

    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        fileService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody FileDto fileDto) {
        //validation
        ValidatorUtil.require(fileDto.getPath(), "path");
        ValidatorUtil.length(fileDto.getPath(), "path", 1, 100);
        ValidatorUtil.length(fileDto.getName(), "name", 1, 100);
        ValidatorUtil.length(fileDto.getSuffix(), "suffix", 1, 10);

        ResponseDto responseDto = new ResponseDto();
        fileService.save(fileDto);
        responseDto.setContent(fileDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        fileService.delete(id);
        return responseDto;
    }
}

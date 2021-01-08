package com.course.business.controller.admin;

import com.course.server.dto.*;
import com.course.server.service.CourseCategoryService;
import com.course.server.service.CourseService;
import com.course.server.util.ValidatorUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/course")
public class CourseController {

    public static final String BUSINESS_NAME = "course";

    @Resource
    private CourseService courseService;

    @Resource
    private CourseCategoryService courseCategoryService;

    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        courseService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody CourseDto courseDto) {
        //validation
        ValidatorUtil.require(courseDto.getName(), "name");
        ValidatorUtil.length(courseDto.getName(), "name", 1, 50);
        ValidatorUtil.length(courseDto.getSummary(), "summary", 1, 2000);

        ResponseDto responseDto = new ResponseDto();
        courseService.save(courseDto);
        responseDto.setContent(courseDto);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        courseService.delete(id);
        return responseDto;
    }

    @PostMapping("/list-category/{courseId}")
    public ResponseDto listCategory(@PathVariable(value = "courseId") String courseId) {
        ResponseDto responseDto = new ResponseDto();
        List<CourseCategoryDto> courseCategoryDtoList = courseCategoryService.listByCourse(courseId);
        responseDto.setContent(courseCategoryDtoList);
        return responseDto;
    }

    @GetMapping("/find-content/{id}")
    public ResponseDto findContent(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        CourseContentDto courseContentDto = courseService.findContent(id);
        responseDto.setContent(courseContentDto);
        return responseDto;
    }

    @PostMapping("/save-content")
    public ResponseDto saveContent(@RequestBody CourseContentDto courseContentDto) {
        ResponseDto responseDto = new ResponseDto();
        courseService.saveContent(courseContentDto);
        return responseDto;
    }

    @PostMapping("/sort")
    public ResponseDto sort(@RequestBody SortDto sortDto){
        ResponseDto responseDto = new ResponseDto();
        courseService.sort(sortDto);
        return responseDto;
    }
}

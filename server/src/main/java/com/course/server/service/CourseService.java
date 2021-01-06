package com.course.server.service;

import com.course.server.domain.Course;
import com.course.server.domain.CourseExample;
import com.course.server.dto.CourseDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.CourseMapper;
import com.course.server.mapper.my.MyCourseMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CourseService {

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private MyCourseMapper myCourseMapper;

    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        CourseExample courseExample = new CourseExample();
        courseExample.setOrderByClause("sort asc");
        List<Course> courseList = courseMapper.selectByExample(courseExample);
        PageInfo<Course> pageInfo = new PageInfo<>(courseList);
        pageDto.setTotal(pageInfo.getTotal());

        List<CourseDto> courseDtoList = CopyUtil.copyList(courseList, CourseDto.class);
        pageDto.setList(courseDtoList);

    }

    public void save(CourseDto courseDto) {
        Course course = CopyUtil.copy(courseDto, Course.class);
        if (StringUtils.isEmpty(courseDto.getId())) {
            this.insert(course);
        } else {
            this.update(course);
        }
    }

    /**
     * add new course
     *
     * @param course
     */
    private void insert(Course course) {
        Date now = new Date();
        course.setCreatedAt(now);
        course.setUpdatedAt(now);
        course.setId(UuidUtil.getShortUuid());
        courseMapper.insert(course);

    }

    /**
     * update an existed course
     *
     * @param course
     */
    private void update(Course course) {
        Date now = new Date();
        course.setUpdatedAt(now);
        courseMapper.updateByPrimaryKey(course);
    }

    public void delete(String id) {
        courseMapper.deleteByPrimaryKey(id);
    }

    /**
     * update course duration
     */
    public void updateTime(String courseId) {
        myCourseMapper.updateTime(courseId);
    }
}
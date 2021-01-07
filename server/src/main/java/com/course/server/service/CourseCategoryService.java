package com.course.server.service;

import com.course.server.domain.CourseCategory;
import com.course.server.domain.CourseCategoryExample;
import com.course.server.dto.CategoryDto;
import com.course.server.dto.CourseCategoryDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.CourseCategoryMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseCategoryService {

    @Resource
    private CourseCategoryMapper courseCategoryMapper;

    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        CourseCategoryExample courseCategoryExample = new CourseCategoryExample();
        List<CourseCategory> courseCategoryList = courseCategoryMapper.selectByExample(courseCategoryExample);
        PageInfo<CourseCategory> pageInfo = new PageInfo<>(courseCategoryList);
        pageDto.setTotal(pageInfo.getTotal());

        List<CourseCategoryDto> courseCategoryDtoList = CopyUtil.copyList(courseCategoryList, CourseCategoryDto.class);
        pageDto.setList(courseCategoryDtoList);

    }

    public void save(CourseCategoryDto courseCategoryDto) {
        CourseCategory courseCategory = CopyUtil.copy(courseCategoryDto, CourseCategory.class);
        if (StringUtils.isEmpty(courseCategoryDto.getId())) {
            this.insert(courseCategory);
        } else {
            this.update(courseCategory);
        }
    }

    /**
     * add new courseCategory
     *
     * @param courseCategory
     */
    private void insert(CourseCategory courseCategory) {
        courseCategory.setId(UuidUtil.getShortUuid());
        courseCategoryMapper.insert(courseCategory);

    }

    /**
     * update an existed courseCategory
     *
     * @param courseCategory
     */
    private void update(CourseCategory courseCategory) {
        courseCategoryMapper.updateByPrimaryKey(courseCategory);
    }

    public void delete(String id) {
        courseCategoryMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public void saveBatch(String courseId, List<CategoryDto> categoryDtoList) {
        // clean courseCategory according to courseId to avoid duplications when editing
        // an existing course
        CourseCategoryExample example = new CourseCategoryExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        courseCategoryMapper.deleteByExample(example);
        for (int i = 0; i < categoryDtoList.size(); i++) {
            CategoryDto categoryDto = categoryDtoList.get(i);
            CourseCategory courseCategory = new CourseCategory();
            courseCategory.setId(UuidUtil.getShortUuid());
            courseCategory.setCourseId(courseId);
            courseCategory.setCategoryId(categoryDto.getId());
            insert(courseCategory);
        }
    }

    /**
     * List course categories according to course id
     * @param courseId
     * @return
     */
    public List<CourseCategoryDto> listByCourse(String courseId) {
        CourseCategoryExample example = new CourseCategoryExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        List<CourseCategory> courseCategoryList = courseCategoryMapper.selectByExample(example);
        return CopyUtil.copyList(courseCategoryList, CourseCategoryDto.class);
    }
}
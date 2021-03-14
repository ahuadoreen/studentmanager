package com.example.studentmanager.controller;

import com.example.studentmanager.entity.ListWithPageData;
import com.example.studentmanager.entity.ResponseDataNew;
import com.example.studentmanager.entity.ResponseData;
import com.example.studentmanager.entity.Score;
import com.example.studentmanager.mapper.ScoreMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/score")
public class ScoreController {
    @Autowired
    ScoreMapper scoreMapper;

    @Parameters({
            @Parameter(name = "name", description = "学生姓名", schema = @Schema(implementation = String.class), in = ParameterIn.QUERY,
                    allowEmptyValue = true)})
    @GetMapping(value = "/scores")
    public ResponseDataNew<ListWithPageData<Score>> findScores(final String name, final Integer index, final Integer size){
        Page<Score> page = PageHelper.startPage(index + 1, size);
        List<Score> scores = scoreMapper.selectByStudent(name);
        ResponseDataNew<ListWithPageData<Score>> response = new ResponseDataNew<>();
        response.ok();
        ListWithPageData<Score> data = new ListWithPageData<>();
        data.setPageCount(page.getPages());
        data.setTotal(page.getTotal());
        data.setList(scores);
        response.setData(data);
        return response;
    }

    @PostMapping(value = "/addScore", consumes = { "application/x-www-form-urlencoded" })
    @ResponseBody
    public ResponseData addScore(final Integer score, Long studentId, Long subjectId, Long teacherId)
    {
        Score scoreO = new Score();
        scoreO.setScore(score);
        scoreO.setStudentId(studentId);
        scoreO.setSubjectId(subjectId);
        scoreO.setTeacherId(teacherId);
        scoreMapper.insert(scoreO);
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }

    @PostMapping(value = "/editScore", consumes = { "application/x-www-form-urlencoded" })
    @ResponseBody
    public ResponseData editScore(Long id, Integer score, Long studentId, Long subjectId, Long teacherId)
    {
        Score scoreO = new Score();
        scoreO.setId(id);
        scoreO.setScore(score);
        scoreO.setStudentId(studentId);
        scoreO.setSubjectId(subjectId);
        scoreO.setTeacherId(teacherId);
        scoreMapper.updateByPrimaryKey(scoreO);
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }

    @PostMapping(value = "/deleteScore", consumes = { "application/x-www-form-urlencoded" })
    @ResponseBody
    public ResponseData deleteScore(Long id)
    {
        scoreMapper.deleteByPrimaryKey(id);
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }
}

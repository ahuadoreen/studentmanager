package com.example.studentmanager.controller;

import com.example.studentmanager.entity.ResponseData;
import com.example.studentmanager.entity.Score;
import com.example.studentmanager.mapper.ScoreMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/score")
public class ScoreController {
    @Autowired
    ScoreMapper scoreMapper;

    @RequestMapping(value = "/scores")
    public ResponseData findScores(final String name, final Integer index, final Integer size){
        Page<Score> page = PageHelper.startPage(index + 1, size);
        List<Score> scores = scoreMapper.selectByStudent(name);
        ResponseData responseData = ResponseData.ok();
        responseData.putDataValue("pageCount", page.getPages());
        responseData.putDataValue("total", page.getTotal());
        responseData.putDataValue("scores", scores);
        return responseData;
    }

    @PostMapping(value = "/addScore")
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

    @PostMapping(value = "/editScore")
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

    @PostMapping(value = "/deleteScore")
    @ResponseBody
    public ResponseData deleteScore(Long id)
    {
        scoreMapper.deleteByPrimaryKey(id);
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }
}

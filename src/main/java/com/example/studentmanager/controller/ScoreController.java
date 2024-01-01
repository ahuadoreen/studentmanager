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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/score")
@Validated
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
    public ResponseData addScore(@Validated Score scoreO, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            ResponseData responseData = new ResponseData(400, bindingResult.getFieldError().getDefaultMessage());
            return responseData;
        } else {
            scoreMapper.insert(scoreO);
            ResponseData responseData = ResponseData.ok();
            return responseData;
        }
    }

    @PostMapping(value = "/editScore", consumes = { "application/x-www-form-urlencoded" })
    public ResponseData editScore(@Validated Score scoreO, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            ResponseData responseData = new ResponseData(400, bindingResult.getFieldError().getDefaultMessage());
            return responseData;
        } else {
            scoreMapper.updateByPrimaryKey(scoreO);
            ResponseData responseData = ResponseData.ok();
            return responseData;
        }
    }

    @PostMapping(value = "/deleteScore", consumes = { "application/x-www-form-urlencoded" })
    public ResponseData deleteScore(Long id)
    {
        scoreMapper.deleteByPrimaryKey(id);
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }

    @PostMapping(value = "/addScores")
    public ResponseData addScores(@RequestBody @Valid List<Score> scores, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            ResponseData responseData = new ResponseData(400, bindingResult.getFieldError().getDefaultMessage());
            return responseData;
        } else {
            scoreMapper.insertMultiple(scores);
            ResponseData responseData = ResponseData.ok();
            return responseData;
        }
    }

    @PostMapping(value = "/deleteScores")
    public ResponseData deleteScores(@RequestBody Long[] ids, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            ResponseData responseData = new ResponseData(400, bindingResult.getFieldError().getDefaultMessage());
            return responseData;
        } else {
            scoreMapper.deleteMultiple(ids);
            ResponseData responseData = ResponseData.ok();
            return responseData;
        }
    }
}

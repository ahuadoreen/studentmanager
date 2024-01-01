package com.example.studentmanager.controller;

import com.example.studentmanager.entity.ResponseData;
import com.example.studentmanager.entity.Subject;
import com.example.studentmanager.mapper.SubjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "subject", description = "subject api")
@RestController
@RequestMapping(value = "/subject")
public class SubjectController {
    @Autowired
    SubjectMapper subjectMapper;

    @Operation(summary = "get subjects list")
    @Parameters({
            @Parameter(name = "name", description = "科目名称", schema = @Schema(implementation = String.class), in = ParameterIn.QUERY,
                    allowEmptyValue = true),
            @Parameter(name = "index", description = "页码，从0开始", schema = @Schema(implementation = Integer.class), in = ParameterIn.QUERY),
            @Parameter(name = "size", description = "每页条数", schema = @Schema(implementation = Integer.class), in = ParameterIn.QUERY)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = { @Content(mediaType = "application/json",schema = @Schema(implementation = ResponseData.class),
                            examples = {@ExampleObject(value = "{\n" +
                                    "  \"message\": \"Ok\",\n" +
                                    "  \"code\": 200,\n" +
                                    "  \"data\": {\n" +
                                    "    \"pageCount\": 7,\n" +
                                    "    \"total\": 20,\n" +
                                    "    \"subjects\": [\n" +
                                    "      {\n" +
                                    "        \"id\": 1,\n" +
                                    "        \"name\": \"语文\"\n" +
                                    "      },\n" +
                                    "      {\n" +
                                    "        \"id\": 2,\n" +
                                    "        \"name\": \"英语\"\n" +
                                    "      },\n" +
                                    "      {\n" +
                                    "        \"id\": 3,\n" +
                                    "        \"name\": \"数学\"\n" +
                                    "      }\n" +
                                    "    ]\n" +
                                    "  }\n" +
                                    "}")})})})
    @GetMapping(value = "/subjects")
    public ResponseData findSubjects(final String name, final Integer index, final Integer size){
        Page<Subject> page = PageHelper.startPage(index + 1, size);
        List<Subject> subjectList = subjectMapper.findSubjects(name);
        ResponseData responseData = ResponseData.ok();
        responseData.putDataValue("pageCount", page.getPages());
        responseData.putDataValue("total", page.getTotal());
        responseData.putDataValue("subjects", subjectList);
        return responseData;
    }
    @PostMapping(value = "/addSubject", consumes = { "application/x-www-form-urlencoded" })
    public ResponseData addSubject(String name){
        subjectMapper.insertSubject(name);
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }

    @PostMapping(value = "/editSubject", consumes = { "application/x-www-form-urlencoded" })
    public ResponseData editSubject(Long id, final String name)
    {
        subjectMapper.updateSubject(id, name);
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }

    @PostMapping(value = "/deleteSubject", consumes = { "application/x-www-form-urlencoded" })
    public ResponseData deleteSubject(Long id)
    {
        subjectMapper.deleteSubject(id);
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }

    @PostMapping(value = "/addSubjects")
    public ResponseData addSubjects(@RequestBody List<Subject> subjects){
        subjectMapper.insertSubjects(subjects);
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }

    @PostMapping(value = "/editSubjects")
    public ResponseData editSubjects(@RequestBody List<Subject> subjects){
        subjectMapper.updateSubjects(subjects);
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }

    @PostMapping(value = "/deleteSubjects")
    public ResponseData deleteSubjects(@RequestBody Long[] ids)
    {
        subjectMapper.deleteSubjects(ids);
        ResponseData responseData = ResponseData.ok();
        return responseData;
    }
}

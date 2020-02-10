package com.training.platform.entities.es;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.util.Date;

@Data
@Document(indexName = "company", type = "employees")
public class Company {
    @Id
    @Field(type = FieldType.Keyword, store = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String firstName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String designation;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer salary;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date dateOfJoining;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String address;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Field(type = FieldType.Keyword, store = true)
    private String gender;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer age;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String maritalStatus;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String interests;
}

package com.efa.windoor.core.dtos;

import com.efa.windoor.core.constants.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class RuleDto implements Serializable {
    @JsonProperty("key")
    private Long id;

    private String name;
    @JsonProperty("desc")
    private String description;
    @JsonProperty("href")
    private String url;

    private Integer progress;

    private Status status;

    private Long createdAt;
    private Long updatedAt;

    private Long createdBy;
    private Long modifiedBy;

}

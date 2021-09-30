package com.efa.windoor.core.entities;

import com.efa.windoor.core.constants.Status;
import com.efa.windoor.core.constants.TableNames;
import com.efa.windoor.core.dtos.RuleDto;
import com.efa.windoor.core.entities.base.BaseEntity;
import com.efa.windoor.core.entities.base.ModifierEntity;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Entity
@Table(name = TableNames.WD_RULE)
public class RuleEntity extends ModifierEntity {

    private String name;
    private String description;
    private String url;

    @Max(100)
    @Min(0)
    private Integer progress;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    public RuleDto getDto(){
        RuleDto dto = new RuleDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}

package com.efa.windoor.admin.serivces;

import com.efa.windoor.admin.form.ResponseForm;
import com.efa.windoor.admin.form.table.DataTableForm;
import com.efa.windoor.admin.form.table.FilterForm;
import com.efa.windoor.core.constants.Status;
import com.efa.windoor.core.dtos.RuleDto;
import com.efa.windoor.core.entities.RuleEntity;
import com.efa.windoor.core.repositories.RuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RuleService {

    private final RuleRepository ruleRepository;

    public DataTableForm<RuleDto> getRules(FilterForm filterForm) {
        if (filterForm.getCurrent() == null) filterForm.setCurrent(0);
        if (filterForm.getPageSize() == null) filterForm.setPageSize(10);

        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        PageRequest pageRequest = PageRequest.of(filterForm.getCurrent() - 1, filterForm.getPageSize(), sort);
        Page<RuleEntity> entities = ruleRepository.findAll(pageRequest);

        DataTableForm<RuleDto> dataTable = new DataTableForm<>();
        dataTable.setData(entities.stream().map(RuleEntity::getDto).collect(Collectors.toList()));
        dataTable.setTotal(ruleRepository.count());
        dataTable.setSuccess(true);
        return dataTable;
    }

    public ResponseForm addRule(RuleDto ruleDto) {
        RuleEntity entity = new RuleEntity();
        entity.setName(ruleDto.getName());
        entity.setDescription(ruleDto.getDescription());

        Random random = new Random();
        entity.setProgress(random.nextInt(100));
        entity.setStatus(Status.RUNNING);
        ruleRepository.save(entity);

        return ResponseForm.builder().success(true).build();
    }

    public ResponseForm updateRule(RuleDto ruleDto) {
        RuleEntity entity = ruleRepository.findById(ruleDto.getId()).orElse(null);

        if (entity != null) {
            entity.setName(ruleDto.getName());
            entity.setDescription(ruleDto.getDescription());
            ruleRepository.save(entity);
        } else {
            return ResponseForm.builder().success(false).msg("Bunday role topilmadi").build();
        }
        return ResponseForm.builder().success(true).build();
    }
}

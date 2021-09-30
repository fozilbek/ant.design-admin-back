package com.efa.windoor.admin.controllers;

import com.efa.windoor.admin.form.ResponseForm;
import com.efa.windoor.admin.form.table.DataTableForm;
import com.efa.windoor.admin.form.table.FilterForm;
import com.efa.windoor.admin.serivces.RuleService;
import com.efa.windoor.core.dtos.RuleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class RuleController {

    private final RuleService ruleService;

    @GetMapping("/rule")
    public @ResponseBody
    DataTableForm<RuleDto> getRules(FilterForm filterForm) {
        return ruleService.getRules(filterForm);
    }

    @PostMapping("/rule")
    public ResponseForm addRules(@RequestBody RuleDto ruleDto) {
        return ruleService.addRule(ruleDto);
    }

    @PutMapping("/rule")
    public ResponseForm updateRules(@RequestBody RuleDto ruleDto) {
        return ruleService.updateRule(ruleDto);
    }
}

package com.example.calender.controller.plan;

import com.example.calender.service.plan.CalenderService;
import com.example.calender.service.plan.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/methods")
public class CalenderController {

    private final CalenderService calenderService;
    private final PlanService planService;

    @GetMapping
    public String calender(Model model) {

        var calender = calenderService.create();

        model.addAttribute("calender", calender);

        return "methods/calender";
    }


    @GetMapping("/list")
    public String list(Model model) {
        var planList = planService.find()
                .stream()
                .map(PlanDTO::toDTO)
                .toList();

        model.addAttribute("planList", planList);

        return "methods/list";
    }

    @GetMapping("/{id}")
    public String showDetail(@PathVariable("id") long planId, Model model) {
        var planDTO = planService.findById(planId)
                .map(s -> PlanDTO.toDTO(s))
                .orElseThrow(() -> new PlanNotFoundException());
        model.addAttribute("plan", planDTO);
        return "methods/detail";
    }

    @GetMapping("/form")
    public String showCreateForm(@ModelAttribute PlanForm form, Model model) {
        model.addAttribute("mode", "CREATE");
        return "methods/form";
    }

    @PostMapping
    public String create(@Validated PlanForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return showCreateForm(form, model);
        }
        planService.create(form.toEntity());
        return "redirect:/methods/list";
    }

    @GetMapping("/{id}/editForm")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        var form = planService.findById(id)
                .map(entity -> PlanForm.fromEntity(entity))
                .orElseThrow(() -> new PlanNotFoundException());
        model.addAttribute("planForm", form);
        model.addAttribute("mode", "EDIT");
        return "methods/form";
    }

    @PutMapping("{id}")
    public String update(@PathVariable("id") long id,
                         @Validated @ModelAttribute PlanForm form,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "EDIT");
            return "methods/form";
        }

        var entity = form.toEntity(id);
        planService.update(entity);
        return "redirect:/methods/{id}";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") long id){
        planService.delete(id);
        return "redirect:/methods/list";
    }

}
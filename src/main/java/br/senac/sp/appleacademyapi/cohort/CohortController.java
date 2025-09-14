package br.senac.sp.appleacademyapi.cohort;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping({"cohort", "cohort/"})
public class CohortController {
    
    private final CohortService service;

    public CohortController(CohortService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cohort> getAll(){
        return service.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cohort create(@RequestBody @Valid Cohort cohort){
        return service.create(cohort);
    }

    @GetMapping("{id}")
    public Cohort getById(@PathVariable UUID id){
        return service.getById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        //TODO check if there are students in the cohort
        service.delete(id);
    }

    @PutMapping("{id}")
    public Cohort update(@PathVariable UUID id, @RequestBody @Valid Cohort cohort){
        return service.update(id, cohort);
    }


}

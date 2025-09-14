package br.senac.sp.appleacademyapi.cohort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CohortService {

    private final CohortRepository repository;

    public CohortService(CohortRepository cohortRepository) {
        this.repository = cohortRepository;
    }

    public List<Cohort> getAll() {
        return repository.findAll();    
    }

    public Cohort create(Cohort cohort) {
        return repository.save(cohort);
    }

    public Cohort getById(UUID id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException( HttpStatus.NOT_FOUND ,"Cohort with id " +  id + " not found"));
    }

    public void delete(UUID id) {
        repository.delete(findById(id));
    }

    public Cohort update(UUID id, Cohort cohortToUpdate) {
        var cohort = findById(id);
        BeanUtils.copyProperties(cohortToUpdate, cohort, "id", "createdAt");
        cohort.setUpdatedAt(LocalDateTime.now());
        return repository.save(cohort);
    }

    private Cohort findById(UUID id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException( HttpStatus.NOT_FOUND ,"Cohort with id " +  id + " not found"));
    }

}

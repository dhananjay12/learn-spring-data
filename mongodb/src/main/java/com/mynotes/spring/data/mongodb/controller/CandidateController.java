package com.mynotes.spring.data.mongodb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mynotes.spring.data.mongodb.exception.ResourceNotFoundException;
import com.mynotes.spring.data.mongodb.model.Candidate;
import com.mynotes.spring.data.mongodb.repo.CandidateRepository;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping
    public List<Candidate> getAll() {
        return candidateRepository.findAll();
    }

    @GetMapping("/searchByEmail")
    public Candidate searchByEmail(@RequestParam(name = "email") String email) {
        return candidateRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException());

    }

    @GetMapping("/searchByExp")
    public List<Candidate> searchByExp(@RequestParam(name = "expFrom") Double expFrom, @RequestParam(name = "expTo", required = false) Double expTo) {
        List<Candidate> result = new ArrayList<>();
        if (expTo != null) {
            result = candidateRepository.findByExpBetween(expFrom, expTo);
        } else {
            result = candidateRepository.findByExpGreaterThanEqual(expFrom);
        }
        return result;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Candidate add(@RequestBody Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @PutMapping(value = "/{id}")
    public Candidate update(@PathVariable String id, @RequestBody Candidate updatedCandidate) {
        Candidate candidate = candidateRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException());
        candidate.setName(updatedCandidate.getName());
        candidate.setExp(updatedCandidate.getExp());
        candidate.setEmail(updatedCandidate.getEmail());
        return candidateRepository.save(candidate);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable String id) {
        Candidate candidate = candidateRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException());
        candidateRepository.delete(candidate);
    }

}

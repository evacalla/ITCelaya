package com.itcelaya.village.Controller;

import com.itcelaya.village.Exception.ResourceNotFoundException;
import com.itcelaya.village.Model.Function;
import com.itcelaya.village.Repository.FunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/functions")
public class Functions {

    @Autowired
    private FunctionRepository functionRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Function> getFunctions() {
        return functionRepository.findAll();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Function createFunction(@Valid @RequestBody Function function) { return functionRepository.save(function);}

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Function getFunction(@PathVariable("id") Long id) {
        return functionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Function", "id", id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Function putFunction(@PathVariable("id") Long id, @Valid @RequestBody Function editFunction) {
        Function function = functionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Function", "id", id));
        function.room = editFunction.room;
        function.version = editFunction.version;

        return functionRepository.save(function);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Function function = functionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("function", "id", id));
        functionRepository.delete(function);

        return ResponseEntity.ok().build();
    }
}

package com.itcelaya.village.Controller;

import com.itcelaya.village.Exception.ResourceNotFoundException;
import com.itcelaya.village.Model.Function;
import com.itcelaya.village.Model.Room;
import com.itcelaya.village.Model.Version;
import com.itcelaya.village.Repository.FunctionRepository;
import com.itcelaya.village.Repository.RoomRepository;
import com.itcelaya.village.Repository.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/functions")
public class Functions {

    @Autowired
    private FunctionRepository functionRepository;
    @Autowired
    private VersionRepository versionRepository;
    @Autowired
    private RoomRepository roomRepository;


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Function> getFunctions() {
        return functionRepository.findAll();
    }

    @RequestMapping(value = "/room/{roomId}/version/{versionId}", method = RequestMethod.POST)
    public Function createFunction(@PathVariable("roomId") Long roomId, @PathVariable("versionId") Long versionId) {

        Version version = versionRepository.findById(versionId)
                .orElseThrow(() -> new ResourceNotFoundException("Version", "id", versionId));

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", roomId));

        Function function = new Function();
        function.room = room;
        function.version = version;

        return functionRepository.save(function);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Function getFunction(@PathVariable("id") Long id) {
        return functionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Function", "id", id));
    }

    @RequestMapping(value = "/{id}/room/{roomId}/version/{versionId}", method = RequestMethod.PUT)
    public Function putFunction(@PathVariable("id") Long id,@PathVariable("roomId") Long roomId, @PathVariable("versionId") Long versionId) {

        Function function = functionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("function", "id", id));
        Version version = versionRepository.findById(versionId)
                .orElseThrow(() -> new ResourceNotFoundException("Version", "id", versionId));

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", roomId));

        function.version = version;
        function.room = room;

        return functionRepository.save(function);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Function function = functionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("function", "id", id));
        functionRepository.delete(function);

        return ResponseEntity.ok().build();
    }
}

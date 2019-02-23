package com.itcelaya.village.Controller;

import com.itcelaya.village.Exception.ResourceNotFoundException;
import com.itcelaya.village.Model.Function;
import com.itcelaya.village.Model.Seat;
import com.itcelaya.village.Model.Ticket;
import com.itcelaya.village.Model.User;
import com.itcelaya.village.Repository.FunctionRepository;
import com.itcelaya.village.Repository.SeatRepository;
import com.itcelaya.village.Repository.TicketRepository;
import com.itcelaya.village.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class Ticteks {

    @Autowired
    public TicketRepository ticketRepository;
    @Autowired
    public FunctionRepository functionRepository;
    @Autowired
    public SeatRepository seatRepository;
    @Autowired
    public UserRepository userRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Ticket> getAllTicket () {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());
        return ticketRepository.findByUser(user.id);
    }

    @RequestMapping(value = "/function/{functionId}/seat/{seatId}", method = RequestMethod.POST)
    public Ticket postTicket(@PathVariable("functionId") Long functionId, @PathVariable("seatId") Long seatId) {

        Function function = functionRepository.findById(functionId).orElseThrow(() -> new ResourceNotFoundException("Function", "id", functionId));;
        Seat seat = seatRepository.findById(seatId).orElseThrow(() -> new ResourceNotFoundException("Seat", "id", seatId));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findUserByUsername(authentication.getName());

        Ticket ticket = new Ticket();

        ticket.function = function;
        ticket.seat = seat;
        ticket.user = user;

        return ticketRepository.save(ticket);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Ticket getTicket (@PathVariable("id") Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", id));
    }

    @RequestMapping(value = "{id}/function/{functionId}/seat/{seatId}", method = RequestMethod.PUT)
    public Ticket putTicket (@PathVariable("id") Long id,@PathVariable("functionId") Long functionId, @PathVariable("seatId") Long seatId) {

        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", id));
        Function function = functionRepository.findById(functionId).orElseThrow(() -> new ResourceNotFoundException("Function", "id", functionId));;
        Seat seat = seatRepository.findById(seatId).orElseThrow(() -> new ResourceNotFoundException("Seat", "id", seatId));

        ticket.function = function;
        ticket.seat = seat;
        return ticketRepository.save(ticket);

    }

    @RequestMapping(value = "{/id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTicket (@PathVariable("id") Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", id));
        ticketRepository.delete(ticket);

        return ResponseEntity.ok().build();
    }
}

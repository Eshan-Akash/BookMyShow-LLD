package com.scaler.bmsfairmysql.services;

import com.scaler.bmsfairmysql.exceptions.ShowSeatNotAvailableException;
import com.scaler.bmsfairmysql.exceptions.UserNotFoundException;
import com.scaler.bmsfairmysql.models.ShowSeat;
import com.scaler.bmsfairmysql.models.Ticket;
import com.scaler.bmsfairmysql.models.User;
import com.scaler.bmsfairmysql.models.enums.SeatStatus;
import com.scaler.bmsfairmysql.repositories.ShowSeatRepository;
import com.scaler.bmsfairmysql.repositories.TicketRepository;
import com.scaler.bmsfairmysql.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private TicketRepository ticketRepository;
    private ShowSeatRepository showSeatRepository;
    private UserRepository userRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, ShowSeatRepository showSeatRepository, UserRepository userRepository){
        this.ticketRepository = ticketRepository;
        this.showSeatRepository = showSeatRepository;
        this.userRepository = userRepository;
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Ticket bookTicket(List<Long> showSeatIds, Long userId) throws ShowSeatNotAvailableException, UserNotFoundException {
        // Algorithm
        // 1. get all the show seats from repository
        // 2. check if they are available (available + locked 10 minutes ago)
        // 2.1 If not available. return with an exception
        // 2.2 If available. set the status to lock
        // 3. Return the ticket (after this we can send him to payment gateway)

        // get all the show seats from repository
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        // check if they are available
        for(ShowSeat showSeat: showSeats){
            if(isShowSeatAvailable(showSeat) == false){
                throw new ShowSeatNotAvailableException("One of the selected seats is not available. Offending seat " + showSeat.getSeat().getNumber());
            }
        }

        // set the status to lock
        List<ShowSeat> updatedShowSeats = new ArrayList<>();
        for(ShowSeat showSeat: showSeats){
            showSeat.setLockedAt(new Date());
            showSeat.setSeatStatus(SeatStatus.LOCKED);
            ShowSeat updatedShowSeat = showSeatRepository.save(showSeat);
            updatedShowSeats.add(updatedShowSeat);
        }

        // Return the ticket
        Optional<User> bookedBy = userRepository.findById(userId);
        if(bookedBy.isEmpty()){
            throw new UserNotFoundException("user not found for this id");
        }

        Ticket ticket = new Ticket();
        ticket.setBookedAt(new Date());
        ticket.setBookedBy(bookedBy.get());

        // set the amount -> by getting seattype from seat in showseat, make a call to ShowSeatTypeRepository

        ticket.setShowSeats(updatedShowSeats);
        Ticket savedTicket = ticketRepository.save(ticket);

        return savedTicket;
    }

    private boolean isShowSeatAvailable(ShowSeat showSeat){
        if(showSeat.getSeatStatus().equals(SeatStatus.AVAILABLE)){
            return true;
        }
        else if(showSeat.getSeatStatus().equals(SeatStatus.LOCKED)){
            long lockedAt = showSeat.getLockedAt().getTime(); // the number of ms elapsed since 1st jan 1970 till the time it was locked at
            long currentTime = System.currentTimeMillis();  // the number of ms elapsed since 1st jan 1970 till now
            long duration = (currentTime - lockedAt) / 1000;  // converting to seconds
            if(duration > 600){
                return true;
            }
        }
        return false;
    }
}

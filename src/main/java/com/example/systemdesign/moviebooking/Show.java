package com.example.systemdesign.moviebooking;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Show {
    private final String showId;
    private final Movie movie;
    private final Screen screen;
    private final ShowTime showTime;
    private final LocalDate date;
    private final List<Seat> seats;

    public Show(String showId, Movie movie, Screen screen, ShowTime showTime, LocalDate date) {
        this.showId = showId;
        this.movie = movie;
        this.screen = screen;
        this.showTime = showTime;
        this.date = date;
        this.seats = screen.generateSeats();
    }

    public Seat getSeatById(String seatId) {
        return seats.stream().filter(s -> s.getSeatId().equals(seatId)).findFirst().orElse(null);
    }

    public List<Seat> getAvailableSeats() {
        return seats.stream().filter(s -> !s.isBooked()).collect(Collectors.toList());
    }

    public List<Seat> getAvailableSeatsByType(SeatType type) {
        return seats.stream().filter(s -> !s.isBooked() && s.getType() == type).collect(Collectors.toList());
    }

    public String getShowId() { return showId; }
    public Movie getMovie() { return movie; }
    public Screen getScreen() { return screen; }
    public ShowTime getShowTime() { return showTime; }
    public LocalDate getDate() { return date; }
    public List<Seat> getSeats() { return seats; }

    @Override
    public String toString() {
        return showId + " | " + movie.getTitle() + " | " + screen.getScreenId() +
               " | " + showTime + " | " + date + " | Available: " + getAvailableSeats().size();
    }
}

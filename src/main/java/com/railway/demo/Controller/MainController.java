package com.railway.demo.Controller;


import com.railway.demo.model.Passenger;
import com.railway.demo.model.Station;
import com.railway.demo.model.Train;
import com.railway.demo.model.Trip;
import com.railway.demo.service.PassengerService;
import com.railway.demo.service.StationService;
import com.railway.demo.service.TrainService;
import com.railway.demo.service.TripService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    StationService stationService;
    @Autowired
    TrainService trainService;
    @Autowired
    TripService tripService;
    @Autowired
    PassengerService passengerService;

    @GetMapping("/")
    public String showHomePage(){ return "index";}

    @GetMapping("/station/new")
    public String showAddStationPage (Model model){
        model.addAttribute("station",new Station());
        return "newStation";
    }

    @PostMapping("/station/new")
    public String saveStation(@Valid @ModelAttribute("station") Station station, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("errors",bindingResult.getAllErrors());
            model.addAttribute("station",new Station());
            return "newStation";
        }
        stationService.saveStation(station);
        model.addAttribute("stations",stationService.getAllStationsPaged(0));
        model.addAttribute("currentPage",0);
        return "stations";
    }

    @GetMapping("/station/delete")
    public String deleteStation(@PathParam("stationId") int stationId,Model model){
        stationService.deleteStation(stationId);
        model.addAttribute("stations",stationService.getAllStationsPaged(0));
        model.addAttribute("currentPage",0);
        return "stations";
    }

    @GetMapping("/stations")
    public String showStationsList(@RequestParam(defaultValue = "0") int pageNo, Model model) {
        model.addAttribute("stations", stationService.getAllStationsPaged(pageNo));
        model.addAttribute("currentPage", pageNo);
        return "stations";
    }

    @GetMapping("train/new")
    public String showAddTrainPage(Model model){
        model.addAttribute("train",new Train());
        return "newTrain";
    }

    @PostMapping("/train/new")
    public String saveTrain (@Valid @ModelAttribute("train") Train train,BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("errors",bindingResult.getAllErrors());
            model.addAttribute("train",new Train());
            return "newTrain";
        }
        trainService.saveTrain(train);
        model.addAttribute("trains",trainService.getAllTrainsPaged(0));
        model.addAttribute("currentPage",0);
        return "trains";
    }

    @GetMapping("/train/delete")
    public String deleteTrain(@PathParam("trainId") long trainId,Model model){
        trainService.deleteTrainById(trainId);
        model.addAttribute("trains",trainService.getAllTrainsPaged(0));
        model.addAttribute("currentPage",0);
        return "trains";
    }

    @GetMapping("/trains")
    public String showTrainsList(@RequestParam(defaultValue = "0") int pageNo,Model model){
        model.addAttribute("trains",trainService.getAllTrainsPaged(pageNo));
        model.addAttribute("currentPage",pageNo);
        return "trains";
    }

    @GetMapping("/trip/new")
    public String showNewTripPage(Model model){
    model.addAttribute("trip",new Trip());
    model.addAttribute("trains",trainService.getAllTrains());
    model.addAttribute("stations",stationService.getAllStations());
    return "newTrip";
    }

    @PostMapping("/trip/new")
    public String saveTrip(@Valid @ModelAttribute("trips") Trip trip,BindingResult bindingResult,
                           @RequestParam("departureStation") int departureStation,
                           @RequestParam("destinationStation") int destinationStation,
                           @RequestParam("train") long trainId,
                           @RequestParam("arrivalTime") String arrivalTime,
                           @RequestParam("departureTime") String departureTime,
                           Model model){

        if (bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("trip",new Trip());
            model.addAttribute("trains",trainService.getAllTrains());
            model.addAttribute("stations",stationService.getAllStations());
            return "newTrip";
        }
        if (departureStation==destinationStation){
            model.addAttribute("sameStationError","Departure and destination station can't be same");
            model.addAttribute("trip",new Trip());
            model.addAttribute("trains",trainService.getAllTrains());
            model.addAttribute("stations",stationService.getAllStations());
            return "newTrip";
        }

        /*Save the Trip information*/
        trip.setTrain(trainService.getTrainById(trainId));
        trip.setDepartureStation(stationService.getStationById(departureStation));
        trip.setDestinationStation(stationService.getStationById(destinationStation));
        trip.setDepartureTime(departureTime);
        trip.setArrivalTime(arrivalTime);
        tripService.saveTrip(trip);

        /* Show all the trips after saving*/
        model.addAttribute("trips",tripService.getAllTripsPaged(0));
        model.addAttribute("currentPage",0);
        return "trips";
    }

    @GetMapping("/trip/delete")
    public String deleteTrip (@PathParam("tripId") long tripId,Model model){
        tripService.deleteTripById(tripId);

        /* Show all the trips after deleting*/
        model.addAttribute("trips",tripService.getAllTripsPaged(0));
        model.addAttribute("currentPage",0);
        return "trips";
    }

    @GetMapping("/trips")
    public String showTripsList(@RequestParam(defaultValue = "0") int pageNO,Model model){
        model.addAttribute("trips",tripService.getAllTripsPaged(pageNO));
        model.addAttribute("currentPage",pageNO);
        return "trips";
    }

    @GetMapping("/trip/search")
    public String showSearchTripPage(Model model){
        model.addAttribute("stations",stationService.getAllStations()) ;
        model.addAttribute("trips",null);
        return "searchTrip";
    }

    @PostMapping("/trip/search")
    public String searchTrip(@RequestParam("departureStation") int departureStation,
                             @RequestParam("destinationStation") int destinationStation,
                             @RequestParam("departureTime") String departureTime,
                             Model model){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate deptTime = LocalDate.parse(departureTime, dtf);
        Station depStation = stationService.getStationById(departureStation);
        Station destStation = stationService.getStationById(destinationStation);

        if (departureStation == destinationStation) {
            model.addAttribute("StationError", "Departure and destination station cant be same!");
            model.addAttribute("stations", stationService.getAllStations());
            return "searchTrip";
        }
        List<Trip> trips = tripService.getAllTripsByStationAndDepartureTime(depStation, destStation, deptTime);
        if(trips.isEmpty()){
            model.addAttribute("notFound", "No Record Found!");
        }else{
            model.addAttribute("trips", trips);
        }

        model.addAttribute("stations", stationService.getAllStations());
        return "searchTrip";
    }

    @GetMapping("/trip/book")
    public String showBookTripPage(Model model) {
        model.addAttribute("stations", stationService.getAllStations());
        return "bookTrip";
    }

    @PostMapping("/trip/book")
    public String searchTripToBook(@RequestParam("departureStation") int departureStation,
                                     @RequestParam("destinationStation") int destinationStation,
                                     @RequestParam("departureTime") String departureTime,
                                     Model model) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate deptTime = LocalDate.parse(departureTime, dtf);
        Station depStation = stationService.getStationById(departureStation);
        Station destStation = stationService.getStationById(destinationStation);

        if (departureStation == destinationStation) {
            model.addAttribute("StationError", "Departure and destination station cant be same!!!");
            model.addAttribute("stations", stationService.getAllStations());
            return "bookTrip";
        }
        List<Trip> trips = tripService.getAllTripsByStationAndDepartureTime(depStation, destStation, deptTime);
        if(trips.isEmpty()){
            model.addAttribute("notFound", "No Record Found!");
        }else{
            model.addAttribute("trips", trips);
        }
        model.addAttribute("stations", stationService.getAllStations());
        return "bookTrip";
    }

    @GetMapping("/trip/book/new")
    public String showCustomerInfoPage(@RequestParam long tripId, Model model) {
        model.addAttribute("tripId", tripId);
        model.addAttribute("passenger", new Passenger());
        return "newPassenger";
    }

    @PostMapping("/trip/book/new")
    public String bookTrip(@Valid @ModelAttribute("passenger") Passenger passenger, BindingResult bindingResult,
                           @RequestParam("tripId") long tripId, Model model) {
        Trip trip = tripService.getTripById(tripId);
        passenger.setTrip(trip);
        passengerService.savePassenger(passenger);
        model.addAttribute("passenger", passenger);
        return "confirmationPage";
    }

    @GetMapping("/trip/book/verify")
    public String showVerifyBookingPage() {
        return "verifyBooking";
    }

    @PostMapping("/trip/book/verify")
    public String showVerifyBookingPageResult(@RequestParam("tripId") long tripId,
                                              @RequestParam("passengerId") long passengerId,
                                              Model model) {
        /* create var trip in Class Trip and save inside it the current tripId which is givven by the http requset [POST] */
        Trip trip = tripService.getTripById(tripId);
        if (trip != null) {
            model.addAttribute("trip", trip);
            /* Link the trip database via the service and through the repository to get passengers by id trip*/
            List <Passenger> passengers = trip.getPassengers();
            Passenger passenger = null;
            /* here we pass Two classes together as an object to compare them */
            for (Passenger p : passengers) {
                if (p.getPassengerId() == passengerId) {
                    passenger = passengerService.getPassengerById(passengerId);
                    model.addAttribute("passenger", passenger);
                }
            }
            if (passenger != null) {
                return "verifyBooking";
            }else{
                System.out.print("YES LOST IT !!!!!!");
                model.addAttribute("notFound", "Not Found");
                return "verifyBooking";
            }
        } else {
            model.addAttribute("notFound", "Not Found");
            return "verifyBooking";
        }

    }

    @PostMapping("/trip/book/cancel")
    public String cancelTicket(@RequestParam("passengerId") long passengerId, Model model){
        passengerService.deletePassengerById(passengerId);
        model.addAttribute("trips", tripService.getAllTripsPaged(0));
        model.addAttribute("currentPage", 0);
        return "trips";
    }

    @GetMapping("passengers")
    public String showPassengerList(@RequestParam long tripId, Model model){
        List<Passenger> passengers = tripService.getTripById(tripId).getPassengers();
        model.addAttribute("passengers", passengers);
        model.addAttribute("trip", tripService.getTripById(tripId));
        return "passengers";
    }

    @GetMapping("/login")
    public String showLoginPage(){
        return "login";
    }


    @GetMapping("fancy")
    public String showLoginPage1(){
        return "index";
    }

    @GetMapping("/error")
    public String showErrorPage(){return "Error";}
}




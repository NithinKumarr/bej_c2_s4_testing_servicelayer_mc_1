package org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.controller;

import org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.domain.Track;
import org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.exception.TrackAlreadyExists;
import org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.exception.TrackNotFoundException;
import org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/t1")
public class TrackController {
      TrackService trackService;
    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }
    @PostMapping("/track")
    public ResponseEntity<?> addCustomer(@RequestBody Track track) throws TrackAlreadyExists
    {
        return new ResponseEntity<>(trackService.addTrack(track), HttpStatus.CREATED);
    }
    @GetMapping("/Track")
    public ResponseEntity<?> getAllCustomers()
    {
        return new ResponseEntity<>(trackService.getAllTracks(),HttpStatus.OK);
    }
    @DeleteMapping("/tracks/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int id) throws TrackNotFoundException
    {
        return new ResponseEntity<>(trackService.deleteTrack(id),HttpStatus.OK);
    }
    @GetMapping("/Tracks")
    public ResponseEntity<?> getAllTrackRatingMoreThan4()
    {
          return new ResponseEntity<>(trackService.trackRatingMoreThan4(),HttpStatus.CREATED);
    }
    @GetMapping("/tracks/{name}")
    public ResponseEntity<?> getAllArtistNameByJustinBieber(@PathVariable String name) throws TrackNotFoundException {
        return new ResponseEntity<>(trackService.findAllTrackByArtistName(name),HttpStatus.CREATED);
    }
}

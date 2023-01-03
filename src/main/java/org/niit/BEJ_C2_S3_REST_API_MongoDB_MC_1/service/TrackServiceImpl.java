package org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.service;

import org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.domain.Track;
import org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.exception.TrackAlreadyExists;
import org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.exception.TrackNotFoundException;
import org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TrackServiceImpl implements TrackService {
    TrackRepository trackRepository;
    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track addTrack(Track track) throws TrackAlreadyExists{
        if(trackRepository.findById(track.getTrackId()).isEmpty())
        {
            return trackRepository.insert(track);
        }
        throw new TrackAlreadyExists() ;
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public String deleteTrack(int track_id)throws TrackNotFoundException {
        if(trackRepository.findById(track_id).isEmpty())
        {
            throw new TrackNotFoundException();
        }
        trackRepository.deleteById(track_id);
        return "Track deleted  Successfully";
    }

    @Override
    public List<Track> trackRatingMoreThan4() {
        return trackRepository.findByTrackRating();
    }

    @Override
    public List<Track> findAllTrackByArtistName(String artistName) throws TrackNotFoundException {
        if((trackRepository.findAllByArtistName(artistName)).isEmpty())
        {
            throw new TrackNotFoundException();
        }
        return trackRepository.findAllByArtistName(artistName);
    }
}

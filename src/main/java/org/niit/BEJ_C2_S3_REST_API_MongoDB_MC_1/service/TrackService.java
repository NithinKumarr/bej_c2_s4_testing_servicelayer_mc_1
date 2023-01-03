package org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.service;

import org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.domain.Track;
import org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.exception.TrackAlreadyExists;
import org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.exception.TrackNotFoundException;

import java.util.List;

public interface TrackService {
    public Track addTrack(Track track) throws TrackAlreadyExists;
    public List<Track> getAllTracks();
    public String deleteTrack(int track_id) throws TrackNotFoundException;
    public List<Track> trackRatingMoreThan4();
    public List<Track>findAllTrackByArtistName(String artistName) throws TrackNotFoundException;
}

package org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.repository;

import org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.domain.Track;
import org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.exception.TrackNotFoundException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TrackRepository extends MongoRepository<Track,Integer>

{             @Query("{trackRating:{$gt:4}}")
            public List<Track>findByTrackRating();

            @Query("{'trackArtist.artistName':{$in:[?0]}}")
    public List<Track> findAllByArtistName(String artistName) throws TrackNotFoundException;

}
